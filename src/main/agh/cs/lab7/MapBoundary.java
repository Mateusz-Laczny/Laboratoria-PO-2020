package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab6.IMapElement;

import java.util.LinkedList;
import java.util.List;

public class MapBoundary implements IPositionChangeObserver{
    private final List<IMapElement> sortedElementsX;
    private final List<IMapElement> sortedElementsY;

    public MapBoundary() {
        sortedElementsX = new LinkedList<>();
        sortedElementsY = new LinkedList<>();
    }

    @Override
    public void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition) {
        IMapElement lowestX = sortedElementsX.get(0);
        IMapElement highestX = sortedElementsX.get(sortedElementsX.size() - 1);

        IMapElement lowestY = sortedElementsY.get(0);
        IMapElement highestY = sortedElementsY.get(sortedElementsY.size() - 1);

        // Conditions for sorting the first list
        if(movedElement.equals(lowestX) || lowestX.getPosition().x_coordinate > newPosition.x_coordinate ||
                movedElement.equals(highestX) || highestX.getPosition().x_coordinate < newPosition.x_coordinate) {
            sortedElementsX.sort(IMapElement::compareByXCoordinate);
        }

        // Conditions for sorting the second list
        if(movedElement.equals(lowestY) || lowestY.getPosition().y_coordinate > newPosition.y_coordinate ||
                movedElement.equals(highestY) || highestY.getPosition().y_coordinate < newPosition.y_coordinate) {
            sortedElementsY.sort(IMapElement::compareByYCoordinate);
        }
    }

    /**
     * Adds the given object to two collections sorted by the X and Y coordinate
     *
     * @param element
     *          IMapElement object which is supposed to be added to the collection
     */
    public void addElementToList(IMapElement element) {
        sortedElementsX.add(element);
        sortedElementsY.add(element);

        // New element could be a new corner, so we have to sort both lists
        sortedElementsX.sort(IMapElement::compareByXCoordinate);
        sortedElementsY.sort(IMapElement::compareByYCoordinate);
    }

    /**
     * Returns 2 Vector2d objects used for visualization purposes by the unbounded map types
     *
     * @return Vector2d array consisting of 2 Vector2d objects which are the corners of visualized rectangle
     */
    public Vector2d[] returnCornersForVisualization() {
        int lowerLeftXCoordinate = 0;
        int lowerLeftYCoordinate = 0;
        int upperRightXCoordinate = 0;
        int upperRightYCoordinate = 0;

        if (sortedElementsX.size() > 0) {
            lowerLeftXCoordinate = sortedElementsX.get(0).getPosition().x_coordinate;
            upperRightXCoordinate = sortedElementsX.get(sortedElementsX.size() - 1).getPosition().x_coordinate;
        }

        if (sortedElementsY.size() > 0) {
            lowerLeftYCoordinate = sortedElementsY.get(0).getPosition().y_coordinate;
            upperRightYCoordinate = sortedElementsY.get(sortedElementsY.size() - 1).getPosition().y_coordinate;
        }

        return new Vector2d[]{new Vector2d(lowerLeftXCoordinate, lowerLeftYCoordinate),
                new Vector2d(upperRightXCoordinate, upperRightYCoordinate)};
    }
}
