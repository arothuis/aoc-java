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
}
