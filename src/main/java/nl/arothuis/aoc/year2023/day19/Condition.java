package nl.arothuis.aoc.year2023.day19;

public record Condition(String lhs, Character op, Integer rhs, String target) {
    public boolean applies(Rating rating) {
        return !hasOp() || op.equals('>') && rating.get(lhs) > rhs || op.equals('<') && rating.get(lhs) < rhs;
    }

    public boolean hasOp() {
        return op != null;
    }

    public boolean isGreaterThan() {
        return op.equals('>');
    }
}
