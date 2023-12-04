package nl.arothuis.aoc.year2015.day05;

import nl.arothuis.aoc.core.PuzzleSolution;

import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolution<Long, Long> {
    private static final Predicate<String> THREE_OR_MORE_VOWELS = Pattern.compile("(?:[aeiou].*){3,}").asPredicate();
    private static final Predicate<String> SAME_LETTER_TWICE_IN_A_ROW = Pattern.compile("([a-zA-Z])\\1").asPredicate();
    private static final Predicate<String> BAD_PAIR = Pattern.compile("(ab|cd|pq|xy)").asPredicate();
    private static final Predicate<String> OVERLAPPING_PAIR = Pattern.compile("(\\w\\w).*\\1").asPredicate();
    private static final Predicate<String> REPEATING_LETTER = Pattern.compile("(\\w)\\w\\1").asPredicate();

    @Override
    public Long solveA(String input) {
        return input.lines()
                .filter(THREE_OR_MORE_VOWELS.and(SAME_LETTER_TWICE_IN_A_ROW).and(BAD_PAIR.negate()))
                .count();
    }

    @Override
    public Long solveB(String input) {
        return input.lines()
                .filter(OVERLAPPING_PAIR.and(REPEATING_LETTER))
                .count();
    }
}