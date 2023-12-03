package nl.arothuis.aoc.year2023.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Schematic {
    private final List<EnginePart> engineParts = new ArrayList<>();

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

                Optional<Connector> connector = findConnector(lines, startLine, startColumn, context.length());
                if (connector.isPresent()) {
                    schematic.engineParts.add(new EnginePart(Integer.parseInt(context.toString()), connector.get()));
                }
                context = new StringBuilder();
            }
        }

        return schematic;
    }

    private static Optional<Connector> findConnector(String[] lines, int line, int start, int length) {
        for (int row = line - 1; row <= line + 1; row++) {
            if (row < 0 || row >= lines.length) {
                continue;
            }

            for (int column = start - 1; column < start + length + 1; column++) {
                if (column < 0 || column >= lines[0].length()) {
                    continue;
                }

                char type = lines[row].charAt(column);
                if (type != '.' && !Character.isDigit(type)) {
                    return Optional.of(new Connector(type, row, column));
                }
            }
        }

        return Optional.empty();
    }

    public Integer sumConnectedEnginePartNumbers() {
        return this.engineParts.stream().mapToInt(EnginePart::partNumber).sum();
    }

    public Integer sumGearRatios() {
        return this.engineParts.stream().filter(part -> part.connector().type() == '*')
                .collect(Collectors.groupingBy(
                        EnginePart::connector,
                        Collectors.mapping(EnginePart::partNumber, Collectors.toList())
                ))
                .values().stream()
                .filter(parts -> parts.size() == 2)
                .mapToInt(parts -> parts.stream().reduce((a, b) -> a * b).get())
                .sum();
    }

    private record Connector(char type, int line, int column) {
    }

    private record EnginePart(int partNumber, Connector connector) {
    }
}
