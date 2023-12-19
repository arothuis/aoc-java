package nl.arothuis.aoc.year2023.day19;

import java.util.List;

public record Rule(List<Condition> conditions) {
    public String apply(Rating rating) {
        for (var condition : conditions) {
            if (condition.applies(rating)) {
                return condition.target();
            }
        }

        throw new RuntimeException("Unresolved rule");
    }
}
