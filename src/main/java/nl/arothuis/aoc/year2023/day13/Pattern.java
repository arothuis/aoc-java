package nl.arothuis.aoc.year2023.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record Pattern(List<String> rows, List<String> columns) {
    public static Pattern fromString(String input) {
        List<String> rows = Arrays.asList(input.split("\n"));
        List<String> columns = new ArrayList<>(
                Collections.nCopies(rows.get(0).length(), "")
        );

        for (String row : rows) {
            for (int c = 0; c < rows.size(); c++) {
                columns.set(c, columns.get(c) + row.charAt(c));
            }
        }

        return new Pattern(rows, columns);
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
        return true;
    }
}
