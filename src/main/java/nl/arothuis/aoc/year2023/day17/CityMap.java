package nl.arothuis.aoc.year2023.day17;

import nl.arothuis.aoc.core.Coordinates;

import java.util.*;

import static nl.arothuis.aoc.core.Coordinates.*;

public class CityMap {
    private final Map<Coordinates, Integer> locations = new HashMap<>();
    private Coordinates size;

    public static CityMap fromString(String input) {
        CityMap map = new CityMap();

        String[] rows = input.split("\n");
        int y;
        int x = 0;
        for (y = 0; y < rows.length; y++) {
            String[] cols = rows[y].split("");
            for (x = 0; x < cols.length; x++) {
                var coords = new Coordinates(x, y);
                map.locations.put(coords, Integer.parseInt(cols[x]));
            }
        }

        map.size = new Coordinates(x, y);

        return map;
    }

    public int shortestDistance() {
        Coordinates end = size.subtract(new Coordinates(1, 1));
        Set<Tile> seenTiles = new HashSet<>();
        Queue<Step> options = new PriorityQueue<>();

        Step current = new Step(0, Tile.of(0, 0, 0, 0, 0));
        options.add(current);

        while (!current.hasReached(end)) {
            current = options.poll();

            if (seenTiles.contains(current.tile())) {
                continue;
            }

            seenTiles.add(current.tile());

            for (var direction : List.of(northwards(), eastwards(), southwards(), westwards())) {
                if (direction.equals(current.tile().direction().inverse())) {
                    continue;
                }

                Tile nextTile = current.goTowards(direction);
                if (!locations.containsKey(nextTile.position())) {
                    continue;
                }

                if (nextTile.steps() > 3) {
                    continue;
                }

                int nextLoss = current.heatLoss() + locations.get(nextTile.position());
                options.add(new Step(nextLoss, nextTile));
            }
        }

        return current.heatLoss();
    }
}
