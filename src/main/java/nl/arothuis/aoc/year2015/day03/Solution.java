package nl.arothuis.aoc.year2015.day03;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        var santa = new Santa();

        for (String step: input.split("")) {
            santa.move(step);
        }

        return santa.getUniqueVisitedHouses().size();
    }

    @Override
    public Integer solveB(String input) {
        var santa = new Santa();
        var robot = new Santa();
        var active = santa;

        for (String step: input.split("")) {
            active.move(step);
            active = active.equals(santa) ? robot : santa;
        }

        var visited = santa.getUniqueVisitedHouses();
        visited.addAll(robot.getUniqueVisitedHouses());

        return visited.size();
    }
}