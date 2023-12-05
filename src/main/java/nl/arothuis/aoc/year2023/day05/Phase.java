package nl.arothuis.aoc.year2023.day05;

import java.util.ArrayList;
import java.util.List;

public class Phase {
    private final List<Mapping> mappings = new ArrayList<>();

    public void add(Mapping mapping) {
        this.mappings.add(mapping);
    }

    public long process(long number) {
        for (Mapping mapping : mappings) {
            long next = mapping.process(number);
            if (next != number) {
                return next;
            }
        }

        return number;
    }

    public List<Range> process(Range range) {
        List<Range> sources = new ArrayList<>();
        List<Range> destinations = new ArrayList<>();

        for (Mapping mapping : mappings) {
            sources.add(mapping.source());
            var destinationRange = mapping.process(range);
            if (destinationRange != null) {
                destinations.add(destinationRange);
            }
        }

        destinations.addAll(range.minusAll(sources));

        return destinations;
    }
}