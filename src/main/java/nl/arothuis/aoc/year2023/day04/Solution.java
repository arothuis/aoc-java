package nl.arothuis.aoc.year2023.day04;

import nl.arothuis.aoc.core.PuzzleSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        return input.lines()
                .map(ScratchCard::fromString)
                .mapToInt(ScratchCard::calculateValue)
                .sum();
    }

    @Override
    public Integer solveB(String input) {
        Map<Integer, Set<Integer>> winningCards = input.lines()
                .map(ScratchCard::fromString)
                .collect(Collectors.groupingBy(
                        ScratchCard::getId,
                        Collectors.flatMapping(card -> card.streamWonCards(input.split("\n").length), Collectors.toSet())
                ));

        List<Integer> wonCards = new ArrayList<>();
        winningCards.values().forEach(wonCards::addAll);

        for (int key : winningCards.keySet()) {
            for (int i = 0; i < wonCards.size(); i++) {
                if (wonCards.get(i) == key) {
                    wonCards.addAll(winningCards.getOrDefault(key, Set.of()));
                }
            }
        }

        return wonCards.size() + winningCards.size();
    }
}