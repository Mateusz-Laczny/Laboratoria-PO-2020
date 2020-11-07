package agh.cs.Lab5;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.MapVisualiser;

import java.lang.reflect.Array;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;


public class GrassField implements IWorldMap {
    private final List<Grass> grassSwathsList= new LinkedList<>();
    private final List<Animal> animalList = new LinkedList<>();
    // Visualizer for map drawing
    private final MapVisualiser visualizer = new MapVisualiser(this);

    public GrassField(int numOfGrassSwaths) {
        generateGrass(numOfGrassSwaths);
    }

    @Override
    public String toString() {
        return visualizer.draw(calculateCornersForVisualization()[0], calculateCornersForVisualization()[1]);
    }

    /**
     * Calculates map corners for visualization. Animals have higher priority than grass swaths
     *
     * @return An array consisting of the lower left and upper right corner
     */
    private Vector2d[] calculateCornersForVisualization() {
        Vector2d[] corners = new Vector2d[2];

        if(animalList.size() > 0) {
            corners[0] = animalList.get(0).getPosition();
            corners[1] = animalList.get(0).getPosition();

            for(Animal animal : animalList) {
                corners[0] = corners[0].lowerLeft(animal.getPosition());
                corners[1] = corners[1].upperRight(animal.getPosition());
            }
        }

        for(Grass grass : grassSwathsList) {
            corners[0] = corners[0].lowerLeft(grass.getPosition());
            corners[1] = corners[1].upperRight(grass.getPosition());
        }

        return corners;
    }

    /**
     * Fills up the map randomly within the (0,0), (sqrt(n*10), sqrt(n*10) rectangle, given number of swaths n
     *
     * @param numOfGrassSwaths
     *          Number of grass swaths to generate
     */
    private void generateGrass(int numOfGrassSwaths) {
        int maxPos = (int) Math.sqrt(numOfGrassSwaths * 10);

        for(int i = 0; i < numOfGrassSwaths; i++) {
            grassSwathsList.add(new Grass(generateRandomPosition(numOfGrassSwaths, maxPos)));
        }
    }

    /**
     * Generates valid random grass swath position for the grass generation
     *
     * @param numOfGrassSwaths
     *          Number based on which the position is generated
     * @param maxPosition
     *          Max boundary on the coordinate value. The minimum is 0
     * @return Vector2d object representing a position on a 2D map
     */
    private Vector2d generateRandomPosition(int numOfGrassSwaths, int maxPosition) {
        int randomPosX = ThreadLocalRandom.current().nextInt(0, maxPosition + 1);
        int randomPosY = ThreadLocalRandom.current().nextInt(0, maxPosition + 1);

        Vector2d generatedPos = new Vector2d(randomPosX, randomPosY);

        while (isOccupied(generatedPos)) {
            randomPosX = ThreadLocalRandom.current().nextInt(0, maxPosition + 1);
            randomPosY = ThreadLocalRandom.current().nextInt(0, maxPosition + 1);

            generatedPos = new Vector2d(randomPosX, randomPosY);
        }

        return generatedPos;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
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

        for (Grass grass : grassSwathsList) {
            if (grass.getPosition().equals(position)) {
                return Optional.of(grass);
            }
        }

        return Optional.empty();
    }
}
