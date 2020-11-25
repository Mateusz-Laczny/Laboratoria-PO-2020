package agh.cs.lab7;

import agh.cs.lab2.Vector2d;
import agh.cs.lab6.IMapElement;

public interface IPositionChangeObserver {
    void positionChanged(IMapElement movedElement, Vector2d oldPosition, Vector2d newPosition);
}
