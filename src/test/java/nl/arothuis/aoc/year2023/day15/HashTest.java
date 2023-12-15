package nl.arothuis.aoc.year2023.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTest {
    @Test
    void calculateHash() {
        var input = "HASH";
        var result = Hash.calculate(input);

        assertEquals(52, result);
    }

    @Test
    void calculateEmptyHash() {
        var input = "";
        var result = Hash.calculate(input);

        assertEquals(0, result);
    }
}
