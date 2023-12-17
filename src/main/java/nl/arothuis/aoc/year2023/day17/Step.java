package nl.arothuis.aoc.year2023.day17;

import nl.arothuis.aoc.core.Coordinates;

public record Step(Integer heatLoss, Tile tile) implements Comparable<Step> {
    boolean hasReached(Coordinates coords) {
        return tile.position().equals(coords);
    }

    public Tile goTowards(Coordinates direction) {
        int nextSteps = tile.direction().equals(direction) ? tile.steps() + 1 : 1;
        return new Tile(tile.position().add(direction), direction, nextSteps);
    }

    @Override
    public int compareTo(Step other) {
        return heatLoss.compareTo(other.heatLoss);
    }

    public boolean hasDirection(Coordinates otherDirection) {
        return tile.direction().equals(otherDirection);
    }

    public boolean isAllowedToGoTowards(Coordinates direction, int minSteps, int maxSteps) {
        return (hasDirection(direction) || hasDirection(Coordinates.origin()) || tile.steps() >= minSteps)
                && (!hasDirection(direction.inverse()))
                && !(hasDirection(direction) && tile.steps() + 1 > maxSteps);
    }
}
