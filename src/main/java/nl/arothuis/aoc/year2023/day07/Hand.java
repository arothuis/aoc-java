package nl.arothuis.aoc.year2023.day07;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public record Hand(int bid, List<Card> cards) implements Comparable<Hand> {
    public static Hand fromString(String input) {
        String[] parts = input.split(" ");

        return new Hand(
                Integer.parseInt(parts[1]),
                Arrays.stream(parts[0].split(""))
                    .map(Card::fromString)
                    .toList()
        );
    }

    public int compareTo(Hand other) {
        var type = classify();
        var otherType = other.classify();

        return type == otherType ? 0 : type.ordinal() > otherType.ordinal() ? 1 : -1;
    }

    public HandType classify() {
        Map<Rank, List<Card>> cardsByRank = cards.stream()
                .collect(groupingBy(Card::rank));

        if (cardsByRank.size() == 1) {
            return HandType.FIVE_OF_A_KIND;
        }

        if (cardsByRank.size() == 2) {
            return HandType.FULL_HOUSE;
        }

        if (cardsByRank.values().stream().anyMatch(cards -> cards.size() == 3)) {
            return HandType.THREE_OF_A_KIND;
        }

        if (cardsByRank.size() == 3) {
            return HandType.TWO_PAIR;
        }

        if (cardsByRank.size() == 4) {
            return HandType.ONE_PAIR;
        }

        return HandType.HIGH_CARD;
    }
}
