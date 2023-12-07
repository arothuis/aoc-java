package nl.arothuis.aoc.year2023.day07;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HandTest {
    @Test
    @DisplayName("compare same hand type, highest wins")
    void compareSameHandTypeDifferentValues() {
        var a = Hand.fromString("KK677 100", Card::fromString);
        var b = Hand.fromString("KTJJT 200", Card::fromString);

        assertEquals(1, a.compareTo(b));
    }

    @Test
    @DisplayName("compare same hand")
    void compareSameHand() {
        var a = Hand.fromString("KK677 100", Card::fromString);
        var b = Hand.fromString("KK677 100", Card::fromString);

        assertEquals(0, a.compareTo(b));
    }

    @Test
    @DisplayName("compare same hand type, highest wins, joker")
    void compareJokerHand() {
        var a = Hand.fromString("QQQQ2 100", Card::fromStringWithJokers);
        var b = Hand.fromString("JKKK2 200", Card::fromStringWithJokers);

        assertEquals(1, a.compareTo(b));
    }
}
