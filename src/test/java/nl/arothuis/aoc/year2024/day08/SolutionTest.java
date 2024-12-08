package nl.arothuis.aoc.year2024.day08;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2024", "08");
    private final String EXAMPLE_INPUT = PuzzleInput.getFromFile("2024", "08-example");

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_INPUT);
        assertEquals(14L, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(359L, solution);
    }

    @Test
    void exampleB() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_INPUT);
        assertEquals(34L, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(1293L, solution);
    }
}
