package nl.arothuis.aoc.year2023.day03;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        return Schematic.fromString(input).sumConnectedEnginePartNumbers();
    }

    @Override
    public Integer solveB(String input) {
        return Schematic.fromString(input).sumGearRatios();
    }
}