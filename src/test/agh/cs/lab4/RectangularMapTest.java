package agh.cs.lab4;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void testToString() {
        RectangularMap map = new RectangularMap(4, 5);
        map.place(new Animal(map));
        map.place(new Animal(map, new Vector2d(2, 4)));

        String correctRepresentation = " y\\x  0 1 2 3\n" +
                                       "  5: ---------\n" +
                                       "  4: | | |^| |\n" +
                                       "  3: | | | | |\n" +
                                       "  2: | | |^| |\n" +
                                       "  1: | | | | |\n" +
                                       "  0: | | | | |\n" +
                                       " -1: ---------\n";

        // Useful for comparing
        System.out.println("Correct");
        System.out.println(correctRepresentation);
        System.out.println("Actual");
        System.out.println(map);

        assertEquals(correctRepresentation, map.toString());
    }

    @Test
    void testCanMoveToCorrectPositions() {
        RectangularMap map = new RectangularMap(3, 7);

        List<Vector2d> listOfCorrectPositions = List.of(new Vector2d(1, 5),
                new Vector2d(2, 6), new Vector2d(1, 1));

        for(Vector2d position : listOfCorrectPositions) {
            assertTrue(map.canMoveTo(position));
        }
    }

    @Test
    void cantMoveToPositionsOutOfMap() {
        RectangularMap map = new RectangularMap(2, 6);

        List<Vector2d> listOfIncorrectPositions = List.of(new Vector2d(10, 10),
                new Vector2d(2, 7), new Vector2d(-2, 3),
                new Vector2d(1, 7), new Vector2d(0, -1));

        for(Vector2d position : listOfIncorrectPositions) {
            assertFalse(map.canMoveTo(position));
        }
    }

    @Test
    void cantMoveToOccupiedPositions() {
        RectangularMap map = new RectangularMap(3, 5);

        List<Animal> listOfAnimals = List.of(new Animal(map), new Animal(map, new Vector2d(1, 4)));
        List<Vector2d> incorrectPositions = new LinkedList<>();

        for(Animal animal : listOfAnimals) {
            map.place(animal);
            incorrectPositions.add(animal.getPosition());
        }

        for(Vector2d position: incorrectPositions) {
            assertFalse(map.canMoveTo(position));
        }
    }

    @Test
    void placesAnimalsOnCorrectPositions() {
        RectangularMap map = new RectangularMap(1, 8);
        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 7)));

        for(Animal animal : listOfAnimals) {
            assertTrue(map.place(animal));
        }
    }

    @Test
    void doesentPlaceAnimalsOnPositionsOutOfMap() {
        RectangularMap map = new RectangularMap(4, 20);
        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 100)),
                new Animal(map, new Vector2d(-1, 3)),
                new Animal(map, new Vector2d(0, -10)));

        for(Animal animal : listOfAnimals) {
            assertThrows(IllegalArgumentException.class, () -> map.place(animal));
        }
    }

    @Test
    void doesentPlaceAnimalsOnOccupiedPositions() {
        RectangularMap map = new RectangularMap(1, 8);
        
        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 7)));

        for(Animal animal : listOfAnimals) {
            map.place(animal);
            assertThrows(IllegalArgumentException.class, () -> map.place(animal));
        }
    }

    @Test
    void testRun() {
        RectangularMap map = new RectangularMap(4,4);

        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 2)), new Animal(map));

        List<MoveDirection> directions = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD);

        List<Vector2d> correctPositions = List.of(new Vector2d(1, 1),
                new Vector2d(0, 3),
                new Vector2d(0, 2),
                new Vector2d(3, 1));

        List<MapDirection> correctOrientations = List.of(MapDirection.EAST,
                MapDirection.WEST,
                MapDirection.WEST,
                MapDirection.EAST);

        for(Animal animal : listOfAnimals) {
            map.place(animal);
        }

        map.run(directions);
        int index = 0;

        for(Animal animal : listOfAnimals) {
            System.out.println(animal.getPosition());
            assertEquals(correctPositions.get(index), animal.getPosition());
            assertEquals(correctOrientations.get(index), animal.getOrientation());

            index += 1;
        }
    }

    @Test
    void isOccupiedReturnsTrueIfPositionIsOccupied() {
        RectangularMap map = new RectangularMap(10, 10);

        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 2)), new Animal(map));

        for (Animal animal : listOfAnimals) {
            map.place(animal);
            assertTrue(map.isOccupied(animal.getPosition()));
        }
    }

    @Test
    void isOccupiedReturnsFalseIfPositionIsNotOccupied() {
        RectangularMap map = new RectangularMap(10, 10);

        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 2)), new Animal(map));

        List<Vector2d> listOfPositions = List.of(new Vector2d(1, 1),
                new Vector2d(0, 4),
                new Vector2d(3, 0),
                new Vector2d(2, 3));

        for (Animal animal : listOfAnimals) {
            map.place(animal);
        }

        for (Vector2d position : listOfPositions) {
            assertFalse(map.isOccupied(position));
        }
    }

    @Test
    void objectAtReturnsCorrectObject() {
        RectangularMap map = new RectangularMap(10, 10);

        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 2)), new Animal(map));

        List<Vector2d> listOfPositions = List.of(new Vector2d(1, 1),
                new Vector2d(0, 4),
                new Vector2d(3, 0),
                new Vector2d(2, 3));

        for (Animal animal : listOfAnimals) {
            map.place(animal);
            assertEquals(Optional.of(animal), map.objectAt(animal.getPosition()));
        }

        for (Vector2d position : listOfPositions) {
            assertEquals(Optional.empty(), map.objectAt(position));
        }
    }
}