package nl.arothuis.aoc.year2024.day03;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2024", "03");
    private final String EXAMPLE_A = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
    private final String EXAMPLE_B1 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
    private final String EXAMPLE_B2 = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)?mul(8,5))";


    @Test
    void exampleA() {
        var day = new Solution();
        var solution = day.solveA(EXAMPLE_A);
        assertEquals(161, solution);
    }

    @Test
    void solutionA() {
        var day = new Solution();
        var solution = day.solveA(PUZZLE_INPUT);
        assertEquals(153469856L, solution);
    }

    @Test
    void exampleB1() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_B1);
        assertEquals(48L, solution);
    }

    @Test
    void exampleB2() {
        var day = new Solution();
        var solution = day.solveB(EXAMPLE_B2);
        assertEquals(8L, solution);
    }

    @Test
    void solutionB() {
        var day = new Solution();
        var solution = day.solveB(PUZZLE_INPUT);
        assertEquals(77055967L, solution);
    }
}
