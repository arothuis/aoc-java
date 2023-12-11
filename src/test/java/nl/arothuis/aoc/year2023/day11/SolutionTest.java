package nl.arothuis.aoc.year2023.day11;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String EXAMPLE_INPUT = PuzzleInput.getFromFile("2023", "11-example");
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2023", "11");

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_INPUT);
        assertEquals(374, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(10292708L, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(790194712336L, solution);
    }
}
