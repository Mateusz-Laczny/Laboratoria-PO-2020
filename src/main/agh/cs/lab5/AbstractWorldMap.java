package agh.cs.lab5;

import agh.cs.lab6.IMapElement;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;
import agh.cs.lab7.IPositionChangeObserver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    // List of animals on the map
    protected final Map<Vector2d, Animal> animalMap = new LinkedHashMap<>();
    // Visualizer for map drawing
    private final MapVisualiser visualizer = new MapVisualiser(this);

    @Override
    public String toString() {
        return visualizer.draw(calculateCornersForVisualization()[0], calculateCornersForVisualization()[1]);
    }

    /**
     * Calculates map corners for visualization
     *
     * @return An array consisting of the lower left and upper right corners for visualization
     */
    protected abstract Vector2d[] calculateCornersForVisualization();

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public void place(Animal animal) throws IllegalArgumentException {
        if(canMoveTo(animal.getPosition())) {
            animalMap.put(animal.getPosition(), animal);
            animal.addObserver(this);
        } else {
            throw new IllegalArgumentException("Given position is incorrect. " +
                    "It's either occupied by an object that blocks movement, or is out of map bounds");
        }
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int animalIndex = 0;
        Animal[] animals = animalMap.values().toArray(new Animal[0]);
        int numOfAnimals = animals.length;

        for(MoveDirection direction : directions) {
            Animal currentAnimal = animals[animalIndex];

            currentAnimal.move(direction);

            animalIndex = (animalIndex + 1) % numOfAnimals;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if(objectAt(position).isPresent()) {
            return objectAt(position).get().isBlockingMovement();
        }

        return false;
    }

    @Override
    public Optional<IMapElement> objectAt(Vector2d position) {
        if (animalMap.containsKey(position)) {
            return Optional.of(animalMap.get(position));
        }

        return Optional.empty();
    }

    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        Animal[] animals = animalMap.values().toArray(new Animal[0]);
        Animal currentAnimal = animalMap.get(oldPosition);

        animalMap.clear();

        for(Animal animal : animals) {
            if(animal.equals(currentAnimal)) {
                animalMap.put(newPosition, currentAnimal);
            } else {
                animalMap.put(animal.getPosition(), animal);
            }
        }
    }
}
