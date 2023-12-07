package nl.arothuis.aoc.year2023.day07;

public record Card(Rank rank, Suit suit) implements Comparable<Card> {
    public int compareTo(Card other) {
        return rank == other.rank ? 0 : (rank.getValue() > other.rank.getValue()) ? 1 : -1;
    }

    public static Card fromString(String rankCharacter) {
        return switch (rankCharacter) {
            case "A" -> new Card(Rank.ACE, Suit.HEARTS);
            case "K" -> new Card(Rank.KING, Suit.HEARTS);
            case "Q" -> new Card(Rank.QUEEN, Suit.HEARTS);
            case "J" -> new Card(Rank.JACK, Suit.HEARTS);
            case "T" -> new Card(Rank.TEN, Suit.HEARTS);
            case "9" -> new Card(Rank.NINE, Suit.HEARTS);
            case "8" -> new Card(Rank.EIGHT, Suit.HEARTS);
            case "7" -> new Card(Rank.SEVEN, Suit.HEARTS);
            case "6" -> new Card(Rank.SIX, Suit.HEARTS);
            case "5" -> new Card(Rank.FIVE, Suit.HEARTS);
            case "4" -> new Card(Rank.FOUR, Suit.HEARTS);
            case "3" -> new Card(Rank.THREE, Suit.HEARTS);
            case "2" -> new Card(Rank.TWO, Suit.HEARTS);
            default -> throw new RuntimeException("Invalid card: " + rankCharacter);
        };
    }
}
