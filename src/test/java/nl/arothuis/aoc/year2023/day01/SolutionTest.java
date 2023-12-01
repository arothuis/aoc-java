package nl.arothuis.aoc.year2023.day01;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private String PUZZLE_INPUT = PuzzleInput.getFromFile("2023", "01");
    private String EXAMPLE_A_INPUT = PuzzleInput.getFromFile("2023", "01-exampleA");
    private String EXAMPLE_B_INPUT = PuzzleInput.getFromFile("2023", "01-exampleB");

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_A_INPUT);
        assertEquals(142, solution);
    }

    @Test
    void exampleB() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_B_INPUT);
        assertEquals(281, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(55208, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(1783, solution);
    }
}
