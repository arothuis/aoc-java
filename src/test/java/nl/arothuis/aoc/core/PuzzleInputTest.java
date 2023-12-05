package nl.arothuis.aoc.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PuzzleInputTest {
    @Test
    @DisplayName("read puzzle input by year and day under resources directory")
    void readInputFromFile() {
        var input = PuzzleInput.getFromFile("2023", "-test");
        assertEquals("It works!", input);
    }

    @Test
    @DisplayName("informs when input cannot be read from file")
    void inputCannotBeReadFromFile() {
        assertThrows(RuntimeException.class, () -> PuzzleInput.getFromFile("0000", "00"));
    }
}