package nl.arothuis.aoc.year2023.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record MirrorPattern(List<String> rows, List<String> columns) {
    public static MirrorPattern fromString(String input) {
        List<String> rows = Arrays.asList(input.split("\n"));
        List<String> columns = new ArrayList<>(
                Collections.nCopies(rows.get(0).length(), "")
        );

        for (String row : rows) {
            for (int c = 0; c < row.length(); c++) {
                columns.set(c, row.charAt(c) + columns.get(c));
            }
        }

        return new MirrorPattern(rows, columns);
    }

    public int findMirror(List<String> line, int smudges) {
        for (int mirror = 1; mirror < line.size(); mirror++) {
            int smudgesFound = 0;

            for (int r = 1; mirror - r >= 0 && mirror + r - 1 < line.size(); r++) {
                var before = line.get(mirror - r);
                var after = line.get(mirror + r - 1);

                for (int field = 0; field < before.length(); field++) {
                    smudgesFound += before.charAt(field) != after.charAt(field) ? 1 : 0;
                }
            }

            if (smudgesFound == smudges) {
                return mirror;
            }
        }

        return 0;
    }
}
