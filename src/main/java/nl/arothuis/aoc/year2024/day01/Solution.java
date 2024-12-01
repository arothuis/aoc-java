package nl.arothuis.aoc.year2024.day01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Streams;

import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        List<List<Long>> lists = parseLists(input);

        return Streams
            .zip(
                lists.get(0).stream().sorted(), 
                lists.get(1).stream().sorted(),
                (l, r) -> Math.abs(r - l)
            )
            .reduce(Long::sum)
            .orElse(0L);
    }

    @Override
    public Long solveB(String input) {
        List<List<Long>> lists = parseLists(input);

        Map<Long, Long> occurence = lists
            .get(1)
            .stream()
            .collect(Collectors.groupingBy(
                Long::longValue,
                Collectors.counting()
            ));

        return lists
            .get(0)
            .stream()
            .mapToLong(n -> n * occurence.getOrDefault(n, 0L))
            .sum();
    }

    private List<List<Long>> parseLists(String input) {
        List<Long> left = new ArrayList<>();
        List<Long> right = new ArrayList<>();
        
        String[] lines = input.split("\n");

        for (String line : lines) {
            String[] parts = line.trim().split("   ");
            
            left.add(Long.valueOf(parts[0]));
            right.add(Long.valueOf(parts[1]));
        }

        return List.of(left, right);
    }
}