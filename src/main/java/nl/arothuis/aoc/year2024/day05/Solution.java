package nl.arothuis.aoc.year2024.day05;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return SafetyManual.from(input).calculateValidUpdates();
    }

    @Override
    public Long solveB(String input) {
        return SafetyManual.from(input).calculateCorrectedUpdates();
    }
}