package nl.arothuis.aoc.year2023.day14;

import nl.arothuis.aoc.core.Coordinates;
import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return RockPositions.fromString(input).tilt(new Coordinates(0, -1)).countLoadOnNorthernBeams();
    }

    @Override
    public Long solveB(String input) {
        return -1L;
    }
}