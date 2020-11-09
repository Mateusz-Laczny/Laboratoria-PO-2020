package agh.cs.lab4;

import agh.cs.Lab5.AbstractWorldMap;
import agh.cs.lab2.Vector2d;

public class RectangularMap extends AbstractWorldMap {
    // Dimensions of the rectangle
    private final int width;
    private final  int height;
    // Visualizer for map drawing
    private final MapVisualiser visualizer = new MapVisualiser(this);

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected Vector2d[] calculateCornersForVisualization() {
        return new Vector2d[]{Vector2d.zero(), new Vector2d(width - 1, height - 1)};
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && isInsideMap(position);
    }

    /**
     * Checks whether given position is inside the boundaries of the map
     *
     * @param position
     *          A Vector2d object representing position to check
     * @return True if position is inside the map
     */
    private boolean isInsideMap(Vector2d position) {
        return Vector2d.zero().follows(position) &&
                (new Vector2d(width - 1, height - 1)).precedes(position);
    }
}
