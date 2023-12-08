package nl.arothuis.aoc.year2023.day08;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String EXAMPLE_A_INPUT = PuzzleInput.getFromFile("2023", "08-exampleA");
    private final String EXAMPLE_B_INPUT = PuzzleInput.getFromFile("2023", "08-exampleB");
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2023", "08");

    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_A_INPUT);
        assertEquals(2, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(15989, solution);
    }

    @Test
    void exampleB() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_B_INPUT);
        assertEquals(6, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(13830919117339L, solution);
    }
}
