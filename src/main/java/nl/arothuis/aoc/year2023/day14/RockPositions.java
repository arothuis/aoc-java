package nl.arothuis.aoc.year2023.day14;

import nl.arothuis.aoc.core.Coordinates;

import java.util.HashSet;
import java.util.Set;

public class RockPositions {
    private final Set<Coordinates> cube = new HashSet<>();
    private final Set<Coordinates> round = new HashSet<>();
    private Coordinates size;

    public static RockPositions fromString(String input) {
        var rockPositions = new RockPositions();
        var rows = input.split("\n");

        int y;
        int x = 0;
        for (y = 0; y < rows.length; y++) {
            var tiles = rows[y].split("");

            for (x = 0; x < rows[y].length(); x++) {
                if (tiles[x].equals("O")) {
                    rockPositions.round.add(new Coordinates(x, y));
                } else if (tiles[x].equals("#")) {
                    rockPositions.cube.add(new Coordinates(x, y));
                }
            }
        }

        rockPositions.size = new Coordinates(x, y);

        return rockPositions;
    }

    public RockPositions tilt(Coordinates direction) {
        while (canRollAnyRock(direction)) {
            rollRocks(direction);
        }

        return this;
    }

    public long countLoadOnNorthernBeams() {
        return round.stream().mapToLong(rock -> size.subtract(rock).y()).sum();
    }

    private void rollRocks(Coordinates direction) {
        Set<Coordinates> roundView = new HashSet<>(round);

        for (var rock : roundView) {
            if (!canRoll(rock, direction)) {
                continue;
            }
            var next = rock.add(direction);

            round.remove(rock);
            round.add(next);
        }
    }

    private boolean canRollAnyRock(Coordinates direction) {
        return round.stream().anyMatch(rock -> canRoll(rock, direction));
    }

    private boolean canRoll(Coordinates rock, Coordinates direction) {
        return rock.add(direction).isWithin(new Coordinates(0, 0), size)
                && !round.contains(rock.add(direction))
                && !cube.contains(rock.add(direction));
    }
}
