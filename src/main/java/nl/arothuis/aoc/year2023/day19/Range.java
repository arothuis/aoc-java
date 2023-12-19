package nl.arothuis.aoc.year2023.day19;

public record Range(long start, long end) {
    public long sizeInclusive() {
        return end - start + 1;
    }

    public Range newStart(long newStart) {
        return new Range(newStart, end);
    }

    public Range newEnd(long newEnd) {
        return new Range(start, newEnd);
    }
}
