package nl.arothuis.aoc.year2023.day12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrangementTest {
    static Stream<Arguments> possibleArrangements() {
        return Stream.of(
                Arguments.of("???.### 1,1,3", 1),
                Arguments.of(".??..??...?##. 1,1,3", 4),
                Arguments.of("?#?#?#?#?#?#?#? 1,3,1,6", 1),
                Arguments.of("????.#...#... 4,1,1", 1),
                Arguments.of("????.######..#####. 1,6,5", 4),
                Arguments.of("?###???????? 3,2,1", 10)
        );
    }

    @ParameterizedTest
    @DisplayName("calculates amount of valid arrangements")
    @MethodSource("possibleArrangements")
    void options(String input, int expectedResult) {
        var arrangement = ConditionRecord.fromString(input);
        long result = arrangement.countPossibleArrangements(new HashMap<>());

        assertEquals(expectedResult, result);
    }
}
