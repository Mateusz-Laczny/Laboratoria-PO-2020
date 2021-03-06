package agh.cs.lab5;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {
    @Test
    void testCanMoveToCorrectPositions() {
        GrassField map = new GrassField(10);

        List<Vector2d> listOfCorrectPositions = List.of(new Vector2d(1, 5),
                new Vector2d(2, 6), new Vector2d(1, 1));

        for(Vector2d position : listOfCorrectPositions) {
            assertTrue(map.canMoveTo(position));
        }
    }

    @Test
    void cantMoveToOccupiedPositions() {
        GrassField map = new GrassField(1);

        List<Animal> listOfAnimals = List.of(new Animal(map), new Animal(map, new Vector2d(1, 4)));
        List<Vector2d> incorrectPositions = new LinkedList<>();

        for(Animal animal : listOfAnimals) {
            incorrectPositions.add(animal.getPosition());
        }

        for(Vector2d position: incorrectPositions) {
            assertFalse(map.canMoveTo(position));
        }
    }

    @Test
    void placesAnimalsOnCorrectPositions() {
        GrassField map = new GrassField(3);

        assertDoesNotThrow(() -> List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 7))));
    }

    @Test
    void doesentPlaceAnimalsOnOccupiedPositions() {
        GrassField map = new GrassField(1000);

        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 7)));

        for(Animal animal : listOfAnimals) {
            assertThrows(IllegalArgumentException.class, () -> new Animal(map, animal.getPosition()));
        }
    }

    @Test
    void testRun() {
        GrassField map = new GrassField(9);

        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 2)), new Animal(map));

        List<MoveDirection> directions = List.of(
                MoveDirection.FORWARD,  // 1
                MoveDirection.RIGHT,    // 2
                MoveDirection.FORWARD,  // 3
                MoveDirection.BACKWARD, // 4
                MoveDirection.RIGHT,    // 1
                MoveDirection.LEFT,     // 2
                MoveDirection.FORWARD,  // 3
                MoveDirection.RIGHT,    // 4
                MoveDirection.BACKWARD, // 1
                MoveDirection.LEFT,     // 2
                MoveDirection.LEFT,     // 3
                MoveDirection.FORWARD,  // 4
                MoveDirection.BACKWARD, // 1
                MoveDirection.FORWARD,  // 2
                MoveDirection.FORWARD); // 3

        List<Vector2d> correctPositions = List.of(new Vector2d(-2, 1),
                new Vector2d(-1, 3),
                new Vector2d(-1, 2),
                new Vector2d(3, 1));

        List<MapDirection> correctOrientations = List.of(MapDirection.EAST,
                MapDirection.WEST,
                MapDirection.WEST,
                MapDirection.EAST);

        map.run(directions);
        int index = 0;

        for(Animal animal : listOfAnimals) {
            assertEquals(correctPositions.get(index), animal.getPosition());
            assertEquals(correctOrientations.get(index), animal.getOrientation());

            index += 1;
        }
    }

    @Test
    void isOccupiedReturnsTrueIfPositionIsOccupied() {
        GrassField map = new GrassField(0);

        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 2)), new Animal(map));

        for (Animal animal : listOfAnimals) {
            assertTrue(map.isOccupied(animal.getPosition()));
        }
    }

    @Test
    void isOccupiedReturnsFalseIfPositionIsNotOccupied() {
        GrassField map = new GrassField(90);

        List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 2)),
                new Animal(map));

        List<Vector2d> listOfPositions = List.of(new Vector2d(1, 1),
                new Vector2d(0, 4),
                new Vector2d(3, 0),
                new Vector2d(2, 3));

        for (Vector2d position : listOfPositions) {
            assertFalse(map.isOccupied(position));
        }
    }

    @Test
    void objectAtReturnsCorrectObject() {
        GrassField map = new GrassField(8);

        List<Animal> listOfAnimals = List.of(new Animal(map, new Vector2d(0, 1)),
                new Animal(map, new Vector2d(0, 3)),
                new Animal(map, new Vector2d(0, 2)), new Animal(map));

        List<Vector2d> listOfPositions = List.of(new Vector2d(1, 1),
                new Vector2d(0, 4),
                new Vector2d(3, 0),
                new Vector2d(2, 3));

        for (Animal animal : listOfAnimals) {
            assertEquals(Optional.of(animal), map.objectAt(animal.getPosition()));
        }

        for (Vector2d position : listOfPositions) {
            assertEquals(Optional.empty(), map.objectAt(position));
        }
    }
}