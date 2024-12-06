package nl.arothuis.aoc.year2024.day04;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nl.arothuis.aoc.core.Coordinates;

public final class LetterGrid {
    final Map<Coordinates, String> letters;
    final Coordinates size;

    private LetterGrid(Map<Coordinates, String> letters, Coordinates size) {
        this.letters = letters;
        this.size = size;
    }

    final static LetterGrid from(String input) {
        int x = 0;
        int y = 0;
        
        Map<Coordinates, String> letters = new HashMap<>();
        for (String line : input.split("\n")) {
            x = 0;

            for (String letter : line.trim().split("")) {
                letters.put(new Coordinates(x, y), letter);
                x++;
            }

            y++;
        }

        return new LetterGrid(letters, new Coordinates(x, y));
    }

    final private String get(int x, int y) {
        return letters.getOrDefault(new Coordinates(x, y), "_");
    }

    final private Long match(String candidate, String target) {
        return Set.of(target, new StringBuilder(target).reverse().toString())
            .contains(candidate) ? 1L : 0L;
    }

    final public Long countXmas() {
        long count = 0L;

        for (int y = 0; y < size.y(); y++) {
            for (int x = 0; x < size.x(); x++) {
                count += match(get(x, y) + get(x + 1, y) + get(x + 2, y) + get(x + 3, y), "XMAS")
                    + match(get(x, y) + get(x, y + 1) + get(x, y + 2) + get(x, y + 3), "XMAS")
                    + match(get(x, y) + get(x + 1, y + 1) + get(x + 2, y + 2) + get(x + 3, y + 3), "XMAS")
                    + match(get(x, y) + get(x + 1, y - 1) + get(x + 2, y - 2) + get(x + 3, y - 3), "XMAS");    
            }
        }

        return count;
    }

    final public Long countCrossMas() {
        long count = 0L;

        for (int y = 0; y < size.y(); y++) {
            for (int x = 0; x < size.x(); x++) {
                if (get(x, y).equals("A")) {
                    count += match(get(x - 1, y - 1) + get(x, y) + get(x + 1, y + 1), "MAS")
                        & match(get(x - 1, y + 1) + get(x, y) + get(x + 1, y - 1), "MAS");
                }   
            }
        }

        return count;
    }
}
