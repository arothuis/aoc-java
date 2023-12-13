package nl.arothuis.aoc.year2023.day13;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternTest {
    @Test
    @DisplayName("can find perfect mirror in row or column")
    void mirror1() {
        var input = "#.#..#.#\n"
                + "..####..\n"
                + "..####..\n"
                + "#.#..#.#\n";

        var pattern = MirrorPattern.fromString(input);

        assertEquals(2, pattern.findMirror(pattern.rows(), 0));
        assertEquals(4, pattern.findMirror(pattern.columns(), 0));
    }

    @Test
    @DisplayName("cannot find mirror points")
    void noMirrorPoints() {
        var input = "#.#..#.#\n"
                + "..#.##..\n"
                + "..####..\n"
                + "#.#..#.#\n";

        var pattern = MirrorPattern.fromString(input);

        assertEquals(0, pattern.findMirror(pattern.rows(), 0));
        assertEquals(0, pattern.findMirror(pattern.columns(), 0));
    }

    @Test
    @DisplayName("can find perfect mirror point after unsmudge")
    void onlyMirrorAfterUnsmudge() {
        var input = "#.#..#.#\n"
                + "..#.##..\n"
                + "..####..\n"
                + "#.#..#.#\n";

        var pattern = MirrorPattern.fromString(input);

        assertEquals(2, pattern.findMirror(pattern.rows(), 1));
        assertEquals(4, pattern.findMirror(pattern.columns(), 1));
    }
}
