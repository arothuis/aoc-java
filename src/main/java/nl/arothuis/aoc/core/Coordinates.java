package nl.arothuis.aoc.core;

public record Coordinates(int x, int y) {
    public static Coordinates northwards() {
        return new Coordinates(0, -1);
    }

    public static Coordinates eastwards() {
        return new Coordinates(1, 0);
    }

    public static Coordinates southwards() {
        return new Coordinates(0, 1);
    }

    public static Coordinates westwards() {
        return new Coordinates(-1, 0);
    }

    public Coordinates subtract(Coordinates other) {
        return new Coordinates(x - other.x, y - other.y);
    }

    public Coordinates add(Coordinates other) {
        return new Coordinates(x + other.x, y + other.y);
    }

    public boolean isWithin(Coordinates start, Coordinates endExclusive) {
        return x >= start.x && y >= start.y && x < endExclusive.x && y < endExclusive.y;
    }

    public Coordinates inverse() {
        return new Coordinates(-x, -y);
    }
}
