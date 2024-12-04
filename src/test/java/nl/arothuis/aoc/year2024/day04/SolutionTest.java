package nl.arothuis.aoc.year2024.day04;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2024", "04");
    private final String EXAMPLE_INPUT = PuzzleInput.getFromFile("2024", "04-example");

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_INPUT);
        assertEquals(18L, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(2401L, solution);
    }

    @Test
    void exampleB() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_INPUT);
        assertEquals(9L, solution);
    }


    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(1822L, solution);
    }
}
