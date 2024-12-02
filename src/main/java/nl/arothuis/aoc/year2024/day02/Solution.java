package nl.arothuis.aoc.year2024.day02;

import static java.lang.Integer.signum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        return solve(input, this::isSafe);
    }

    @Override
    public Long solveB(String input) {
        return solve(input, this::isSafeWithCorrection);
    }

    private long solve(String input, Predicate<List<Integer>> safetyChecker) {
        return Arrays.stream(input.split("\n"))
            .map(ln -> Arrays.stream(ln.split(" ")).map(n -> Integer.valueOf(n.trim())).toList())
            .filter(safetyChecker)
            .count();
    }

    private boolean isSafe(List<Integer> report) {
        return IntStream.range(1, report.size())
                .parallel()
                .allMatch(i -> {
                    int diff = report.get(i) - report.get(i - 1);

                    return Math.abs(diff) >= 1 && Math.abs(diff) <= 3 
                        && (i == 1 || signum(diff) == signum(report.get(i - 1) - report.get(i - 2)));
                });
    }
    
    public boolean isSafeWithCorrection(List<Integer> report) {
        // Optimize later ;)
        return isSafe(report) 
            || IntStream.range(0, report.size())
                .parallel()
                .anyMatch(i -> {
                    List<Integer> corrected = new ArrayList<>(report);
                    corrected.remove(i);
                    return isSafe(corrected);
                });
    }
}