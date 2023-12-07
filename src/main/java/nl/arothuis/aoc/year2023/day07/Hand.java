package nl.arothuis.aoc.year2023.day07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static nl.arothuis.aoc.year2023.day07.HandType.*;
import static nl.arothuis.aoc.year2023.day07.Rank.ACE;
import static nl.arothuis.aoc.year2023.day07.Rank.JOKER;

public record Hand(int bid, List<Card> cards) implements Comparable<Hand> {
    public static Hand fromString(String input, Function<String, Card> cardCreatorFn) {
        String[] parts = input.split(" ");

        return new Hand(
                Integer.parseInt(parts[1]),
                Arrays.stream(parts[0].split(""))
                        .map(cardCreatorFn)
                        .toList()
        );
    }

    public int compareTo(Hand other) {
        var type = classify();
        var otherType = other.classify();

        if (type != otherType) {
            return type.ordinal() > otherType.ordinal() ? 1 : -1;
        }

        for (int i = 0; i < cards.size(); i++) {
            if (!cards.get(i).equals(other.cards.get(i))) {
                return cards.get(i).compareTo(other.cards.get(i));
            }
        }

        return 0;
    }

    private HandType classify() {
        Map<Rank, Long> rankCount = cards.stream()
                .collect(groupingBy(Card::rank, Collectors.counting()));

        replaceJokers(rankCount);

        int distinctRanks = rankCount.size();
        long maxFrequency = rankCount.values().stream().max(Comparator.naturalOrder()).orElse(0L);

        return switch (distinctRanks) {
            case 1 -> FIVE_OF_A_KIND;
            case 2 -> maxFrequency == 4L ? FOUR_OF_A_KIND : FULL_HOUSE;
            case 3 -> maxFrequency == 3L ? THREE_OF_A_KIND : TWO_PAIR;
            case 4 -> ONE_PAIR;
            default -> HIGH_CARD;
        };
    }

    private void replaceJokers(Map<Rank, Long> rankCount) {
        var jokers = rankCount.getOrDefault(JOKER, 0L);
        rankCount.remove(JOKER);

        rankCount.entrySet()
                .stream()
                .max(Map.Entry.<Rank, Long>comparingByValue().thenComparing(Map.Entry::getKey))
                .ifPresentOrElse(
                        e -> rankCount.put(e.getKey(), e.getValue() + jokers),
                        () -> rankCount.put(ACE, 5L)
                );
    }

    public Long calculateScore(long multiplier) {
        return multiplier * bid;
    }
}
