package nl.arothuis.aoc.year2023.day12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ArrangementTest {
    @Test
    @DisplayName("calculates only valid arrangement for simple cases")
    void options() {

    }

    static Stream<Arguments> simpleCases() {
        return Stream.of(
                Arguments.of("???.### 1,1,3", Set.of("#.#.###"))
        );
    }
}
