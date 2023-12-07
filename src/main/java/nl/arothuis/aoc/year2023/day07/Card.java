package nl.arothuis.aoc.year2023.day07;

public record Card(Rank rank) implements Comparable<Card> {
    public static Card fromString(String rankCharacter) {
        return switch (rankCharacter) {
            case "A" -> new Card(Rank.ACE);
            case "K" -> new Card(Rank.KING);
            case "Q" -> new Card(Rank.QUEEN);
            case "J" -> new Card(Rank.JACK);
            case "T" -> new Card(Rank.TEN);
            case "9" -> new Card(Rank.NINE);
            case "8" -> new Card(Rank.EIGHT);
            case "7" -> new Card(Rank.SEVEN);
            case "6" -> new Card(Rank.SIX);
            case "5" -> new Card(Rank.FIVE);
            case "4" -> new Card(Rank.FOUR);
            case "3" -> new Card(Rank.THREE);
            case "2" -> new Card(Rank.TWO);
            default -> throw new RuntimeException("Invalid card: " + rankCharacter);
        };
    }

    public static Card fromStringWithJokers(String rankCharacter) {
        var card = fromString(rankCharacter);
        return (card.rank() == Rank.JACK) ? new Card(Rank.JOKER) : card;
    }

    public int compareTo(Card other) {
        return rank == other.rank ? 0 : (rank.ordinal() > other.rank.ordinal()) ? 1 : -1;
    }
}
