package nl.arothuis.aoc.year2023.day10;

import nl.arothuis.aoc.core.Coordinates;

import java.util.*;
import java.util.stream.Stream;

public class PipeSystem {
    private Coordinates start;
    private Map<Coordinates, Pipe> pipeMap = new HashMap<>();

    public static PipeSystem fromString(String input) {
        var system = new PipeSystem();

        var lines = input.split("\n");
        for (int y = 0; y < lines.length; y++) {
            var line = lines[y].split("");

            for (int x = 0; x < line.length; x++) {
                Coordinates coords = new Coordinates(x, y);
                system.pipeMap.put(coords, new Pipe(coords, line[x]));

                if (line[x].equals("S")) {
                    system.start = coords;
                }
            }
        }

        return system;
    }

    public LinkedHashSet<Pipe> findLoop() {
        Pipe current = new Pipe(start, "S");
        LinkedHashSet<Pipe> seen = new LinkedHashSet<>();

        while (!Objects.equals(current, Pipe.none())) {
            seen.add(current);
            current = findConnection(current.coordinates(), seen);
        }

        return seen;
    }

    private Pipe findConnection(Coordinates coords, Set<Pipe> seen) {
        int x = coords.x();
        int y = coords.y();

        return Stream.of(getPipe(x, y + 1), getPipe(x + 1, y), getPipe(x, y - 1), getPipe(x - 1, y))
                .filter(other -> !seen.contains(other) && other.canConnectTo(getPipe(x, y)))
                .findFirst()
                .orElse(Pipe.none());
    }

    private Pipe getPipe(int x, int y) {
        return pipeMap.getOrDefault(new Coordinates(x, y), Pipe.none(new Coordinates(x, y)));
    }
}
