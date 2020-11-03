package agh.cs.lab2;

import java.util.Objects;

public class Vector2d {
    public final int x_coordinate;
    public final int y_coordinate;

    public Vector2d(int x_coordinate, int y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    public boolean precedes(Vector2d other) {
        return other.x_coordinate <= x_coordinate && other.y_coordinate <= y_coordinate;
    }

    public boolean follows(Vector2d other) {
        return other.x_coordinate >= x_coordinate && other.y_coordinate >= y_coordinate;
    }

    public Vector2d upperRight(Vector2d other) {
        int max_x_cr = x_coordinate;
        int max_y_cr = y_coordinate;

        if(max_x_cr < other.x_coordinate) {
            max_x_cr = other.x_coordinate;
        }

        if(max_y_cr < other.y_coordinate) {
            max_y_cr = other.y_coordinate;
        }

        return new Vector2d(max_x_cr, max_y_cr);
    }

    public Vector2d lowerLeft(Vector2d other) {
        int min_x_cr = x_coordinate;
        int min_y_cr = y_coordinate;

        if(min_x_cr > other.x_coordinate) {
            min_x_cr = other.x_coordinate;
        }

        if(min_y_cr > other.y_coordinate) {
            min_y_cr = other.y_coordinate;
        }

        return new Vector2d(min_x_cr, min_y_cr);
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x_coordinate + other.x_coordinate, y_coordinate + other.y_coordinate);
    }

    public Vector2d subtract(Vector2d other) {
        return new Vector2d(x_coordinate - other.x_coordinate, y_coordinate - other.y_coordinate);
    }

    public Vector2d opposite() {
        return new Vector2d(-x_coordinate, -y_coordinate);
    }

    /**
     *   Returns the zero vector (0,0)
     * @return Vector2D object with coordinates (0, 0)
    **/
    public static Vector2d zero() {
        return new Vector2d(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2D = (Vector2d) o;
        return x_coordinate == vector2D.x_coordinate &&
                y_coordinate == vector2D.y_coordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x_coordinate, y_coordinate);
    }

    @Override
    public String toString() {
        return "(" + x_coordinate + ", " + y_coordinate + ")";
    }
}
