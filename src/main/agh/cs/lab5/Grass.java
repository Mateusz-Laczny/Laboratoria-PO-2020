package agh.cs.lab5;

import agh.cs.lab6.AbstractMapElement;
import agh.cs.lab2.Vector2d;

public class Grass extends AbstractMapElement {
    public Grass(Vector2d position) {
        super(position, false, 10);
    }

    @Override
    public String toString() {
        return "*";
    }
}
