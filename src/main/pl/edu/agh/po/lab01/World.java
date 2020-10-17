package pl.edu.agh.po.lab01;
import agh.cs.lab1.Direction;

public class World {
    private static Direction[] convertStringToEnum(String[] directionStringArray) {
        Direction[] result = new Direction[directionStringArray.length];

        for (int i = 0; i < directionStringArray.length; i++) {
            switch (directionStringArray[i]) {
                case "f" -> result[i] = Direction.FORWARD;
                case "b" -> result[i] = Direction.BACKWARD;
                case "l" -> result[i] = Direction.LEFT;
                case "r" -> result[i] = Direction.RIGHT;
            }
        }

        return result;
    }

    private static void run(Direction[] arr) {
        for (Direction direction : arr) {
            System.out.print("Zwierak idzie ");

            switch (direction) {
                case FORWARD -> System.out.print("do przodu");
                case BACKWARD -> System.out.print("do tyłu");
                case LEFT -> System.out.print("w lewo");
                case RIGHT -> System.out.print("w prawo");
            }

            System.out.println();
        }

        System.out.println("\nWprowadzone dane:");

        if (arr.length > 0) {
            System.out.print(arr[0] + ",");

            for (int index = 1; index < arr.length - 1; index++) {
                System.out.print(" " + arr[index] + ",");
            }

            System.out.print(" " + arr[arr.length - 1] + '\n');
        } else {
            System.out.println("Brak");
        }
    }

    public static void main(String[] args) {
        // Debug
        //String[] test = new String[3];
        //test[0] = "ala";
        //test[1] = "ma";
        //test[2] = "kota";

        System.out.println("Start systemu");
        System.out.println("****************************************");
        run(convertStringToEnum(args));
        System.out.println("****************************************");
        System.out.println("Koniec działania systemu");
    }
}
