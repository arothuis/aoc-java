package nl.arothuis.aoc.year2023.day16;

import nl.arothuis.aoc.core.Coordinates;

import java.util.*;
import java.util.stream.Collectors;

public class Contraption {
    private final Map<Coordinates, String> grid = new HashMap<>();
    private final Set<Beam> energizedTiles = new LinkedHashSet<>();
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
                contraption.grid.put(coords, tiles[x]);
            }
        }

        contraption.size = new Coordinates(x, y);

        return contraption;
    }

    public int maximizeLight() {
        List<Beam> origins = new ArrayList<>();

        for (var x = 0; x < size.x(); x++) {
            origins.add(Beam.southFrom(x, 0));
            origins.add(Beam.northFrom(x, size.y() - 1));
        }

        for (var y = 0; y < size.y(); y++) {
            origins.add(Beam.eastFrom(0, y));
            origins.add(Beam.westFrom(size.x() - 1, y));
        }

        int max = 0;

        for (var origin : origins) {
            energizedTiles.clear();
            int tileCount = shineLight(origin).size();
            max = Math.max(max, tileCount);
        }

        return max;
    }

    public Set<Coordinates> shineLight(Beam beam) {
        while (beam.fitsIn(size) && !energizedTiles.contains(beam)) {
            energizedTiles.add(beam);

            if (grid.containsKey(beam.position())) {
                List<Beam> beams = beam.changeDirection(grid.get(beam.position()));
                beam = beams.get(0);

                if (beams.size() == 2) {
                    shineLight(beams.get(1).next());
                }
            }

            beam = beam.next();
        }

        return energizedTiles.stream().map(Beam::position).collect(Collectors.toSet());
    }
}
