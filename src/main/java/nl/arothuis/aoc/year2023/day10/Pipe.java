package nl.arothuis.aoc.year2023.day10;

import nl.arothuis.aoc.core.Coordinates;

import java.util.Set;

public record Pipe(Coordinates coordinates, String pipe) {
    public static Pipe none() {
        return new Pipe(new Coordinates(0, 0), ".");
    }

    public static Pipe none(Coordinates coords) {
        return new Pipe(coords, ".");
    }

    public boolean canConnectTo(Pipe other) {
        Coordinates direction = other.coordinates.subtract(coordinates);

        if (direction.y() != 0 && direction.x() != 0) {
            return false;
        }

        if (pipe.equals(".") || other.pipe.equals(".")) {
            return false;
        }

        if (direction.x() == 1) {
            return Set.of("-", "L", "F", "S").contains(pipe) && Set.of("-", "7", "J", "S").contains(other.pipe);
        }

        if (direction.y() == 1) {
            return Set.of("|", "7", "F", "S").contains(pipe) && Set.of("|", "L", "J", "S").contains(other.pipe);
        }

        return other.canConnectTo(this);
    }
}
