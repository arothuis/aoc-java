package nl.arothuis.aoc.year2023.day02;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolutionTest {
    private final String EXAMPLE_INPUT = PuzzleInput.getFromFile("2023", "02-example");
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2023", "02");

    @Test
    @DisplayName("bags can only contain red, green and blue cubes")
    void noUnsupportedCubes() {
        var bag = new Bag();
        assertThrows(RuntimeException.class, () -> bag.addCubeCount("orange", 100));
    }

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_INPUT);
        assertEquals(8, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(2600, solution);
    }

    @Test
    void exampleB() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_INPUT);
        assertEquals(2286, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(86036, solution);
    }
}
