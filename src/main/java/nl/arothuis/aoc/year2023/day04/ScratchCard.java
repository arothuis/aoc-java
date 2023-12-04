package nl.arothuis.aoc.year2023.day04;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ScratchCard {
    private final int id;
    private final Set<Integer> left = new HashSet<>();
    private final Set<Integer> right = new HashSet<>();

    private ScratchCard(int id) {
        this.id = id;
    }

    public static ScratchCard fromString(String cardData) {
        String[] numbers = cardData.split(":");
        String[] sections = numbers[1].split("\\|");

        ScratchCard card = new ScratchCard(Integer.parseInt(numbers[0].substring(4).trim()));

        for (String leftNumber : sections[0].split(" ")) {
            if (!leftNumber.isBlank()) {
                card.left.add(Integer.parseInt(leftNumber.trim()));
            }
        }

        for (String rightNumber : sections[1].split(" ")) {
            if (!rightNumber.isBlank()) {
                card.right.add(Integer.parseInt(rightNumber.trim()));
            }
        }

        return card;
    }

    public int calculateValue() {
        var result = new HashSet<>(this.left);
        result.retainAll(this.right);

        return result.size() > 0 ? (int) Math.pow(2, result.size() - 1) : 0;
    }

    public Stream<Integer> streamWonCards(int limit) {
        var result = new HashSet<>(this.left);
        result.retainAll(this.right);

        return IntStream.range(id + 1, id + Math.min(result.size() + 1, limit)).boxed();
    }

    public int getId() {
        return id;
    }
}
