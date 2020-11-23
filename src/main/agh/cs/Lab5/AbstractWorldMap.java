package agh.cs.Lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;

import java.util.*;


public abstract class AbstractWorldMap implements IWorldMap {
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
    public boolean place(Animal animal) throws IllegalArgumentException {
        if(canMoveTo(animal.getPosition())) {
            animalMap.put(animal.getPosition(), animal);
            return true;
        } else {
            throw new IllegalArgumentException("Given position is incorrect");
        }
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int animalIndex = 0;
        Animal[] animals = animalMap.values().toArray(new Animal[0]);
        int numOfAnimals = animals.length;

        for(MoveDirection direction : directions) {
            Animal currentAnimal = animals[animalIndex];

            animalMap.remove(currentAnimal.getPosition());

            currentAnimal.move(direction);

            animalMap.put(currentAnimal.getPosition(), currentAnimal);

            animalIndex = (animalIndex + 1) % numOfAnimals;
        }

        // We repopulate the map to keep the correct order of animals
        animalMap.clear();

        for(Animal animal : animals) {
            animalMap.put(animal.getPosition(), animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position).isPresent();
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if (animalMap.containsKey(position)) {
            return Optional.of(animalMap.get(position));
        }

        return Optional.empty();
    }
}
