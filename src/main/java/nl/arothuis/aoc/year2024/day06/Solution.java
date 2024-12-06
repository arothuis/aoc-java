package nl.arothuis.aoc.year2024.day06;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return Patrol.from(input)
            .determineRoute()
            .stream()
            .distinct()
            .count();
    }

    @Override
    public Long solveB(String input) {
        return (long) Patrol.from(input)
            .determineObstacles()
            .size();
        }
}