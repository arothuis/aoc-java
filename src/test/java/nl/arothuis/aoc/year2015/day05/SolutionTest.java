package nl.arothuis.aoc.year2015.day05;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2015", "05");

    @Test
    void solutionA() {
        var solution = new Solution();
        var result = solution.solveA(PUZZLE_INPUT);
        assertEquals(258, result);
    }

    @Test
    void solutionB() {
        var solution = new Solution();
        var result = solution.solveB(PUZZLE_INPUT);
        assertEquals(53, result);
    }
}