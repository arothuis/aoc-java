package nl.arothuis.aoc.year2023.day19;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RuleTest {
    @Test
    void noMatchingCondition() {
        assertThrows(RuntimeException.class, () -> new Rule(List.of()).apply(new Rating(1, 2, 3, 4)));
    }
}
