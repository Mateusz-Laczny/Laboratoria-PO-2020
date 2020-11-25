package agh.cs.lab6;

import agh.cs.lab2.Vector2d;

/**
 * Interface for object supposed to work with classes implementing IMapElement
 */
public interface IMapElement {
    /**
     * Returns the position of the object in the 2D space
     *
     * @return Vector2d object representing the position of the object
     */
    Vector2d getPosition();

    /**
     * Returns priority for visualization. Higher number means higher priority. Priorities are always non-negative
     *
     * @return Int number representing priority for visualization
     */
    int getPriority();

    /**
     * Returns true if other map elements can't share the same position on the map with this object
     *
     * @return True if object is blocking it's position for other objects
     */
    boolean isBlockingMovement();

    // Methods used for creating comparators and sorting collections
    static int compareByXCoordinate(IMapElement element1, IMapElement element2) {
        if(element1.getPriority() != element2.getPriority()) {
            return Integer.compare(element1.getPriority(), element2.getPriority());
        } else {
            return Integer.compare(element1.getPosition().x_coordinate, element2.getPosition().x_coordinate);
        }
    }

    static int compareByYCoordinate(IMapElement element1, IMapElement element2) {
        if(element1.getPriority() != element2.getPriority()) {
            return Integer.compare(element1.getPriority(), element2.getPriority());
        } else {
            return Integer.compare(element1.getPosition().y_coordinate, element2.getPosition().y_coordinate);
        }
    }
}
