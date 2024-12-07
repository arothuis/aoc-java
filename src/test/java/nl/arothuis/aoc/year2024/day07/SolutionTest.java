package nl.arothuis.aoc.year2024.day07;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2024", "07");
    private final String EXAMPLE_INPUT = PuzzleInput.getFromFile("2024", "07-example");

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_INPUT);
        assertEquals(3749L, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(303766880536L, solution);
    }

    @Test
    void exampleB() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_INPUT);
        assertEquals(11387L, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(337041851384440L, solution);
    }
}
