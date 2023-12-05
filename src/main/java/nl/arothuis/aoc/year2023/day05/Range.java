package nl.arothuis.aoc.year2023.day05;

import java.util.ArrayList;
import java.util.List;

public record Range(long start, long end) {
    public boolean contains(long number) {
        return number >= start && number <= end;
    }

    public boolean overlapsWith(Range other) {
        return start <= other.end && other.start <= end;
    }

    public Range intersect(Range other) {
        return new Range(Math.max(start, other.start), Math.min(end, other.end));
    }

    public long toIndex(long number) {
        return number - start;
    }

    public long byIndex(long index) {
        return start + index;
    }

    public List<Range> minus(Range other) {
        if (!overlapsWith(other)) {
            return List.of(this);
        }

        List<Range> result = new ArrayList<>();

        if (other.start > start) {
            result.add(new Range(start, other.start));
        }

        if (other.end < end) {
            result.add(new Range(other.end, end));
        }

        return result;
    }

    public List<Range> minusAll(List<Range> others) {
        List<Range> result = new ArrayList<>();
        result.add(this);

        for (Range other : others) {
            List<Range> tempResult = new ArrayList<>();

            for (Range current : result) {
                tempResult.addAll(current.minus(other));
            }

            result = tempResult;
        }

        return result;
    }
}
