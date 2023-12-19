package nl.arothuis.aoc.year2023.day19;

import java.util.function.Predicate;

public record Condition(Predicate<Rating> condition, String target) {
    public boolean applies(Rating rating) {
        return condition.test(rating);
    }
}
