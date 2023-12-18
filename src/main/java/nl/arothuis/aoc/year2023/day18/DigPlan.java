package nl.arothuis.aoc.year2023.day18;

import nl.arothuis.aoc.core.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class DigPlan {
    private final List<Coordinates> digBoundary = new ArrayList<>();
    private List<Instruction> instructions = new ArrayList<>();

    public static DigPlan fromString(String input) {
        var plan = new DigPlan();
        plan.instructions = input.lines().map(Instruction::fromString).toList();

        return plan;
    }

    public long calculateDigSite(BiFunction<Instruction, Coordinates, Coordinates> rangeFn) {
        Coordinates current = Coordinates.origin();
        digBoundary.add(current);

        for (var instruction : instructions) {
            current = rangeFn.apply(instruction, current);
            digBoundary.add(current);
        }

        return calculateTotalArea();
    }

    private long calculateTotalArea() {
        // Shoelace formula + Pick's theorem (see: day 10)
        long area = 0;
        long boundary = 0;

        for (int i = 0; i < digBoundary.size(); i++) {
            Coordinates current = digBoundary.get(i);
            Coordinates next = digBoundary.get((i + 1) % digBoundary.size());
            area += current.x() * next.y() - current.y() * next.x();
            boundary += Math.abs(next.x() - current.x()) + Math.abs(next.y() - current.y());
        }

        return Math.abs(area) / 2 - boundary / 2 + 1 + boundary;
    }
}
