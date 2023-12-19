package nl.arothuis.aoc.year2023.day19;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return RatingSystem.fromString(input).streamAcceptedParts().mapToLong(r -> r.x() + r.m() + r.a() + r.s()).sum();
    }

    @Override
    public Long solveB(String input) {
        return RatingSystem.fromString(input).countAcceptedRatings();
    }
}