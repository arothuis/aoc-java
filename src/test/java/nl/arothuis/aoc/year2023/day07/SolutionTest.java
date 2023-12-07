package nl.arothuis.aoc.year2023.day07;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String EXAMPLE_INPUT = PuzzleInput.getFromFile("2023", "076-example");
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2023", "07");

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_INPUT);
        assertEquals(288, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(1710720, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(35349468, solution);
    }
}
