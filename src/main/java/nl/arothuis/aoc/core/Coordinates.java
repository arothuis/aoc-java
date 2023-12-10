package nl.arothuis.aoc.core;

public record Coordinates(int x, int y) {
    public Coordinates subtract(Coordinates other) {
        return new Coordinates(x - other.x, y - other.y);
    }
}
