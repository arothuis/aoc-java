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

    public int findMirror(List<String> line) {
        for (int i = 1; i < line.size(); i++) {
            if (!line.get(i - 1).equals(line.get(i))) {
                continue;
            }

            if (isPerfectMirror(line, i)) {
                return i;
            }
        }

        return 0;
    }

    private boolean isPerfectMirror(List<String> line, int i) {
        for (int j = 1; i - j > 0 && i + j < line.size(); j++) {
            if (!line.get(i - j - 1).equals(line.get(i + j))) {
                return false;
            }
        }

        return true;
    }
}
