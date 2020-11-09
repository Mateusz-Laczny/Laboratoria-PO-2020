package agh.cs.Lab5;

import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;

import java.util.concurrent.ThreadLocalRandom;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;


public class GrassField extends AbstractWorldMap {
    private final List<Grass> grassSwathsList= new LinkedList<>();

    public GrassField(int numOfGrassSwaths) {
        generateGrass(numOfGrassSwaths);
    }

    @Override
    public String toString() {
        return visualizer.draw(calculateCornersForVisualization()[0], calculateCornersForVisualization()[1]);
    }

    @Override
    protected Vector2d[] calculateCornersForVisualization() {
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

        while (hasGrass(generatedPos)) {
            randomPosX = ThreadLocalRandom.current().nextInt(0, maxPosition + 1);
            randomPosY = ThreadLocalRandom.current().nextInt(0, maxPosition + 1);

            generatedPos = new Vector2d(randomPosX, randomPosY);
        }

        return generatedPos;
    }

    /**
     * Checks whether the given position on the map is occupied by a Grass object
     *
     * @param position
     *          Position to check
     * @return True if the position is occupied by grass.
     */
    private boolean hasGrass(Vector2d position) {
        for (Grass grass : grassSwathsList) {
            if (grass.getPosition().equals(position)) {
                return true;
            }
        }

        return false;
    }
}
