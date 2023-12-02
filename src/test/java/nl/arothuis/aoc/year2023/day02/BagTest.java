package nl.arothuis.aoc.year2023.day02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BagTest {
    @Test
    @DisplayName("bags can only contain red, green and blue cubes")
    void noUnsupportedCubes() {
        var bag = new Bag();
        assertThrows(RuntimeException.class, () -> bag.addCubeCount("orange", 100));
    }
}
