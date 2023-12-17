package nl.arothuis.aoc.year2023.day16;

import nl.arothuis.aoc.core.Coordinates;

import java.util.List;

public record Obstacle(Coordinates coords, String type) {
    public List<Coordinates> changeDirection(Coordinates direction) {
        return switch (type) {
            case "/" -> direction.x() > 0 ? List.of(new Coordinates(0, -1))
                    : direction.x() < 0 ? List.of(new Coordinates(0, 1))
                    : direction.y() > 0 ? List.of(new Coordinates(-1, 0))
                    : List.of(new Coordinates(1, 0));
            case "\\" -> direction.x() > 0 ? List.of(new Coordinates(0, 1))
                    : direction.x() < 0 ? List.of(new Coordinates(0, -1))
                    : direction.y() > 0 ? List.of(new Coordinates(1, 0))
                    : List.of(new Coordinates(-1, 0));
            case "|" -> direction.x() != 0 ? List.of(new Coordinates(0, -1), new Coordinates(0, 1))
                    : List.of(direction);
            default -> direction.y() != 0 ? List.of(new Coordinates(-1, 0), new Coordinates(1, 0))
                    : List.of(direction);
        };
    }
}
