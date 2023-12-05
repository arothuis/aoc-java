package nl.arothuis.aoc.year2023.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Almanac {
    private final List<Long> seedNumbers = new ArrayList<>();
    private final List<Phase> phases = new ArrayList<>();

    public static Almanac fromString(String string) {
        var almanac = new Almanac();

        String[] maps = string.split("\n\n");
        for (String seed : maps[0].substring(7).split(" ")) {
            almanac.seedNumbers.add(Long.parseLong(seed));
        }

        for (String map : Arrays.copyOfRange(maps, 1, maps.length)) {
            Phase phase = new Phase();
            String[] lines = map.split("\n");

            for (String line : Arrays.copyOfRange(lines, 1, lines.length)) {
                String[] numbers = line.split(" ");
                long destination = Long.parseLong(numbers[0]);
                long source = Long.parseLong(numbers[1]);
                long size = Long.parseLong(numbers[2]);

                phase.add(new Mapping(
                        new Range(source, source + size),
                        new Range(destination, destination + size - 1)
                ));
            }

            almanac.phases.add(phase);
        }

        return almanac;
    }

    public Long getLowestLocation() {
        List<Long> results = new ArrayList<>();

        for (long seedNumber : seedNumbers) {
            long current = seedNumber;

            for (Phase phase : phases) {
                current = phase.process(current);
            }

            results.add(current);
        }

        return results.stream().mapToLong(Long::longValue).min().orElse(-1);
    }

    public Long getLowestLocationFromRanges() {
        List<Range> currentRanges = new ArrayList<>();
        for (int i = 0; i < seedNumbers.size(); i += 2) {
            currentRanges.add(new Range(seedNumbers.get(i), seedNumbers.get(i) + seedNumbers.get(i + 1) - 1));
        }

        for (Phase phase : phases) {
            List<Range> nextRanges = new ArrayList<>();

            for (Range range : currentRanges) {
                nextRanges.addAll(phase.process(range));
            }

            currentRanges = nextRanges;
        }

        return currentRanges.stream().mapToLong(Range::start).min().orElse(-1);
    }
}
