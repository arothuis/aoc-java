package nl.arothuis.aoc.year2023.day21;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    public Long solveExampleA(String input) {
        return Garden.fromString(input).countReachablePlots(6);
    }

    @Override
    public Long solveA(String input) {
        return Garden.fromString(input).countReachablePlots(64);
    }

    @Override
    public Long solveB(String input) {
        return -1L;
    }
}