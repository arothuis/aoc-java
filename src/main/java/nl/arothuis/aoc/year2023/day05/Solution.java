package nl.arothuis.aoc.year2023.day05;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return Almanac.fromString(input).getLowestLocation();
    }

    @Override
    public Long solveB(String input) {
        return Almanac.fromString(input).getLowestLocationFromRanges();
    }
}