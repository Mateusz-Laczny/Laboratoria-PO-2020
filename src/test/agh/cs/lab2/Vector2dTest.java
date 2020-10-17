package agh.cs.lab2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    @Test
    public void equalsReturnsCorrectValue() {
        Vector2d testVector1 = new Vector2d(1, 0);
        Vector2d testVector2 = new Vector2d(1, 0);
        Vector2d testVector3 = new Vector2d(0, 1);
        Vector2d testVector1Copy = testVector1;
        Object dummyObject = new Object();

        assertEquals(testVector1, testVector1Copy);
        assertEquals(testVector1, testVector2);
        assertNotEquals(testVector1, testVector3);
        assertNotEquals(testVector1, dummyObject);
    }

    @Test
    public void returnsCorrectString() {
        Vector2d testVector1 = new Vector2d(1, 0);
        assertEquals("(1, 0)", testVector1.toString());
    }

    @Test
    public void correctlyChecksOrientation() {
        Vector2d testVector0 = new Vector2d(0, 0);
        Vector2d testVector1 = new Vector2d(1, 0);
        Vector2d testVector2 = new Vector2d(-1, 0);
        Vector2d testVector3 = new Vector2d(0, -2);

        assertTrue(testVector0.follows(testVector1));
        assertFalse(testVector1.follows(testVector0));
        assertTrue(testVector2.follows(testVector0));
        assertFalse(testVector3.follows(testVector2));

        assertFalse(testVector0.precedes(testVector1));
        assertTrue(testVector1.precedes(testVector0));
        assertFalse(testVector2.precedes(testVector0));
        assertFalse(testVector3.precedes(testVector2));
    }

    @Test
    public void returnsCorrectCorner() {
        Vector2d testVector1 = new Vector2d(1, 2);
        Vector2d testVector2 = new Vector2d(3, 0);

        assertEquals(new Vector2d(3, 2), testVector1.upperRight(testVector2));
        assertEquals(new Vector2d(1, 0), testVector1.lowerLeft(testVector2));

        assertEquals(new Vector2d(3, 2), testVector2.upperRight(testVector1));
        assertEquals(new Vector2d(1, 0), testVector2.lowerLeft(testVector1));
    }

    @Test
    public void basicOperationsGiveCorrectAnswer() {
        Vector2d testVector1 = new Vector2d(1, 2);
        Vector2d testVector2 = new Vector2d(3, 0);

        assertEquals(new Vector2d(4, 2), testVector1.add(testVector2));
        assertEquals(new Vector2d(-2, 2), testVector1.subtract(testVector2));
        assertEquals(new Vector2d(4, 2), testVector2.add(testVector1));
        assertEquals(new Vector2d(2, -2), testVector2.subtract(testVector1));
    }

    @Test
    public void returnsCorrectOppositeVector() {
        Vector2d testVector0 = new Vector2d(0, 0);
        Vector2d testVector1 = new Vector2d(1, -1);
        Vector2d testVector2 = new Vector2d(1, 1);

        assertEquals(new Vector2d(0, 0), testVector0.opposite());
        assertEquals(new Vector2d(-1, 1), testVector1.opposite());
        assertEquals(new Vector2d(-1, -1), testVector2.opposite());
    }
}