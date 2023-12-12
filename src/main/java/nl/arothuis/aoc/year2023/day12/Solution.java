package nl.arothuis.aoc.year2023.day12;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return SpringRecords.fromString(input).countAllPossibleArrangements();
    }

    @Override
    public Long solveB(String input) {
        return SpringRecords.fromString(input).countAllPossibleArrangementsUnfoldedBy(5);
    }
}