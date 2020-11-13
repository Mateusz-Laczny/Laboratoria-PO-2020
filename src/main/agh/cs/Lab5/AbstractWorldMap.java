package agh.cs.Lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractWorldMap implements IWorldMap {
    // List of animals on the map
    protected final List<Animal> animalList = new LinkedList<>();
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
            animalList.add(animal);
            return true;
        } else {
            throw new IllegalArgumentException("Given position is incorrect");
        }
    }

    @Override
    public void run(List<MoveDirection> directions) {
        int animalIndex = 0;

        for(MoveDirection direction : directions) {
            animalList.get(animalIndex).move(direction);
            animalIndex = (animalIndex + 1) % animalList.size();
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return !objectAt(position).equals(Optional.empty());
    }

    @Override
    public Optional<Object> objectAt(Vector2d position) {
        for (Animal animal : animalList) {
            if (animal.getPosition().equals(position)) {
                return Optional.of(animal);
            }
        }

        return Optional.empty();
    }
}
