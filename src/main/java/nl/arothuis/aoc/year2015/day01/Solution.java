package nl.arothuis.aoc.year2015.day01;

import nl.arothuis.aoc.core.PuzzleSolution;

import java.util.Arrays;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        return Arrays.stream(input.split(""))
                .map(this::convertToMove)
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public Integer solveB(String input) {
        int floor = 0;
        int steps = 0;

        for (String parenthesis : input.split("")) {
            if (floor == -1) {
                return steps;
            }

            floor += convertToMove(parenthesis);
            steps += 1;
        }

        throw new RuntimeException("Never reached the basement!");
    }

    private int convertToMove(String parenthesis) {
        return parenthesis.equals("(") ? 1 : -1;
    }
}