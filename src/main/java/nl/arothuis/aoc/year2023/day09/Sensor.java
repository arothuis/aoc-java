package nl.arothuis.aoc.year2023.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Sensor {
    private final List<List<Long>> histories = new ArrayList<>();

    public Sensor(String input) {
        input.lines()
                .map(line -> Arrays.stream(line.split(" ")).map(Long::parseLong).toList())
                .forEach(histories::add);
    }

    public LongStream allPredictions() {
        return histories.stream().mapToLong(history -> extrapolate(history, true));
    }

    public LongStream allPrecursors() {
        return histories.stream().mapToLong(history -> extrapolate(history, false));
    }

    private Long extrapolate(List<Long> history, boolean future) {
        List<List<Long>> layers = calculateChangesOverTime(history);

        long change = 0;
        for (List<Long> layer : layers) {
            if (future) {
                change = layer.get(layer.size() - 1) + change;
                layer.add(change);
                continue;
            }

            change = layer.get(0) - change;
            layer.add(0, change);
        }

        List<Long> extrapolations = layers.get(layers.size() - 1);

        return extrapolations.get(future ? extrapolations.size() - 1 : 0);
    }

    private List<List<Long>> calculateChangesOverTime(List<Long> history) {
        List<List<Long>> layers = new ArrayList<>();
        layers.add(new ArrayList<>(history));

        List<Long> currentLayer = history;
        while (!currentLayer.stream().allMatch(n -> n == 0L)) {
            List<Long> nextLayer = findDifferences(currentLayer);
            layers.add(0, nextLayer);
            currentLayer = nextLayer;
        }

        return layers;
    }

    private List<Long> findDifferences(List<Long> xs) {
        List<Long> results = new ArrayList<>();

        for (int i = 1; i < xs.size(); i++) {
            results.add(xs.get(i) - xs.get(i - 1));
        }

        return results;
    }
}