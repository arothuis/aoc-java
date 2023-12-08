package nl.arothuis.aoc.year2023.day08;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return Roadmap.fromString(input).traverse("AAA", n -> n.equals("ZZZ"));
    }

    @Override
    public Long solveB(String input) {
        return Roadmap.fromString(input).flood();
    }
}