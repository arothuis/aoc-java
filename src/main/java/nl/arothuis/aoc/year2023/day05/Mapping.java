package nl.arothuis.aoc.year2023.day05;

public record Mapping(Range source, Range destination) {
    public Long process(long number) {
        return !source.contains(number) ? number : destination.byIndex(source.toIndex(number));
    }

    public Range process(Range range) {
        if (!range.overlapsWith(source)) {
            return null;
        }

        var intersection = source.intersect(range);

        return new Range(
                destination.byIndex(source.toIndex(intersection.start())),
                destination.byIndex(source.toIndex(intersection.end()))
        );
    }
}
