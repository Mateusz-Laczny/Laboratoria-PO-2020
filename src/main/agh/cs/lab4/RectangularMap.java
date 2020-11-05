package agh.cs.lab4;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RectangularMap implements IWorldMap{
    // List of animals currently present on the map
    // Używam listy ze względu na łatwość implementacji i dodawania nowych zwierząt na mapę
    private final List<Animal> animalList = new LinkedList<>();
    // Corners of the rectangle
    private final Vector2d upperRightCorner;
    private final Vector2d lowerLeftCorner;
    // Visualizer for map drawing
    private final MapVisualiser visualizer = new MapVisualiser(this);

    public RectangularMap(int width, int height) {
        upperRightCorner = new Vector2d(width, height);
        lowerLeftCorner = Vector2d.zero();
    }

    @Override
    public String toString() {
        return visualizer.draw(lowerLeftCorner, upperRightCorner);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        // Given position is not in the given rectangle
        return lowerLeftCorner.follows(position) && upperRightCorner.precedes(position) &&
        !isOccupied(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())) {
            animalList.add(animal);
            return true;
        } else {
            return false;
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
