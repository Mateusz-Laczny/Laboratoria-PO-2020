package agh.cs.lab3;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTests {
    @Test
    void scenarioOne() {
        /*
            Używamy metody parse z klasy OptionsParser
            Tworzymy nowy obiekt. Idziemy w prawo do granicy i próbujemy ją przekroczyć, potem skręcamy w lewo i idziemy
            do rogu kwadratu (4, 4) i próbujem iść dalej do góry, potem idziemy w dół i kończymy na pozycji (4, 3).
         */
        Animal testAnimal = new Animal();
        assertEquals(MapDirection.NORTH, testAnimal.getOrientation());
        assertEquals(new Vector2d(2, 2), testAnimal.getPosition());

        String[] testDirectionString = {"r", "f", "forward", "forward", "l", "f", "f", "forward", "backward", "b"};

        List<MoveDirection> directionList = OptionsParser.parse(testDirectionString);

        // Lists with correct values
        List<MoveDirection> correctList = List.of(MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD);
        List<Vector2d> correctPositions = List.of(new Vector2d(2,2), new Vector2d(3,2),
                new Vector2d(4, 2), new Vector2d(4, 2), new Vector2d(4, 2),
                new Vector2d(4, 3), new Vector2d(4, 4), new Vector2d(4, 4),
                new Vector2d(4, 3), new Vector2d(4, 2));
        List<MapDirection> correctOrientation = List.of(MapDirection.EAST, MapDirection.EAST, MapDirection.EAST,
                MapDirection.EAST, MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH,
                MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH);

        int positionIndex = 0;
        int orientationIndex = 0;

        assertEquals(correctList, directionList);

        for(MoveDirection direction : directionList) {
            testAnimal.move(direction);
            assertEquals(correctPositions.get(positionIndex), testAnimal.getPosition());
            assertEquals(correctOrientation.get(orientationIndex), testAnimal.getOrientation());

            positionIndex += 1;
            orientationIndex += 1;
        }

        assertEquals("orientation=Północ, position=(4, 2)", testAnimal.toString());
    }

    @Test
    void scenarioTwo() {
        /*
        Korzystamy z metody parser w klasie OptionsParser żeby zamienić listę napisów na listę kierunków typu
        MoveDirection, potem wywołujemy move dla każdego elementu listy, za każdym razem sprawdzając pozycję i
        orientację
         */
        Animal testAnimal = new Animal();
        assertEquals(MapDirection.NORTH, testAnimal.getOrientation());
        assertEquals(new Vector2d(2, 2), testAnimal.getPosition());

        String[] testDirectionString = {"f", "forward", "f", "backward", "right", "l", "b", "backward", "b", "right",
                "left", "f"};

        List<MoveDirection> directionList = OptionsParser.parse(testDirectionString);

        // Lists of correct values
        List<MoveDirection> correctList = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD,
                MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT,
                MoveDirection.FORWARD);
        List<Vector2d> correctPositions = List.of(new Vector2d(2,3), new Vector2d(2,4),
                new Vector2d(2, 4), new Vector2d(2, 3), new Vector2d(2, 3),
                new Vector2d(2, 3), new Vector2d(2, 2), new Vector2d(2, 1),
                new Vector2d(2, 0), new Vector2d(2, 0), new Vector2d(2, 0),
                new Vector2d(2, 1));
        List<MapDirection> correctOrientation = List.of(MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH,
                MapDirection.NORTH, MapDirection.EAST, MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH,
                MapDirection.NORTH, MapDirection.EAST, MapDirection.NORTH, MapDirection.NORTH);

        assertEquals(correctList, directionList);

        int positionIndex = 0;
        int orientationIndex = 0;

        assertEquals(correctList, directionList);

        for(MoveDirection direction : directionList) {
            testAnimal.move(direction);
            assertEquals(correctPositions.get(positionIndex), testAnimal.getPosition());
            assertEquals(correctOrientation.get(orientationIndex), testAnimal.getOrientation());

            positionIndex += 1;
            orientationIndex += 1;
        }

        assertEquals("orientation=Północ, position=(2, 1)", testAnimal.toString());
    }
}
