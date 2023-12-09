package nl.arothuis.aoc.year2023.day09;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return new Sensor(input).allPredictions().sum();
    }

    @Override
    public Long solveB(String input) {
        return new Sensor(input).allPrecursors().sum();
    }
}