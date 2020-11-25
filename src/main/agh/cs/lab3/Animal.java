package agh.cs.lab3;

import agh.cs.lab6.AbstractMapElement;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.MapDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab7.IPositionChangeObserver;
import agh.cs.lab7.IPositionChangedPublisher;

import java.util.LinkedList;
import java.util.List;

public class Animal extends AbstractMapElement implements IPositionChangedPublisher {
    private MapDirection orientation;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers;

    public Animal(IWorldMap map, Vector2d initialPosition) throws IllegalArgumentException{
        super(initialPosition, true, 0);
        this.map = map;
        map.place(this);
        orientation = MapDirection.NORTH;
        observers = new LinkedList<>();
        observers.add((IPositionChangeObserver) map);
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "^";
            case WEST -> "<";
            case SOUTH -> "v";
            case EAST -> ">";
        };
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> changePosition(orientation.toUnitVector());
            case BACKWARD -> changePosition(orientation.toUnitVector().opposite());
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
        }
    }

    private void changePosition(Vector2d changeVector) {
        Vector2d newPosition = position.add(changeVector);
        if(map.canMoveTo(newPosition)) {
            Vector2d oldPosition = position;
            position = newPosition;

            positionChanged(oldPosition);
        }
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(this, oldPosition, position);
        }
    }
}
