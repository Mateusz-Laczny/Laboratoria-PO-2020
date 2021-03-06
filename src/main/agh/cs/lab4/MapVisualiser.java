package agh.cs.lab4;

// TODO: importy mogą wymagać aktualizacji w zależności od struktury projektu!
import agh.cs.lab6.IMapElement;
import agh.cs.lab2.Vector2d;

import java.util.Optional;

/**
 * The map visualizer converts the {@link IWorldMap} map into a string
 * representation.
 *
 */
public class MapVisualiser {
    private static final String EMPTY_CELL = " ";
    private static final String FRAME_SEGMENT = "-";
    private static final String CELL_SEGMENT = "|";
    private final IWorldMap map;

    /**
     * Initializes the MapVisualizer with an instance of map to visualize.
     *
     * @param map The map to be visualised.
     */
    public MapVisualiser(IWorldMap map) {
        this.map = map;
    }

    /**
     * Convert selected region of the map into a string. It is assumed that the
     * indices of the map will have no more than two characters (including the
     * sign).
     *
     * @param lowerLeft  The lower left corner of the region that is drawn.
     * @param upperRight The upper right corner of the region that is drawn.
     * @return String representation of the selected region of the map.
     */
    public String draw(Vector2d lowerLeft, Vector2d upperRight) {
        StringBuilder builder = new StringBuilder();
        for (int i = upperRight.y_coordinate + 1; i >= lowerLeft.y_coordinate - 1; i--) {
            if (i == upperRight.y_coordinate + 1) {
                builder.append(drawHeader(lowerLeft, upperRight));
            }
            builder.append(String.format("%3d: ", i));
            for (int j = lowerLeft.x_coordinate; j <= upperRight.x_coordinate + 1; j++) {
                if (i < lowerLeft.y_coordinate || i > upperRight.y_coordinate) {
                    builder.append(drawFrame(j <= upperRight.x_coordinate));
                } else {
                    builder.append(CELL_SEGMENT);
                    if (j <= upperRight.x_coordinate) {
                        builder.append(drawObject(new Vector2d(j, i)));
                    }
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    private String drawFrame(boolean innerSegment) {
        if (innerSegment) {
            return FRAME_SEGMENT + FRAME_SEGMENT;
        } else {
            return FRAME_SEGMENT;
        }
    }

    private String drawHeader(Vector2d lowerLeft, Vector2d upperRight) {
        StringBuilder builder = new StringBuilder();
        builder.append(" y\\x ");
        for (int j = lowerLeft.x_coordinate; j < upperRight.x_coordinate + 1; j++) {
            builder.append(String.format("%2d", j));
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    private String drawObject(Vector2d currentPosition) {
        String result = EMPTY_CELL;
        Optional<IMapElement> object = this.map.objectAt(currentPosition);

        if(object.isPresent()) {
            result = object.get().toString();
        }

        return result;
    }
}
