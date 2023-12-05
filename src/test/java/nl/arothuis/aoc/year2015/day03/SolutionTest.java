package nl.arothuis.aoc.year2015.day03;

import nl.arothuis.aoc.core.PuzzleInput;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    private final String PUZZLE_INPUT = PuzzleInput.getFromFile("2015", "03");

    static Stream<Arguments> exampleAStepsToVisitedHouses() {
        return Stream.of(
                Arguments.of(">", 2),
                Arguments.of("^>v<", 4),
                Arguments.of("^v^v^v^v^v", 2)
        );
    }

    static Stream<Arguments> exampleBStepsToVisitedHouses() {
        return Stream.of(
                Arguments.of("^v", 3),
                Arguments.of("^>v<", 3),
                Arguments.of("^v^v^v^v^v", 11)
        );
    }

    @ParameterizedTest
    @MethodSource("exampleAStepsToVisitedHouses")
    void exampleA(String steps, int visitedLocations) {
        var day = new Solution();
        var solution = day.solveA(steps);
        assertEquals(visitedLocations, solution);
    }

    @ParameterizedTest
    @MethodSource("exampleBStepsToVisitedHouses")
    void exampleB(String steps, int visitedLocations) {
        var day = new Solution();
        var solution = day.solveB(steps);
        assertEquals(visitedLocations, solution);
    }

    @Test
    void solutionA() {
        var solution = new Solution();
        var result = solution.solveA(PUZZLE_INPUT);
        assertEquals(2592, result);
    }

    @Test
    void solutionB() {
        var solution = new Solution();
        var result = solution.solveB(PUZZLE_INPUT);
        assertEquals(2360, result);
    }
}