package nl.arothuis.aoc.year2024.day06;

public record Range(long start, long end) {
    public static Range of(long a, long b) {
        return (a < b) 
            ? new Range(a, b)
            : new Range(b, a);
    }

    public boolean contains(long inclusive) {
        return start <= inclusive && end >= inclusive;
    }
}
