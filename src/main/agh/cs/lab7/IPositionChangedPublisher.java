package agh.cs.lab7;

public interface IPositionChangedPublisher {
    /**
     * Adds the given observer to the observer list of this object
     *
     * @param observer
     *          Object implementing IPositionChangeObserver
     */
    void addObserver(IPositionChangeObserver observer);

    /**
     * Removes the given observer from the observer list of this object.
     * If given observer is not on the list, the method does nothing
     *
     * @param observer
     *          Object implementing IPositionChangeObserver
     */
    void removeObserver(IPositionChangeObserver observer);
}
