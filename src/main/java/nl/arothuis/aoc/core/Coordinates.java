package nl.arothuis.aoc.core;

public record Coordinates(int x, int y) {
    public Coordinates subtract(Coordinates other) {
        return new Coordinates(x - other.x, y - other.y);
    }

    public Coordinates add(Coordinates other) {
        return new Coordinates(x + other.x, y + other.y);
    }

    public boolean isWithin(Coordinates start, Coordinates end) {
        return x >= start.x && y >= start.y && x <= end.x && y <= end.y;
    }
}
