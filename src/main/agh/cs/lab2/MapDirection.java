package agh.cs.lab2;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    public MapDirection next () {
        return switch (this) {
            case EAST -> SOUTH;
            case WEST -> NORTH;
            case NORTH -> EAST;
            case SOUTH -> WEST;
        };
    }

    public MapDirection previous () {
        return switch (this) {
            case SOUTH -> EAST;
            case NORTH -> WEST;
            case EAST -> NORTH;
            case WEST -> SOUTH;
        };
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case SOUTH -> new Vector2d(0, -1);
            case NORTH -> new Vector2d(0, 1);
            case EAST -> new Vector2d(1, 0);
            case WEST -> new Vector2d(-1, 0);
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case SOUTH -> "Południe";
            case NORTH -> "Północ";
            case EAST -> "Wschód";
            case WEST -> "Zachód";
        };
    }
}
