package nl.arothuis.aoc.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinatesTest {
    static Stream<Arguments> notInRangeExamples() {
        return Stream.of(
                Arguments.of(-1, 0),
                Arguments.of(0, -1),
                Arguments.of(4, 0),
                Arguments.of(3, 0),
                Arguments.of(0, 4),
                Arguments.of(0, 3),
                Arguments.of(-1, 4),
                Arguments.of(4, -1)

        );
    }

    static Stream<Arguments> clockwiseRotationExamples() {
        return Stream.of(
            Arguments.of(Coordinates.northwards(), Coordinates.eastwards()),
            Arguments.of(Coordinates.eastwards(), Coordinates.southwards()),
            Arguments.of(Coordinates.southwards(), Coordinates.westwards()),
            Arguments.of(Coordinates.westwards(), Coordinates.northwards())
        );
    }

    @Test
    @DisplayName("subtract other coordinates")
    void subtraction() {
        var a = new Coordinates(5, 3);
        var b = new Coordinates(1, 2);

        var result = a.subtract(b);

        assertEquals(new Coordinates(4, 1), result);
    }

    @Test
    @DisplayName("add other coordinates")
    void addition() {
        var a = new Coordinates(5, 3);
        var b = new Coordinates(1, 2);

        var result = a.add(b);

        assertEquals(new Coordinates(6, 5), result);
    }

    @Test
    @DisplayName("is within range")
    void inRange() {
        var start = new Coordinates(0, 0);
        var end = new Coordinates(3, 3);

        var a = new Coordinates(1, 1);
        var result = a.isWithin(start, end);

        assertTrue(result);
    }

    @ParameterizedTest
    @DisplayName("is not within range")
    @MethodSource("notInRangeExamples")
    void notInRange(int x, int y) {
        var start = new Coordinates(0, 0);
        var end = new Coordinates(3, 3);

        var a = new Coordinates(x, y);
        var result = a.isWithin(start, end);

        assertFalse(result);
    }

    @ParameterizedTest
    @DisplayName("rotate direction clockwise")
    @MethodSource("clockwiseRotationExamples")
    void clockwiseRotation(Coordinates input, Coordinates expected) {
        assertEquals(expected, input.rotateClockwise());
    }
}
