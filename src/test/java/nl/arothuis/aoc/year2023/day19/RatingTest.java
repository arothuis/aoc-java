package nl.arothuis.aoc.year2023.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RatingTest {
    @Test
    void noMatchingCategory() {
        assertThrows(RuntimeException.class, () -> new Rating(1, 2, 3, 4).get("q"));
    }
}
