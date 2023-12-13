package nl.arothuis.aoc.year2023.day13;

import java.util.Arrays;
import java.util.List;

public class ValleyOfMirrors {
    private final List<MirrorPattern> patterns;

    private ValleyOfMirrors(List<MirrorPattern> patterns) {
        this.patterns = patterns;
    }

    public static ValleyOfMirrors fromString(String input) {
        return new ValleyOfMirrors(
                Arrays.stream(input.split("\n\n"))
                        .map(MirrorPattern::fromString)
                        .toList()
        );
    }

    public Long sumOfAllMirrors(int smudges) {
        return patterns.stream()
                .mapToLong(p -> p.findMirror(p.columns(), smudges) + 100L * p.findMirror(p.rows(), smudges))
                .sum();
    }
}
