package nl.arothuis.aoc.year2023.day07;

import nl.arothuis.aoc.core.PuzzleSolution;

import java.util.List;
import java.util.stream.IntStream;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        List<Hand> hands = input.lines()
                .map(hand -> Hand.fromString(hand, Card::fromString))
                .sorted()
                .toList();

        return IntStream.range(0, hands.size())
                .mapToLong(i -> hands.get(i).calculateScore(i + 1))
                .sum();
    }

    @Override
    public Long solveB(String input) {
        List<Hand> hands = input.lines()
                .map(hand -> Hand.fromString(hand, Card::fromStringWithJokers))
                .sorted()
                .toList();

        return IntStream.range(0, hands.size())
                .mapToLong(i -> hands.get(i).calculateScore(i + 1))
                .sum();
    }
}