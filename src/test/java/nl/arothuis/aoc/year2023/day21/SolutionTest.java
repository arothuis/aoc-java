package nl.arothuis.aoc.year2023.day21;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String EXAMPLE_INPUT = PuzzleInput.getFromFile("2023", "21-example");
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2023", "21");

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_INPUT);
        assertEquals(-1L, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);

        assertEquals(-1L, solution);
    }

    @Test
    void exampleB() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_INPUT);
        assertEquals(-1L, solution);
    }


    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(-1L, solution);
    }
}
