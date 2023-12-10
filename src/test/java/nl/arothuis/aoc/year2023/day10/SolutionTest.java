package nl.arothuis.aoc.year2023.day10;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String EXAMPLE_A1_INPUT = PuzzleInput.getFromFile("2023", "10-exampleA1");
    private final String EXAMPLE_A2_INPUT = PuzzleInput.getFromFile("2023", "10-exampleA2");
    private final String EXAMPLE_B_INPUT = PuzzleInput.getFromFile("2023", "10-exampleB");
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2023", "10");

    @Test
    void exampleA1() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_A1_INPUT);
        assertEquals(4, solution);
    }

    @Test
    void exampleA2() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_A2_INPUT);
        assertEquals(8, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(6927, solution);
    }

    @Test
    void exampleB() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_B_INPUT);
        assertEquals(4, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(467, solution);
    }
}
