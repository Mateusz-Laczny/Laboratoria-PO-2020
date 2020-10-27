package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;

import java.util.LinkedList;
import java.util.List;

public class World {
    public static void main(String[] args) {
        Animal newAnimal = new Animal();
        List<MoveDirection> directionList = OptionsParser.parse(args);

        System.out.println(newAnimal);

        for(MoveDirection direction : directionList) {
            System.out.println("Moving in the " + direction.toString() + " direction");
            newAnimal.move(direction);
            System.out.println(newAnimal);
        }
    }
}
