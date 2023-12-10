package nl.arothuis.aoc.year2023.day10;

import nl.arothuis.aoc.core.Coordinates;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PipeTest {
    @ParameterizedTest
    @DisplayName("can connect to other pipe")
    @MethodSource("pipeConnectionExamples")
    void canConnect(String pipeA, int bx, int by, String pipeB, boolean expected) {
        Pipe a = new Pipe(new Coordinates(0, 0), pipeA);
        Pipe b = new Pipe(new Coordinates(bx, by), pipeB);

        boolean actual = b.canConnectTo(a);

        assertEquals(expected, actual);
    }

    static Stream<Arguments> pipeConnectionExamples() {
        return Stream.of(
                Arguments.of("-", 1, 0, ".", false),
                Arguments.of(".", 1, 0, "-", false),
                Arguments.of("|", 0, 1, ".", false),
                Arguments.of("-", 1, 1, "-", false),
                Arguments.of("-", 1, 0, "-", true),
                Arguments.of("-", 1, 0, "-", true),
                Arguments.of("-", 1, 0, "7", true),
                Arguments.of("-", 1, 0, "J", true),
                Arguments.of("F", 1, 0, "J", true),
                Arguments.of("J", 1, 0, "F", false),
                Arguments.of("-", -1, 0, "-", true),
                Arguments.of("-", -1, 0, "L", true),
                Arguments.of("-", -1, 0, "F", true),
                Arguments.of("|", 0, 1, "|", true),
                Arguments.of("|", 0, 1, "L", true),
                Arguments.of("|", 0, 1, "J", true),
                Arguments.of("|", 0, -1, "|", true),
                Arguments.of("|", 0, -1, "7", true),
                Arguments.of("|", 0, -1, "F", true),
                Arguments.of("S", 0, -1, "F", true),
                Arguments.of("|", 0, -1, "S", true),
                Arguments.of("S", 0, -1, "-", false)
        );
    }
}
