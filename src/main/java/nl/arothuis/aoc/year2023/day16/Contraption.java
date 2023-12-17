package nl.arothuis.aoc.year2023.day16;

import nl.arothuis.aoc.core.Coordinates;
import nl.arothuis.aoc.core.Pair;

import java.util.*;

public class Contraption {
    private final Map<Coordinates, Obstacle> grid = new HashMap<>();
    private final Set<Pair<Coordinates, Coordinates>> seenBeams = new LinkedHashSet<>();
    private final Set<Coordinates> energized = new HashSet<>();
    private Coordinates size;

    public static Contraption fromString(String input) {
        Contraption contraption = new Contraption();
        String[] lines = input.split("\n");

        int y;
        int x = 0;
        for (y = 0; y < lines.length; y++) {
            String[] tiles = lines[y].split("");

            for (x = 0; x < tiles.length; x++) {
                if (tiles[x].equals(".")) {
                    continue;
                }

                Coordinates coords = new Coordinates(x, y);
                contraption.grid.put(coords, new Obstacle(coords, tiles[x]));
            }
        }

        contraption.size = new Coordinates(x, y);

        return contraption;
    }

    public int maximizeLight() {
        List<Pair<Coordinates, Coordinates>> startPositions = new ArrayList<>();

        for (var x = 0; x < size.x(); x++) {
            startPositions.add(new Pair<>(new Coordinates(x, 0), new Coordinates(0, 1)));
            startPositions.add(new Pair<>(new Coordinates(x, size.y() - 1), new Coordinates(0, -1)));
        }

        for (var y = 0; y < size.y(); y++) {
            startPositions.add(new Pair<>(new Coordinates(0, y), new Coordinates(1, 0)));
            startPositions.add(new Pair<>(new Coordinates(size.x() - 1, y), new Coordinates(-1, 0)));
        }

        int max = 0;

        for (var position : startPositions) {
            seenBeams.clear();
            energized.clear();

            int energizedTiles = shineLight(position.left(), position.right()).size();
            max = Math.max(max, energizedTiles);
        }

        return max;
    }

    public Set<Coordinates> shineLight() {
        return shineLight(new Coordinates(0, 0), new Coordinates(1, 0));
    }

    public Set<Coordinates> shineLight(Coordinates from, Coordinates direction) {
        Coordinates next = from;
        var beam = new Pair<>(next, direction);

        while (next.isWithin(new Coordinates(0, 0), size) && !seenBeams.contains(beam)) {
            seenBeams.add(beam);
            energized.add(next);

            if (grid.containsKey(next)) {
                List<Coordinates> directions = grid.get(next).changeDirection(direction);
                direction = directions.get(0);

                if (directions.size() == 2) {
                    shineLight(next, directions.get(1));
                }
            }

            next = next.add(direction);
            beam = new Pair<>(next, direction);
        }

        return energized;
    }
}
