package nl.arothuis.aoc.year2023.day01;

import nl.arothuis.aoc.core.PuzzleSolution;

import java.util.Arrays;
import java.util.function.BiFunction;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        return Arrays.stream(input.split("\n"))
                .mapToInt(line -> parseNumberFromLine(line, this::digitToInt))
                .sum();
    }

    @Override
    public Integer solveB(String input) {
        return Arrays.stream(input.split("\n"))
                .mapToInt(line -> parseNumberFromLine(line, this::digitAndTextToInt))
                .sum();
    }

    private int parseNumberFromLine(String line, BiFunction<Character, String, Integer> numberParser) {
        int left = 0;
        int right = 0;
        int end = line.length() - 1;

        for (int i = 0; i < line.length(); i++) {
            if (left != 0 && right != 0) {
                break;
            }

            if (left == 0) {
                left = numberParser.apply(line.charAt(i), line.substring(0, i + 1)) * 10;
            }

            if (right == 0) {
                right = numberParser.apply(line.charAt(end - i), line.substring(end - i));
            }
        }

        return left + right;
    }

    private int digitToInt(char c, String _unused) {
        return Character.isDigit(c) ? Character.getNumericValue(c) : 0;
    }

    private int digitAndTextToInt(char c, String substring) {
        return Character.isDigit(c) ? Character.getNumericValue(c)
                : substring.contains("one") ? 1
                : substring.contains("two") ? 2
                : substring.contains("three") ? 3
                : substring.contains("four") ? 4
                : substring.contains("five") ? 5
                : substring.contains("six") ? 6
                : substring.contains("seven") ? 7
                : substring.contains("eight") ? 8
                : substring.contains("nine") ? 9
                : 0;
    }
}