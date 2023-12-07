package nl.arothuis.aoc.year2023.day06;

import java.util.ArrayList;
import java.util.List;

public class RaceSheet {
    private final List<Race> races = new ArrayList<>();
    private Race actualRace;

    public static RaceSheet fromString(String input) {
        var sheet = new RaceSheet();

        var parts = input.split("\n");
        var times = parts[0].split("\\s+");
        var distances = parts[1].split("\\s+");

        StringBuilder actualTime = new StringBuilder();
        StringBuilder actualDistance = new StringBuilder();

        for (int i = 1; i < times.length; i++) {
            sheet.races.add(new Race(Long.parseLong(times[i]), Long.parseLong(distances[i])));
            actualTime.append(times[i]);
            actualDistance.append(distances[i]);
        }

        sheet.actualRace = new Race(Long.parseLong(actualTime.toString()), Long.parseLong(actualDistance.toString()));

        return sheet;
    }

    public long bruteforceAllOptions() {
        return races.parallelStream().mapToLong(this::bruteforceOptions).reduce(1, (a, b) -> a * b);
    }

    public long calculateActualRaceOptions() {
        return calculateOptions(actualRace);
    }

    public long calculateOptions(Race race) {
        long t = race.timeAllowed();
        long x = race.currentRecord();

        double D = Math.sqrt(Math.pow(-t, 2) - 4 * x);
        double h1 = Math.floor((t - D) / 2);
        double h2 = Math.ceil((t + D) / 2);

        return (long) (h2 - h1) - 1;
    }

    private long bruteforceOptions(Race race) {
        int options = 0;

        for (int h = 0; h < race.timeAllowed(); h++) {
            long d = calculateDistance(h, race.timeAllowed());
            if (d > race.currentRecord()) {
                options++;
            }
        }

        return options;
    }

    private long calculateDistance(long holdTime, long maxTime) {
        return holdTime * (maxTime - holdTime);
    }
}