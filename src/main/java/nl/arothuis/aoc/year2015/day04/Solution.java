package nl.arothuis.aoc.year2015.day04;

import com.google.common.hash.Hashing;
import nl.arothuis.aoc.core.PuzzleSolution;

import java.nio.charset.StandardCharsets;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        int i = 0;

        while (!md5(input, i).startsWith("00000")) {
            i++;
        }

        return i;
    }

    @Override
    public Integer solveB(String input) {
        int i = 0;

        while (!md5(input, i).startsWith("000000")) {
            i++;
        }

        return i;
    }

    private String md5(String prefix, int number) {
        return Hashing.md5()
                .hashString(String.format("%s%d", prefix, number), StandardCharsets.UTF_8)
                .toString();
    }
}