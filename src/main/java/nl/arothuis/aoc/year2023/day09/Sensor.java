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
        return histories.stream().mapToLong(this::extrapolatePrediction);
    }

    public LongStream allPrecursors() {
        return histories.stream().mapToLong(this::extrapolatePrecursor);
    }

    private Long extrapolatePrediction(List<Long> history) {
        List<List<Long>> layers = new ArrayList<>();
        layers.add(new ArrayList<>(history));

        List<Long> currentLayer = history;
        while (!currentLayer.stream().allMatch(n -> n == 0L)) {
            List<Long> nextLayer = findDifferences(currentLayer);
            layers.add(0, nextLayer);
            currentLayer = nextLayer;
        }

        long addition = 0;
        for (List<Long> layer : layers) {
            addition = layer.get(layer.size() - 1) + addition;
            layer.add(addition);
        }

        List<Long> prediction = layers.get(layers.size() - 1);

        return prediction.get(prediction.size() - 1);
    }

    private Long extrapolatePrecursor(List<Long> history) {
        List<List<Long>> layers = new ArrayList<>();
        layers.add(new ArrayList<>(history));

        List<Long> currentLayer = history;
        while (!currentLayer.stream().allMatch(n -> n == 0L)) {
            List<Long> nextLayer = findDifferences(currentLayer);
            layers.add(0, nextLayer);
            currentLayer = nextLayer;
        }

        long addition = 0;
        for (List<Long> layer : layers) {
            addition = layer.get(0) - addition;
            layer.add(0, addition);
        }

        List<Long> precursor = layers.get(layers.size() - 1);

        return precursor.get(0);
    }

    private List<Long> findDifferences(List<Long> xs) {
        List<Long> results = new ArrayList<>();

        for (int i = 1; i < xs.size(); i++) {
            results.add(xs.get(i) - xs.get(i - 1));
        }

        return results;
    }
}
