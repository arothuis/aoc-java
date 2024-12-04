package nl.arothuis.aoc.year2024.day04;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return LetterGrid.from(input).countXmas();
    }

    @Override
    public Long solveB(String input) {
        return LetterGrid.from(input).countCrossMas();    
    }
}