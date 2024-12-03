package nl.arothuis.aoc.year2024.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        var mulPattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        var muls = mulPattern.matcher(input);
        var result = 0L;

        while (muls.find()) {
            var digitPattern = Pattern.compile("\\d+");
            var digits = digitPattern.matcher(muls.group());

            List<Long> values = new ArrayList<>();
            while (digits.find()) {
                values.add(Long.valueOf(digits.group()));
            }

            result += values.stream().reduce(1L, (Long a, Long b) -> a * b);
        }

        return result;
    }

    @Override
    public Long solveB(String input) {
        var removalPattern = Pattern.compile("don't\\(\\).+?do\\(\\)", Pattern.DOTALL);
        var removed = removalPattern.matcher(input).replaceAll("_");

        return solveA(removed);
    }
}