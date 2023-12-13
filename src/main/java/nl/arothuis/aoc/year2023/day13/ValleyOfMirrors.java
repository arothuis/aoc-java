package nl.arothuis.aoc.year2023.day13;

import java.util.Arrays;
import java.util.List;

public class ValleyOfMirrors {
    private List<Pattern> patterns;

    private ValleyOfMirrors(List<Pattern> patterns) {
        this.patterns = patterns;
    }

    public static ValleyOfMirrors fromString(String input) {
        return new ValleyOfMirrors(
                Arrays.stream(input.split("\n\n"))
                        .map(Pattern::fromString)
                        .toList()
        );
    }

    public Long sumOfAllMirrors() {
        return patterns.stream().mapToLong(p -> p.findMirror(p.columns()) + 100L * p.findMirror(p.rows())).sum();
    }
}
