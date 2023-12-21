package nl.arothuis.aoc.year2023.day21;

import nl.arothuis.aoc.core.Coordinates;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static nl.arothuis.aoc.core.Coordinates.*;

public class Garden {
    private final Set<Coordinates> rocks = new HashSet<>();
    private Coordinates start;
    private Coordinates size;

    public static Garden fromString(String input) {
        var garden = new Garden();

        int y;
        int x = 0;
        var lines = input.split("\n");

        for (y = 0; y < lines.length; y++) {
            var row = lines[y].split("");
            for (x = 0; x < row.length; x++) {
                if (row[x].equals("#")) {
                    garden.rocks.add(new Coordinates(x, y));
                } else if (row[x].equals("S")) {
                    garden.start = new Coordinates(x, y);
                }
            }
        }

        garden.size = new Coordinates(x, y);

        return garden;
    }

    public long countReachablePlots(long maxSteps) {
        long step = 0;
        Set<Coordinates> currentSteps = new HashSet<>();
        currentSteps.add(start);

        while (step < maxSteps) {
            Set<Coordinates> nextSteps = new HashSet<>();

            for (Coordinates currentStep : currentSteps) {
                Set<Coordinates> options = Stream.of(northwards(), eastwards(), southwards(), westwards())
                        .map(currentStep::add)
                        .filter(direction -> !rocks.contains(direction))
                        .collect(Collectors.toSet());
                nextSteps.addAll(options);
            }

            currentSteps = nextSteps;
            step++;
        }

        return currentSteps.size();
    }
}
