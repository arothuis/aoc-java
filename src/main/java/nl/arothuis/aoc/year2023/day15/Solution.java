package nl.arothuis.aoc.year2023.day15;

import nl.arothuis.aoc.core.PuzzleSolution;

import java.util.Arrays;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return Arrays.stream(input.split(",")).mapToLong(Hash::calculate).sum();
    }

    @Override
    public Long solveB(String input) {
        return -1L;
    }
}