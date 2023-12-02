package nl.arothuis.aoc.year2023.day02;

import nl.arothuis.aoc.core.PuzzleSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        return Arrays.stream(input.split("\n"))
                .map(this::parseGame)
                .filter(game -> game.bags().stream().allMatch(bag -> bag.fitsIn(new Bag(12, 13, 14))))
                .mapToInt(Game::id)
                .sum();
    }

    @Override
    public Integer solveB(String input) {
        return Arrays.stream(input.split("\n"))
                .map(this::parseGame)
                .mapToInt(this::calculatePowerOfCubeSet)
                .sum();
    }

    private Game parseGame(String line) {
        var game = line.split(": ");
        int id = Integer.parseInt(game[0].substring(5));

        List<Bag> bagReveals = new ArrayList<>();
        for (String seen : game[1].split("; ")) {
            Bag bagReveal = new Bag();

            for (String cube : seen.split(", ")) {
                var cubeCount = cube.split(" ");
                bagReveal.addCubeCount(cubeCount[1].trim(), Integer.parseInt(cubeCount[0]));
            }

            bagReveals.add(bagReveal);
        }

        return new Game(id, bagReveals);
    }

    private int calculatePowerOfCubeSet(Game game) {
        return game.bags().stream()
                .reduce(new Bag(), Bag::maxCubes)
                .power();
    }
}