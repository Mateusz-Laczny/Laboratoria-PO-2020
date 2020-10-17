package agh.cs.lab2;

public class World {
    public static void main(String[] args) {
        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println();

        // Testy klasy MapDirection
        MapDirection testEnum = MapDirection.NORTH;
        System.out.println(testEnum);
        System.out.println(testEnum.next());
        System.out.println(testEnum.previous());
        System.out.println(testEnum.toUnitVector());
    }
}
