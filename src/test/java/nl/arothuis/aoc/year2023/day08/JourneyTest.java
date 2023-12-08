package nl.arothuis.aoc.year2023.day08;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JourneyTest {
    @Test
    @DisplayName("gives next steps based on given route")
    void nextStep() {
        var state = new Journey("LR");
        var a = state.next();
        var b = state.next();
        var c = state.next();

        assertEquals(0, a);
        assertEquals(1, b);
        assertEquals(0, c);
    }
}
