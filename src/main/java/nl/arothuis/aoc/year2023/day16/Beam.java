package nl.arothuis.aoc.year2023.day16;

import nl.arothuis.aoc.core.Coordinates;

import java.util.List;

public record Beam(Coordinates position, Coordinates direction) {
    public static Beam northFrom(int x, int y) {
        return new Beam(new Coordinates(x, y), new Coordinates(0, -1));
    }

    public static Beam southFrom(int x, int y) {
        return new Beam(new Coordinates(x, y), new Coordinates(0, 1));
    }

    public static Beam westFrom(int x, int y) {
        return new Beam(new Coordinates(x, y), new Coordinates(-1, 0));
    }

    public static Beam eastFrom(int x, int y) {
        return new Beam(new Coordinates(x, y), new Coordinates(1, 0));
    }

    public boolean fitsIn(Coordinates size) {
        return position.isWithin(new Coordinates(0, 0), size);
    }

    public Beam next() {
        return new Beam(position.add(direction), direction);
    }

    public List<Beam> changeDirection(String obstacle) {
        int x = position.x();
        int y = position.y();

        return switch (obstacle) {
            case "/" -> direction.x() > 0 ? List.of(Beam.northFrom(x, y))
                    : direction.x() < 0 ? List.of(Beam.southFrom(x, y))
                    : direction.y() > 0 ? List.of(Beam.westFrom(x, y))
                    : List.of(Beam.eastFrom(x, y));
            case "\\" -> direction.x() > 0 ? List.of(Beam.southFrom(x, y))
                    : direction.x() < 0 ? List.of(Beam.northFrom(x, y))
                    : direction.y() > 0 ? List.of(Beam.eastFrom(x, y))
                    : List.of(Beam.westFrom(x, y));
            case "|" -> direction.x() != 0 ? List.of(Beam.northFrom(x, y), Beam.southFrom(x, y))
                    : List.of(this);
            default -> direction.y() != 0 ? List.of(Beam.westFrom(x, y), Beam.eastFrom(x, y))
                    : List.of(this);
        };
    }
}
