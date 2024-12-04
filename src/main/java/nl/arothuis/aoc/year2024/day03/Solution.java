package nl.arothuis.aoc.year2024.day03;

import java.util.regex.Pattern;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        var muls = Pattern
            .compile("mul\\(\\d+,\\d+\\)")
            .matcher(input);
        
        var result = 0L;
        while (muls.find()) {
            var digits = Pattern
                .compile("\\d+")
                .matcher(muls.group());

            var value = 1L;
            while (digits.find()) {
                value *= Long.valueOf(digits.group());
            }

            result += value;
        }

        return result;
    }

    @Override
    public Long solveB(String input) {
        return solveA(Pattern
            .compile("don't\\(\\)[^d]*(?:do\\(\\))?", Pattern.DOTALL)
            .matcher(input)
            .replaceAll("_")
        );
    }
}