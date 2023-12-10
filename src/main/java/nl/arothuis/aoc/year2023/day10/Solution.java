package nl.arothuis.aoc.year2023.day10;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        var pipeLoop = PipeSystem.fromString(input).findLoop();
        return (int) Math.ceil(pipeLoop.size() / 2f);
    }

    @Override
    public Integer solveB(String input) {
        return PipeSystem.fromString(input).countEnclosedTiles();
    }
}