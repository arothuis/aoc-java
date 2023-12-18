package nl.arothuis.aoc.year2023.day11;

import nl.arothuis.aoc.core.Coordinates;

import java.util.*;

public class GalaxyImage {
    private final List<Coordinates> galaxies = new ArrayList<>();
    private final Set<Integer> nonEmptyColumns = new HashSet<>();
    private final Set<Integer> nonEmptyRows = new HashSet<>();

    public static GalaxyImage fromString(String input) {
        GalaxyImage image = new GalaxyImage();

        String[] rows = input.split("\n");
        for (int y = 0; y < rows.length; y++) {
            String[] columns = rows[y].split("");
            for (int x = 0; x < columns.length; x++) {
                if (columns[x].equals("#")) {
                    image.galaxies.add(new Coordinates(x, y));
                    image.nonEmptyColumns.add(x);
                    image.nonEmptyRows.add(y);
                }
            }
        }

        return image;
    }

    public long sumExpandedDistances(int growthFactor) {
        Set<List<Coordinates>> seen = new HashSet<>();

        return galaxies.stream()
                .flatMap(a -> galaxies.stream()
                        .filter(b -> !a.equals(b) && !seen.contains(List.of(a, b)))
                        .map(b -> {
                            long d = calculateExpandedDistance(a, b, growthFactor);
                            seen.addAll(List.of(List.of(a, b), List.of(b, a)));
                            return d;
                        }))
                .reduce(0L, Long::sum);
    }

    private long calculateExpandedDistance(Coordinates a, Coordinates b, int growthFactor) {
        long dx = Math.abs(b.x() - a.x());
        long dy = Math.abs(b.y() - a.y());

        long extraCols = dx == 0 ? 0 : dx - countBetween(nonEmptyColumns, a.x(), b.x()) - 1;
        long extraRows = dy == 0 ? 0 : dy - countBetween(nonEmptyRows, a.y(), b.y()) - 1;

        return dx + extraCols * (growthFactor - 1) + dy + extraRows * (growthFactor - 1);
    }

    private long countBetween(Collection<Integer> ns, long a, long b) {
        return ns.stream()
                .filter(n -> n > Math.min(a, b) && n < Math.max(a, b))
                .count();
    }
}
