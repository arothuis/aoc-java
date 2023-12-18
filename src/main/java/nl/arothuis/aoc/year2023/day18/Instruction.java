package nl.arothuis.aoc.year2023.day18;

import nl.arothuis.aoc.core.Coordinates;

import java.util.List;

public record Instruction(String direction, int steps, String directionPart2, int stepsPart2) {
    private static final List<String> DIRECTIONS = List.of("R", "D", "L", "U");

    public static Instruction fromString(String input) {
        var segments = input.split(" ");

        var directionNumber = segments[2].substring(segments[2].length() - 2, segments[2].length() - 1);
        var directionPart2 = DIRECTIONS.get(Integer.parseInt(directionNumber));
        var stepsPart2 = Integer.parseInt(segments[2].substring(2, segments[2].length() - 2), 16);

        return new Instruction(segments[0], Integer.parseInt(segments[1]), directionPart2, stepsPart2);
    }

    public Coordinates calculateEnd1(Coordinates from) {
        return calculateEnd1(from, direction, steps);
    }

    public Coordinates calculateEnd2(Coordinates from) {
        return calculateEnd1(from, directionPart2, stepsPart2);
    }

    private Coordinates calculateEnd1(Coordinates from, String direction, int steps) {
        return switch (direction) {
            case "R" -> from.add(new Coordinates(steps, 0));
            case "L" -> from.add(new Coordinates(-steps, 0));
            case "D" -> from.add(new Coordinates(0, steps));
            default -> from.add(new Coordinates(0, -steps));
        };
    }
}
