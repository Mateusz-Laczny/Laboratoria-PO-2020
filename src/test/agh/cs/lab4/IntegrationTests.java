package agh.cs.lab4;

import agh.cs.lab2.MapDirection;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab3.Animal;
import agh.cs.lab3.OptionsParser;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTests {
    @Test
    void scenarioOne() {
        /*
            Używamy metody parse z klasy OptionsParser
            Tworzymy nowy obiekt. Idziemy w prawo do granicy i próbujemy ją przekroczyć, potem skręcamy w lewo i idziemy
            do rogu kwadratu (4, 4) i próbujem iść dalej do góry, potem idziemy w dół i kończymy na pozycji (4, 3).
         */
        RectangularMap map = new RectangularMap(4, 4);

        Animal testAnimal = new Animal(map);
        assertEquals(MapDirection.NORTH, testAnimal.getOrientation());
        assertEquals(new Vector2d(2, 2), testAnimal.getPosition());

        String[] testDirectionString = {"r", "f", "forward", "forward", "l", "f", "f", "forward", "backward", "b"};

        List<MoveDirection> directionList = OptionsParser.parse(testDirectionString);

        // Lists with correct values
        List<MoveDirection> correctList = List.of(MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD);

        List<Vector2d> correctPositions = List.of(new Vector2d(2,2),
                new Vector2d(3,2),
                new Vector2d(3, 2),
                new Vector2d(3, 2),
                new Vector2d(3, 2),
                new Vector2d(3, 3),
                new Vector2d(3, 3),
                new Vector2d(3, 3),
                new Vector2d(3, 2),
                new Vector2d(3, 1));

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

        assertEquals("^", testAnimal.toString());
    }

    @Test
    void scenarioTwo() {
        /*
        Korzystamy z metody parser w klasie OptionsParser żeby zamienić listę napisów na listę kierunków typu
        MoveDirection, potem wywołujemy move dla każdego elementu listy, za każdym razem sprawdzając pozycję i
        orientację
         */
        RectangularMap map = new RectangularMap(4, 4);

        Animal testAnimal = new Animal(map);
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

        List<Vector2d> correctPositions = List.of(new Vector2d(2,3),
                new Vector2d(2,3),
                new Vector2d(2, 3),
                new Vector2d(2, 2),
                new Vector2d(2, 2),
                new Vector2d(2, 2),
                new Vector2d(2, 1),
                new Vector2d(2, 0),
                new Vector2d(2, 0),
                new Vector2d(2, 0),
                new Vector2d(2, 0),
                new Vector2d(2, 1));

        List<MapDirection> correctOrientation = List.of(MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH,
                MapDirection.NORTH, MapDirection.EAST, MapDirection.NORTH, MapDirection.NORTH, MapDirection.NORTH,
                MapDirection.NORTH, MapDirection.EAST, MapDirection.NORTH, MapDirection.NORTH);

        assertEquals(correctList, directionList);

        int positionIndex = 0;
        int orientationIndex = 0;

        for(MoveDirection direction : directionList) {
            System.out.println(testAnimal.getPosition() + " " + testAnimal.getOrientation());
            testAnimal.move(direction);
            assertEquals(correctPositions.get(positionIndex), testAnimal.getPosition());
            assertEquals(correctOrientation.get(orientationIndex), testAnimal.getOrientation());

            positionIndex += 1;
            orientationIndex += 1;
        }

        assertEquals("^", testAnimal.toString());
    }

    @Test
    void scenarioThree() {
        /*
        Tworzymy mapę, 3 zwierzaki i tablicę komend. Za pomoca metody parse z klasy OptionsParser zamieniamy komendy
        z formy string na formę zdefiniowanego typu enum. Potem dodajemy zwierzaki do mapy (jeden z nich nie możemy
        bo miejsce jest zajęte przez innego zwierzaka) i wywołujemy metodę run. Na koniec sprawdzamy działanie metody
        to_string w klasie Animal
         */
        RectangularMap map = new RectangularMap(3, 3);

        Animal animal1 = new Animal(map, new Vector2d(1, 1));
        assertEquals(MapDirection.NORTH, animal1.getOrientation());
        assertEquals(new Vector2d(1, 1), animal1.getPosition());

        Animal animal2 = new Animal(map, new Vector2d(2, 1));
        assertEquals(MapDirection.NORTH, animal2.getOrientation());
        assertEquals(new Vector2d(2, 1), animal2.getPosition());

        Animal animal3 = new Animal(map);
        assertEquals(MapDirection.NORTH, animal3.getOrientation());
        assertEquals(new Vector2d(2, 2), animal3.getPosition());

        assertThrows(IllegalArgumentException.class, () -> new Animal(map));

        String[] testDirectionString = {"f", "forward", "f", "backward", "right", "l", "b", "right", "b", "right",
                "left", "b"};

        List<MoveDirection> directionList = OptionsParser.parse(testDirectionString);

        // Lists of correct values
        List<MoveDirection> correctList = List.of(MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.BACKWARD,
                MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT,
                MoveDirection.BACKWARD);

        assertEquals(correctList, directionList);

        map.run(directionList);

        // Animal 1 after run method call
        assertEquals(MapDirection.EAST, animal1.getOrientation());
        assertEquals(new Vector2d(1, 0), animal1.getPosition());

        // Animal 2 after run method call
        assertEquals(MapDirection.EAST, animal2.getOrientation());
        assertEquals(new Vector2d(2, 1), animal2.getPosition());

        // Animal 3 after run method call
        assertEquals(MapDirection.WEST, animal3.getOrientation());
        assertEquals(new Vector2d(2, 2), animal3.getPosition());

        assertEquals(">", animal1.toString());
        assertEquals(">", animal2.toString());
        assertEquals("<", animal3.toString());
    }

    @Test
    void scenarioFour() {
        /*
        Tworzymy mapę, 3 zwierzaki i tablicę komend. Za pomoca metody parse z klasy OptionsParser zamieniamy komendy
        z formy string na formę zdefiniowanego typu enum. Potem dodajemy zwierzaki do mapy (jeden z nich nie możemy
        bo miejsce jest zajęte przez innego zwierzaka) i wywołujemy metodę run. Na koniec sprawdzamy działanie metody
        to_string w klasie Animal i próbujemy pobrać zwierzaki z pozycji (2, 2),
         */
        RectangularMap map = new RectangularMap(4, 5);

        Animal animal1 = new Animal(map, new Vector2d(3, 4));
        assertEquals(MapDirection.NORTH, animal1.getOrientation());
        assertEquals(new Vector2d(3, 4), animal1.getPosition());

        Animal animal2 = new Animal(map, new Vector2d(1, 4));
        assertEquals(MapDirection.NORTH, animal2.getOrientation());
        assertEquals(new Vector2d(1, 4), animal2.getPosition());

        Animal animal3 = new Animal(map);
        assertEquals(MapDirection.NORTH, animal3.getOrientation());
        assertEquals(new Vector2d(2, 2), animal3.getPosition());


        assertThrows(IllegalArgumentException.class, () -> new Animal(map, new Vector2d(3, 4)));

        String[] testDirectionString = {"f", "r", "f", "backward", "right", "l", "f", "right", "b", "l",
                "left", "f", "f", "f", "f"};

        List<MoveDirection> directionList = OptionsParser.parse(testDirectionString);

        // Lists of correct values
        List<MoveDirection> correctList = List.of(MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.LEFT,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.FORWARD);

        assertEquals(correctList, directionList);

        map.run(directionList);

        // Animal 1 after run method call
        assertEquals(MapDirection.WEST, animal1.getOrientation());
        assertEquals(new Vector2d(2, 4), animal1.getPosition());

        // Animal 2 after run method call
        assertEquals(MapDirection.SOUTH, animal2.getOrientation());
        assertEquals(new Vector2d(1, 3), animal2.getPosition());

        // Animal 3 after run method call
        assertEquals(MapDirection.WEST, animal3.getOrientation());
        assertEquals(new Vector2d(2, 3), animal3.getPosition());

        assertEquals("<", animal1.toString());
        assertEquals("v", animal2.toString());
        assertEquals("<", animal3.toString());

        // ObjectAt method tests
        assertEquals(Optional.of(animal1), map.objectAt(new Vector2d(2, 4)));
        assertEquals(Optional.of(animal2), map.objectAt(new Vector2d(1, 3)));
        assertEquals(Optional.of(animal3), map.objectAt(new Vector2d(2, 3)));
        assertEquals(Optional.empty(), map.objectAt(new Vector2d(1, 1)));
        assertEquals(Optional.empty(), map.objectAt(new Vector2d(4, 4)));
    }
}