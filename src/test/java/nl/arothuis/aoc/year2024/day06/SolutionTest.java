package nl.arothuis.aoc.year2024.day06;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2024", "06");
    private final String EXAMPLE_INPUT = PuzzleInput.getFromFile("2024", "06-example");

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_INPUT);
        assertEquals(41L, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(4663L, solution);
    }

    @Test
    void solutionB() {
        // Thoughts:
        // - we need to place an obstacle
        // - so that steps + direction will overlap with earlier steps + direction
        // - while traversing, we can create candidate lanes to hook into
        //   look behind you when starting, look left when turning
        // 339 too low
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(-1L, solution);
    }
}
