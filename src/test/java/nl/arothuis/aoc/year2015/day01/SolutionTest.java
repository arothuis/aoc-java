package nl.arothuis.aoc.year2015.day01;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SolutionTest {
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2015", "01");

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(232, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(1783, solution);
    }

    @Test
    void cantReachBasement() {
        var day = new Solution();
        assertThrows(RuntimeException.class, () -> day.solveB(""));
    }
}
