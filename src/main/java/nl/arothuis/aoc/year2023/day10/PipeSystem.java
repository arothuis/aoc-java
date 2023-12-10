package nl.arothuis.aoc.year2023.day10;

import nl.arothuis.aoc.core.Coordinates;

import java.util.*;
import java.util.stream.Stream;

public class PipeSystem {
    private Coordinates start;
    private final Map<Coordinates, Tile> tileMap = new HashMap<>();

    public static PipeSystem fromString(String input) {
        var system = new PipeSystem();

        var lines = input.split("\n");
        int y = 0;
        int x = 0;
        for (y = 0; y < lines.length; y++) {
            var line = lines[y].split("");

            for (x = 0; x < line.length; x++) {
                Coordinates coords = new Coordinates(x, y);
                system.tileMap.put(coords, new Tile(coords, line[x]));

                if (line[x].equals("S")) {
                    system.start = coords;
                }
            }
        }

        return system;
    }

    public LinkedHashSet<Tile> findLoop() {
        Tile current = new Tile(start, "S");
        LinkedHashSet<Tile> seen = new LinkedHashSet<>();

        while (!current.isHole()) {
            seen.add(current);
            current = findConnection(current.coordinates(), seen);
        }

        return seen;
    }

    public int countEnclosedTiles() {
        List<Tile> loop = findLoop().stream().toList();

        // Shoelace theorem: area of a simple polygon given the coordinates of its vertices
        // (https://artofproblemsolving.com/wiki/index.php/Shoelace_Theorem)
        //
        // area = 1/2 * | (x_1y_2 + x_2y_2 + ... x_ny_1) - (y1_x2 + y2_x3 + ... + y_nx_1) |
        //
        int area = 0;

        for (int i = 0; i < loop.size(); i++) {
            Tile current = loop.get(i);
            Tile next = loop.get((i + 1) % loop.size());

            area += (current.coordinates().x() * next.coordinates().y()
                    - current.coordinates().y() * next.coordinates().x());
        }

        area = Math.abs(area) / 2;

        // Pick's theorem: area of a simple polygon by the number of integer points within it and on its boundary
        // (https://artofproblemsolving.com/wiki/index.php/Pick%27s_Theorem)
        //
        // area = interior_points + boundary_points / 2 - 1
        //
        // Rearranged: interior_points = area - boundary_points / 2 + 1
        return area - loop.size() / 2 + 1;
    }

    private Tile findConnection(Coordinates coords, Set<Tile> seen) {
        int x = coords.x();
        int y = coords.y();

        return Stream.of(getTile(x, y + 1), getTile(x + 1, y), getTile(x, y - 1), getTile(x - 1, y))
                .filter(other -> !seen.contains(other) && getTile(x, y).canConnectTo(other))
                .findFirst()
                .orElse(new Tile(new Coordinates(x, y), "."));
    }

    private Tile getTile(int x, int y) {
        return tileMap.getOrDefault(new Coordinates(x, y), new Tile(new Coordinates(x, y), "."));
    }
}
