package nl.arothuis.aoc.year2015.day02;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2015", "02");

    @Test
    void surfaceArea() {
        var solution = new Solution();
        var result = solution.calculateSurfaceArea(2, 3, 4);
        assertEquals(52, result);
    }

    @Test
    void smallestArea() {
        var solution = new Solution();
        var result = solution.calculateSmallestArea(2, 3, 4);
        assertEquals(6, result);
    }

    @Test
    void solutionA() {
        var solution = new Solution();
        var result = solution.solveA(PUZZLE_INPUT);
        assertEquals(1606483, result);
    }

    @Test
    void solutionB() {
        var solution = new Solution();
        var result = solution.solveB(PUZZLE_INPUT);
        assertEquals(3842356, result);
    }
}
