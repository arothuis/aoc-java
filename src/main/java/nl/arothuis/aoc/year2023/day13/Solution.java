package nl.arothuis.aoc.year2023.day13;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return ValleyOfMirrors.fromString(input).sumOfAllMirrors();
    }

    @Override
    public Long solveB(String input) {
        return -1L;
    }
}