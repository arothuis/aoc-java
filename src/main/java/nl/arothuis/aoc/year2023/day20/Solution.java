package nl.arothuis.aoc.year2023.day20;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return ModuleConfiguration.fromString(input).pressButton(1000L);
    }

    @Override
    public Long solveB(String input) {
        return ModuleConfiguration.fromString(input).pressButtonUntilRx();
    }
}