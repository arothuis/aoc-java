package nl.arothuis.aoc.year2023.day06;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return RaceSheet.fromString(input).bruteforceAllOptions();
    }

    @Override
    public Long solveB(String input) {
        return RaceSheet.fromString(input).calculateActualRaceOptions();
    }
}