package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    void correctlyCreatesObject() {
        Animal testAnimal = new Animal();
        assertEquals(MapDirection.NORTH, testAnimal.getOrientation());
        assertEquals(new Vector2d(2, 2), testAnimal.getPosition());
    }

    @Test
    void testToString() {
        Animal testAnimal = new Animal();
        assertEquals("orientation=Północ, position=(2, 2)", testAnimal.toString());
    }

    @Test
    void movesInGivenDirection() {
        Animal testAnimal1 = new Animal();
        testAnimal1.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), testAnimal1.getPosition());

        Animal testAnimal2 = new Animal();
        testAnimal2.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2, 1), testAnimal2.getPosition());
    }

    @Test
    void correctlyChangesOrientationClockwise() {
        Animal testAnimal1 = new Animal();
        testAnimal1.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, testAnimal1.getOrientation());

        testAnimal1.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, testAnimal1.getOrientation());

        testAnimal1.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.WEST, testAnimal1.getOrientation());

        testAnimal1.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.NORTH, testAnimal1.getOrientation());
    }

    @Test
    void correctlyChangesOrientationCounterclockwise() {
        Animal testAnimal1 = new Animal();
        testAnimal1.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, testAnimal1.getOrientation());

        testAnimal1.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, testAnimal1.getOrientation());

        testAnimal1.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, testAnimal1.getOrientation());

        testAnimal1.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, testAnimal1.getOrientation());
    }
}