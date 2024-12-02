package nl.arothuis.aoc.year2024.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

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
        int sign = 0;

        for (int i = 1; i < report.size(); i++) {
            int diff = report.get(i) - report.get(i - 1);
            int nextSign = Integer.signum(diff);
            int absDiff = Math.abs(diff);
            
            if (sign != 0 && sign != nextSign) {
                return false;
            }

            if (absDiff < 1 || absDiff > 3) {
                return false;
            }

            sign = nextSign;
        }

        return true;
    }
    
    public boolean isSafeWithCorrection(List<Integer> report) {
        if (isSafe(report)) {
            return true;
        }

        // Optimize later ;)
        for (int i = 0; i < report.size(); i++) {
            List<Integer> corrected = new ArrayList<>(report);
            corrected.remove(i);

            if (isSafe(corrected)) {
                return true;
            }
        }

        return false;
    }
}