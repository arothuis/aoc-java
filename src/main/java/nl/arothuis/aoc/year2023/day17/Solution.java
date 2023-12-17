package nl.arothuis.aoc.year2023.day17;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        return CityMap.fromString(input).shortestDistance(0, 3);
    }

    @Override
    public Integer solveB(String input) {
        return CityMap.fromString(input).shortestDistance(4, 10);
    }
}