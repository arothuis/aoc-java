package nl.arothuis.aoc.year2023.day03;

import java.util.*;

public class Schematic {
    private final Map<Connector, List<Integer>> connections = new HashMap<>();

    public static Schematic fromString(String input) {
        var schematic = new Schematic();

        StringBuilder context = new StringBuilder();
        int startLine = 0;
        int startColumn = 0;

        String[] lines = input.split("\n");
        for (int line = 0; line < lines.length; line++) {
            String[] columns = lines[line].split("");

            for (int column = 0; column < columns.length; column++) {
                char cursor = columns[column].charAt(0);
                if (Character.isDigit(cursor)) {
                    if (context.isEmpty()) {
                        startColumn = column;
                        startLine = line;
                    }

                    context.append(cursor);
                    continue;
                }

                if (context.isEmpty()) {
                    continue;
                }

                findConnector(lines, startLine, startColumn, context.length())
                        .ifPresent(connector -> schematic.connections
                                .computeIfAbsent(connector, key -> new ArrayList<>())
                                .add(Integer.parseInt(context.toString())));

                context.setLength(0);
            }
        }

        return schematic;
    }

    private static Optional<Connector> findConnector(String[] lines, int line, int start, int length) {
        var rowStart = Math.max(line - 1, 0);
        var rowEnd = Math.min(line + 1, lines.length - 1);
        var colStart = Math.max(start - 1, 0);
        var colEnd = Math.min(start + length, lines[0].length() - 1);

        for (int row = rowStart; row <= rowEnd; row++) {
            for (int column = colStart; column <= colEnd; column++) {
                char type = lines[row].charAt(column);
                if (type != '.' && !Character.isDigit(type)) {
                    return Optional.of(new Connector(type, row, column));
                }
            }
        }

        return Optional.empty();
    }

    public Integer sumConnectedEnginePartNumbers() {
        return this.connections.values().stream()
                .flatMapToInt(ns -> ns.stream().mapToInt(Integer::intValue))
                .sum();
    }

    public Integer sumGearRatios() {
        return this.connections.values().stream()
                .filter(ps -> ps.size() == 2)
                .mapToInt(ps -> ps.get(0) * ps.get(1))
                .sum();
    }
}
