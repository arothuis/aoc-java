package nl.arothuis.aoc.year2023.day08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public record Roadmap(String route, Map<String, List<String>> map) {
    public static Roadmap fromString(String input) {
        String[] parts = input.split("\n\n");

        var map = new HashMap<String, List<String>>();
        for (var forkData : parts[1].split("\n")) {
            var from = forkData.substring(0, 3);
            var left = forkData.substring(7, 10);
            var right = forkData.substring(12, 15);

            map.put(from, List.of(left, right));
        }

        return new Roadmap(parts[0], map);
    }

    public long traverse(String start, Predicate<String> endCondition) {
        String current = start;
        Journey journey = new Journey(route);

        while (!endCondition.test(current)) {
            var step = journey.next();
            current = map.get(current).get(step);
        }

        return journey.getStepsTaken();
    }

    public long flood() {
        return map.keySet().stream()
                .filter(pos -> pos.endsWith("A"))
                .map(start -> traverse(start, pos -> pos.endsWith("Z")))
                .reduce(1L, (a, b) -> (a * b) / gcd(a, b));
    }

    public long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
