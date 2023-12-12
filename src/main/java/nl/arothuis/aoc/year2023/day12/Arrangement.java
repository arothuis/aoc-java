package nl.arothuis.aoc.year2023.day12;

import java.util.*;

public class Arrangement {
    private String original;
    private int size;
    private List<Integer> distribution;
    private List<Integer> holes = new ArrayList<>();
    private int knownOperational = 0;
    private int neededOperational = 0;

    public static Arrangement fromString(String input) {
        var arrangement = new Arrangement();

        var fragments = input.split(" ");
        arrangement.original = fragments[0];
        arrangement.size = fragments[0].length();
        arrangement.distribution = Arrays.stream(fragments[1].split(","))
                .map(Integer::parseInt)
                .toList();
        arrangement.neededOperational = arrangement.distribution.stream().mapToInt(Integer::intValue).sum();

        var places = fragments[0].split("");
        for (int i = 0; i < places.length; i++) {
            if (places[i].equals("?")) {
                arrangement.holes.add(i);
            } else if (places[i].equals("#")) {
                arrangement.knownOperational++;
            }
        }

        return arrangement;
    }
}
