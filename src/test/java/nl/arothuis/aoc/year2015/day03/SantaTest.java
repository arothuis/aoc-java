package nl.arothuis.aoc.year2015.day03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SantaTest {
    @Test
    @DisplayName("only steps <, >, ^, v are supported")
    void noUnsupportedSteps() {
        var santa = new Santa();
        assertThrows(RuntimeException.class, () -> santa.move("X"));
    }
}
