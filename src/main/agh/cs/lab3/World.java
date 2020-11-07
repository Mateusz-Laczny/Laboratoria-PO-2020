package agh.cs.lab3;

import agh.cs.Lab5.GrassField;
import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;

import java.util.List;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> directions = OptionsParser.parse(args);
        IWorldMap map = new RectangularMap(4, 5);
        map.place(new Animal(map));
        map.place(new Animal(map, new Vector2d(2,4)));
        map.run(directions);

        System.out.println(map);
    }
}
