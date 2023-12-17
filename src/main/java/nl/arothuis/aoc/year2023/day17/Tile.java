package nl.arothuis.aoc.year2023.day17;

import nl.arothuis.aoc.core.Coordinates;

public record Tile(Coordinates position, Coordinates direction, int steps) {
    public static Tile of(int x, int y, int dx, int dy, int steps) {
        return new Tile(new Coordinates(x, y), new Coordinates(dx, dy), steps);
    }
}
