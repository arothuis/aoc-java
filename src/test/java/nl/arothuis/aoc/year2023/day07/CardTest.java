package nl.arothuis.aoc.year2023.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static nl.arothuis.aoc.year2023.day07.Rank.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CardTest {
    static Stream<Arguments> comparisonExamples() {
        return Stream.of(
                Arguments.of(new Card(ACE), new Card(ACE), 0),
                Arguments.of(new Card(ACE), new Card(TWO), 1),
                Arguments.of(new Card(JOKER), new Card(TWO), -1),
                Arguments.of(new Card(JACK), new Card(TWO), 1),
                Arguments.of(new Card(JACK), new Card(JOKER), 1)
        );
    }

    @Test
    @DisplayName("cannot parse unsupported characters")
    void unsupportedCharacters() {
        assertThrows(RuntimeException.class, () -> Card.fromString("x"));
    }

    @ParameterizedTest
    @DisplayName("compare cards by value")
    @MethodSource("comparisonExamples")
    void compareCards(Card a, Card b, int expectedSorting) {
        assertEquals(expectedSorting, a.compareTo(b));
    }
}
