package nl.arothuis.aoc.year2023.day13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternTest {
    @Test
    @DisplayName("can find perfect mirror point in a row or column")
    void singleMirrorPoint() {
        var input =
            "#.#..#.#\n" +
            "..####..\n" +
            "..####..\n" +
            "#.#..#.#\n";

        var pattern = Pattern.fromString(input);

        assertEquals(2, pattern.findMirror(pattern.rows()));
        assertEquals(5, pattern.findMirror(pattern.columns()));
    }
}
