package nl.arothuis.aoc.year2023.day11;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return GalaxyImage.fromString(input).sumExpandedDistances(2);
    }

    @Override
    public Long solveB(String input) {
        return GalaxyImage.fromString(input).sumExpandedDistances(1000000);
    }
}