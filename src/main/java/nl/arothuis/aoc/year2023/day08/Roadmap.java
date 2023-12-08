package nl.arothuis.aoc.year2023.day08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Roadmap {
    private Journey journey;
    private Map<String, List<String>> map;

    private Roadmap(Journey journey, Map<String, List<String>> map) {
        this.journey = journey;
        this.map = map;
    }

    public static Roadmap fromString(String input) {
        String[] parts = input.split("\n\n");

        var forks = new HashMap<String, List<String>>();
        for (var forkData : parts[1].split("\n")) {
            var from = forkData.substring(0, 3);
            var left = forkData.substring(7, 10);
            var right = forkData.substring(12, 15);

            forks.put(from, List.of(left, right));
        }

        return new Roadmap(new Journey(parts[0]), forks);
    }

    public Journey traverse(String start, String end) {
        String current = start;

        while (!current.equals(end)) {
            var step = journey.next();
            current = map.get(current).get(step);
        }

        return journey;
    }
}
