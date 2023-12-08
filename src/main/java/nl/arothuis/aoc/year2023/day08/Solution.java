package nl.arothuis.aoc.year2023.day08;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        var roadmap = Roadmap.fromString(input);
        var journey = roadmap.traverse("AAA", "ZZZ");

        return journey.getStepsTaken();
    }

    @Override
    public Integer solveB(String input) {
        return 1;
    }
}