package nl.arothuis.aoc.year2015.day03;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Santa {
    private List<Coordinate> history = new ArrayList<>();

    public Santa() {
        history.add(new Coordinate(0, 0));
    }

    public void move(String step) {
        var current = history.get(history.size() - 1);

        var next = switch (step) {
            case "^" -> new Coordinate(current.x(), current.y()- 1);
            case ">" -> new Coordinate(current.x() + 1, current.y());
            case "v" -> new Coordinate(current.x(), current.y() + 1);
            case "<" -> new Coordinate(current.x() - 1, current.y());
            default -> throw new RuntimeException("Unsupported step: " + step);
        };

        history.add(next);
    }

    public Set<Coordinate> getUniqueVisitedHouses() {
        return new HashSet<>(history);
    }
}
