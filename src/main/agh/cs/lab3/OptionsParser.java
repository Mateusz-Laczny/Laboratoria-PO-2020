package agh.cs.lab3;

import agh.cs.lab2.MoveDirection;

import java.util.LinkedList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] stringDirectionsTable) {
        List<MoveDirection> directionList = new LinkedList<>();

        for(String stringDirection : stringDirectionsTable) {
            switch (stringDirection) {
                case "f", "forward" -> directionList.add(MoveDirection.FORWARD);
                case "b", "backward" -> directionList.add(MoveDirection.BACKWARD);
                case "r", "right" -> directionList.add(MoveDirection.RIGHT);
                case "l", "left" -> directionList.add(MoveDirection.LEFT);
            }
        }

        return directionList;
    }
}
