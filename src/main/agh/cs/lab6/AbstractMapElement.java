package agh.cs.lab6;

import agh.cs.lab2.Vector2d;

public abstract class AbstractMapElement implements IMapElement{
    protected Vector2d position;
    protected final boolean blocksMovement;
    private final int priority;

    public AbstractMapElement(Vector2d position, boolean blocksMovement, int priority) {
        this.position = position;
        this.blocksMovement = blocksMovement;
        this.priority = priority;
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    public boolean isBlockingMovement() {
        return blocksMovement;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
