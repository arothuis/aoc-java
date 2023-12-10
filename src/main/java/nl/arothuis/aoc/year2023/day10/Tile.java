package nl.arothuis.aoc.year2023.day10;

import nl.arothuis.aoc.core.Coordinates;

import java.util.Set;

public record Tile(Coordinates coordinates, String value) {
    public boolean isHole() {
        return value.equals(".");
    }

    public boolean canConnectTo(Tile other) {
        Coordinates direction = other.coordinates.subtract(coordinates);

        if (direction.y() != 0 && direction.x() != 0) {
            return false;
        }

        if (value.equals(".") || other.value.equals(".")) {
            return false;
        }

        if (direction.x() == 1) {
            return Set.of("-", "L", "F", "S").contains(value) && Set.of("-", "7", "J", "S").contains(other.value);
        }

        if (direction.y() == 1) {
            return Set.of("|", "7", "F", "S").contains(value) && Set.of("|", "L", "J", "S").contains(other.value);
        }

        return other.canConnectTo(this);
    }
}
