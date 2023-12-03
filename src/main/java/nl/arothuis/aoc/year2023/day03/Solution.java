package nl.arothuis.aoc.year2023.day03;

import nl.arothuis.aoc.core.PuzzleSolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Connector(char type, int line, int column){

}
record EnginePart(int partNumber, Connector connector) {
}

public class Solution implements PuzzleSolution<Integer, Integer> {
    @Override
    public Integer solveA(String input) {
        return parseEngineParts(input).stream()
                .mapToInt(EnginePart::partNumber)
                .sum();
    }

    @Override
    public Integer solveB(String input) {
        Map<Connector, List<Integer>> connections = new HashMap<>();
        parseEngineParts(input).stream()
                .filter(part -> part.connector().type() == '*')
                .forEach(part -> {
                    var partNumbers = connections.getOrDefault(part.connector(), new ArrayList<>());
                    partNumbers.add(part.partNumber());
                    connections.put(part.connector(), partNumbers);
                });

        return connections.values().stream()
                .filter(parts -> parts.size() == 2)
                .mapToInt(parts -> parts.stream().reduce((a, b) -> a * b).get())
                .sum();
    }

    private List<EnginePart> parseEngineParts(String input) {
        List<EnginePart> engineParts = new ArrayList<>();
        StringBuilder context = new StringBuilder();

        String[] lines = input.split("\n");

        for (int line = 0; line < lines.length; line++) {
            String[] columns = lines[line].split("");
            for (int column = 0; column < columns.length; column++) {
                char cursor = columns[column].charAt(0);

                if (Character.isDigit(cursor)) {
                    context.append(cursor);
                    continue;
                }

                if (context.isEmpty()) {
                    continue;
                }

                Connector connector = findConnector(lines, line, column, context.length());
                if (connector != null) {
                    engineParts.add(new EnginePart(Integer.parseInt(context.toString()), connector));
                }
                context = new StringBuilder();
            }
        }
        return engineParts;
    }

    private Connector findConnector(String[] lines, int line, int end, int valueLength) {
        if (end == 0) {
            line--;
            end = lines[0].length();
        }

        for (int row = line - 1; row <= line + 1; row++) {
            if (row < 0 || row >= lines.length) {
                continue;
            }

            for (int column = end - valueLength - 1; column < end + 1; column++) {
                if (column < 0 || column >= lines[0].length()) {
                    continue;
                }

                char type = lines[row].charAt(column);
                if (type != '.' && !Character.isDigit(type)) {
                    return new Connector(type, row, column);
                }
            }
        }

        return null;
    }
}