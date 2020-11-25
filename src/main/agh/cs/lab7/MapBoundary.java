package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab6.IMapElement;

import java.util.LinkedList;
import java.util.List;

public class MapBoundary implements IPositionChangeObserver{
    List<IMapElement> sortedElementsX;
    List<IMapElement> sortedElementsY;

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
    }

    /**
     * Returns 2 Vector2d objects used for visualization purposes by the unbounded map types
     *
     * @return Vector2d array consisting of 2 Vector2d objects which are the corners of visualized rectangle
     */
    public Vector2d[] returnCornersForVisualization() {
        return new Vector2d[]{new Vector2d(sortedElementsX.get(0).getPosition().x_coordinate,
                sortedElementsY.get(0).getPosition().y_coordinate),
                new Vector2d(sortedElementsX.get(sortedElementsX.size() - 1).getPosition().x_coordinate,
                        sortedElementsY.get(sortedElementsY.size() - 1).getPosition().y_coordinate)};
    }
}
