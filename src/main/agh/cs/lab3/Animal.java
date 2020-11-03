package agh.cs.lab3;

import agh.cs.lab2.*;
import agh.cs.lab4.IWorldMap;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private final IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        orientation = MapDirection.NORTH;
        position = initialPosition;
        this.map = map;
    }

    public Animal(IWorldMap map) {
        orientation = MapDirection.NORTH;
        position = new Vector2d(2, 2);
        this.map = map;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

//    @Override
//    public String toString() {
//        return "orientation=" + orientation +
//                ", position=" + position;
//    }


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
            position = newPosition;
        }
    }
}
