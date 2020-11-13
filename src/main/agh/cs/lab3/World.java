package agh.cs.lab3;

import agh.cs.lab2.MoveDirection;
import agh.cs.lab2.Vector2d;
import agh.cs.lab4.IWorldMap;
import agh.cs.lab4.RectangularMap;

import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            List<MoveDirection> directions = OptionsParser.parse(args);
            IWorldMap map = new RectangularMap(4, 5);
            map.place(new Animal(map));
            map.place(new Animal(map, new Vector2d(1,3)));
            map.place(new Animal(map, new Vector2d(1, 3)));
            map.run(directions);

            System.out.println(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
