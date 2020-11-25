package agh.cs.lab5;

import agh.cs.lab6.IMapElement;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab7.MapBoundary;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.Math;


public class GrassField extends AbstractWorldMap {
    private final Map<Vector2d, Grass> grassSwathsMap = new LinkedHashMap<>();
    private final MapBoundary mapBoundary = new MapBoundary();

    public GrassField(int numOfGrassSwaths) {

        generateGrass(numOfGrassSwaths);
    }

    @Override
    protected Vector2d[] calculateCornersForVisualization() {
        return mapBoundary.returnCornersForVisualization();
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
            Vector2d randomPosition = generateRandomPosition(maxPos);
            Grass newGrass = new Grass(randomPosition);
            grassSwathsMap.put(randomPosition, newGrass);
            mapBoundary.addElementToList(newGrass);
        }
    }

    /**
     * Generates valid random grass swath position for the grass generation
     *
     * @param maxPosition
     *          Max boundary on the coordinate value. The minimum is 0
     * @return Vector2d object representing a position on a 2D map
     */
    private Vector2d generateRandomPosition(int maxPosition) {
        Vector2d generatedPos = getRandomVector2d(maxPosition);

        while (hasGrass(generatedPos)) {
            generatedPos = getRandomVector2d(maxPosition);
        }

        return generatedPos;
    }

    /**
     * Generates random 2D vector in a rectangle with corners (0,0), (maxPosition, maxPosition)
     *
     * @param maxPosition Maximal value of the vector coordinate
     * @return Vector2d object with random coordinates in the given rectangle
     */
    private Vector2d getRandomVector2d(int maxPosition) {
        int randomPosX = ThreadLocalRandom.current().nextInt(0, maxPosition + 1);
        int randomPosY = ThreadLocalRandom.current().nextInt(0, maxPosition + 1);

        return new Vector2d(randomPosX, randomPosY);
    }

    /**
     * Checks whether the given position on the map is occupied by a Grass object
     *
     * @param position
     *          Position to check
     * @return True if the position is occupied by grass.
     */
    private boolean hasGrass(Vector2d position) {
        return grassSwathsMap.containsKey(position);
    }

    @Override
    public Optional<IMapElement> objectAt(Vector2d position) {
        Optional<IMapElement> superObjectAt = super.objectAt(position);

        if(superObjectAt.isEmpty()) {
            if (grassSwathsMap.containsKey(position)) {
                return Optional.of(grassSwathsMap.get(position));
            }
        }

        return superObjectAt;
    }

    @Override
    public void place(Animal animal) throws IllegalArgumentException {
        super.place(animal);
        mapBoundary.addElementToList(animal);
    }
}
