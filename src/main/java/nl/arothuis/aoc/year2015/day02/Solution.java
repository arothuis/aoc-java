package nl.arothuis.aoc.year2015.day02;

import nl.arothuis.aoc.core.PuzzleSolution;

import java.util.Arrays;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        return Arrays.stream(input.split("\n"))
                .map(this::giftToDimensions)
                .mapToInt(dim ->
                        calculateSurfaceArea(dim[0], dim[1], dim[2]) + calculateSmallestArea(dim[0], dim[1], dim[2])
                )
                .sum();
    }

    @Override
    public Integer solveB(String input) {
        return Arrays.stream(input.split("\n"))
                .map(this::giftToDimensions)
                .mapToInt(dim ->
                        calculateSmallestPerimeter(dim[0], dim[1], dim[2]) + calculateVolume(dim[0], dim[1], dim[2])
                )
                .sum();
    }

    public int calculateSurfaceArea(int l, int w, int h) {
        return 2 * l * w + 2 * w * h + 2 * h * l;
    }

    public int calculateSmallestArea(int l, int w, int h) {
        return Math.min(Math.min(l * w, w * h), h * l);
    }

    public int calculateSmallestPerimeter(int l, int w, int h) {
        int[] args = new int[]{l, w, h};
        Arrays.sort(args);

        return args[0] * 2 + args[1] * 2;
    }
    public int calculateVolume(int l, int w, int h) {
        return l * w * h;
    }

    private int[] giftToDimensions(String gift) {
        return Arrays.stream(gift.split("x"))
                .mapToInt(arg -> Integer.parseInt(arg.trim()))
                .toArray();
    }
}