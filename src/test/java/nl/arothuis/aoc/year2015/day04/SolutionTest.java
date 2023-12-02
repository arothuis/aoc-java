package nl.arothuis.aoc.year2015.day04;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private String PUZZLE_INPUT = PuzzleInput.getFromFile("2015", "04");

    @Test
    void solutionA() {
        var solution = new Solution();
        var result = solution.solveA(PUZZLE_INPUT);
        assertEquals(346386, result);
    }

    @Test
    void solutionB() {
        var solution = new Solution();
        var result = solution.solveB(PUZZLE_INPUT);
        assertEquals(9958218, result);
    }
}