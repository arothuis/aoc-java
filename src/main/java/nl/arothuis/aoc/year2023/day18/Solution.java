package nl.arothuis.aoc.year2023.day18;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return DigPlan.fromString(input).calculateDigSite(Instruction::calculateEnd1);
    }

    @Override
    public Long solveB(String input) {
        return DigPlan.fromString(input).calculateDigSite(Instruction::calculateEnd2);
    }
}