package nl.arothuis.aoc.year2023.day16;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        return Contraption.fromString(input).shineLight(Beam.eastFrom(0, 0)).size();
    }

    @Override
    public Integer solveB(String input) {
        return Contraption.fromString(input).maximizeLight();
    }
}