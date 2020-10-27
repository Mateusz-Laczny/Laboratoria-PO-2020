package agh.cs.lab3;

import agh.cs.lab2.*;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        orientation = MapDirection.NORTH;
        position = new Vector2d(2, 2);
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "orientation=" + orientation +
                ", position=" + position;
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
        if(newPosition.precedes(Vector2d.zero()) && newPosition.follows(new Vector2d(4, 4))) {
            position = newPosition;
        }
    }
}
