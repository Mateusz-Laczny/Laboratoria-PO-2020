package agh.cs.lab2;

import java.util.Objects;

public class Vector2d {
    private int x_cr;
    private int y_cr;

    boolean precedes(Vector2d other) {
        return other.x_cr <= x_cr && other.y_cr <= y_cr;
    }

    boolean follows(Vector2d other) {
        return other.x_cr >= x_cr && other.y_cr >= y_cr;
    }

    Vector2d upperRight(Vector2d other) {
        int max_x_cr = x_cr;
        int max_y_cr = y_cr;

        if(max_x_cr < other.x_cr) {
            max_x_cr = other.x_cr;
        }

        if(max_y_cr < other.y_cr) {
            max_y_cr = other.y_cr;
        }

        return new Vector2d(max_x_cr, max_y_cr);
    }

    Vector2d lowerLeft(Vector2d other) {
        int min_x_cr = x_cr;
        int min_y_cr = y_cr;

        if(min_x_cr > other.x_cr) {
            min_x_cr = other.x_cr;
        }

        if(min_y_cr > other.y_cr) {
            min_y_cr = other.y_cr;
        }

        return new Vector2d(min_x_cr, min_y_cr);
    }

    Vector2d add(Vector2d other) {
        return new Vector2d(x_cr + other.x_cr, y_cr + other.y_cr);
    }

    Vector2d subtract(Vector2d other) {
        return new Vector2d(x_cr - other.x_cr, y_cr - other.y_cr);
    }

    Vector2d opposite() {
        return new Vector2d(-x_cr, -y_cr);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2D = (Vector2d) o;
        return x_cr == vector2D.x_cr &&
                y_cr == vector2D.y_cr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x_cr, y_cr);
    }

    @Override
    public String toString() {
        return "(" + x_cr + ", " + y_cr + ")";
    }

    public Vector2d(int x_cr, int y_cr) {
        this.x_cr = x_cr;
        this.y_cr = y_cr;
    }
}
