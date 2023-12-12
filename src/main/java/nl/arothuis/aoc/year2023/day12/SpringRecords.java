package nl.arothuis.aoc.year2023.day12;

import java.util.*;

public class SpringRecords {
    private List<ConditionRecord> arrangements = new ArrayList<>();

    public static SpringRecords fromString(String input) {
        var records = new SpringRecords();

        records.arrangements = Arrays.stream(input.split("\n"))
                .map(ConditionRecord::fromString)
                .toList();

        return records;
    }

    public Long countAllPossibleArrangements() {
        Map<String, Long> memo = new HashMap<>();
        return arrangements.stream().mapToLong(arrangement -> arrangement.countPossibleArrangements(memo)).sum();
    }

    public Long countAllPossibleArrangementsUnfoldedBy(int times) {
        Map<String, Long> memo = new HashMap<>();
        return arrangements
                .stream()
                .mapToLong(arrangement -> arrangement.unfold(times).countPossibleArrangements(memo)).sum();
    }
}
