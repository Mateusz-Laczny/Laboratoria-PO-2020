package agh.cs.Lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap {
    // List of animals on the map
    // TODO ADD FINAL BACK
    protected Map<Vector2d, Animal> animalMap = new LinkedHashMap<>();
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

        for(MoveDirection direction : directions) {
            Vector2d[] keys = animalMap.keySet().toArray(new Vector2d[0]);

            Animal currentAnimal = animalMap.get(keys[animalIndex]);

            Vector2d oldPosition = currentAnimal.getPosition();
            currentAnimal.move(direction);

            // Na razie nie znamy lepszego rozwiązania :(
            if(!currentAnimal.getPosition().equals(oldPosition)) {
                Map<Vector2d, Animal> newAnimalMap = new LinkedHashMap<>();
                for(Vector2d key : keys) {
                    if(key.equals(oldPosition)) {
                        newAnimalMap.put(currentAnimal.getPosition(), currentAnimal);
                    } else {
                        newAnimalMap.put(key, animalMap.get(key));
                    }
                }

                animalMap = newAnimalMap;
            }

            animalIndex = (animalIndex + 1) % keys.length;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return !objectAt(position).equals(Optional.empty());
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        if (animalMap.containsKey(position)) {
            return Optional.of(animalMap.get(position));
        }

        return Optional.empty();
    }
}
