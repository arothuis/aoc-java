package nl.arothuis.aoc.year2024.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.arothuis.aoc.core.Coordinates;

public class AntennaMap {
    private Map<String, List<Coordinates>> positions = new HashMap<>();
    private Coordinates size = new Coordinates(0, 0);

    private AntennaMap() {
    }

    public static AntennaMap from(String input) {
        AntennaMap map = new AntennaMap();

        int y = 0;
        int x = 0;

        for (String line : input.split("\n")) {
            x = 0;

            for (String cell : line.split("")) {
                if (!cell.equals(".")) {
                    List<Coordinates> antennae = map.positions.getOrDefault(cell, new ArrayList<>());
                    antennae.add(new Coordinates(x, y));
                    map.positions.put(cell, antennae);
                }
                x++;
            }

            y++;
        }

        map.size = new Coordinates(x, y);

        return map;
    }

    public Set<Coordinates> calculateAntinodes(
        BiFunction<List<Coordinates>, Coordinates, Stream<Coordinates>> fn
    ) {
        return positions.values()
            .parallelStream()
            .flatMap(antennae -> fn.apply(antennae, size))
            .collect(Collectors.toSet());
    }
}
