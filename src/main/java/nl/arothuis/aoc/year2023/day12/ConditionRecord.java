package nl.arothuis.aoc.year2023.day12;

import java.util.*;

public class ConditionRecord {
    private String initialArrangement;
    private List<Integer> groups;

    public static ConditionRecord fromString(String input) {
        var conditionRecord = new ConditionRecord();

        var fragments = input.split(" ");
        conditionRecord.initialArrangement = fragments[0];
        conditionRecord.groups = Arrays.stream(fragments[1].split(",")).map(Integer::parseInt).toList();

        return conditionRecord;
    }

    public Long countPossibleArrangements(Map<String, Long> memo) {
        return this.countPossibleArrangements(initialArrangement, groups, memo);
    }

    private Long countPossibleArrangements(String arrangement, List<Integer> groups, Map<String, Long> memo) {
        String key = arrangement + groups.toString();
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (arrangement.isEmpty()) {
            return groups.isEmpty() ? 1L : 0;
        }

        if (groups.isEmpty()) {
            return arrangement.contains("#") ? 0 : 1L;
        }

        long possibilities = 0;
        int num1 = groups.get(0);
        char char1 = arrangement.charAt(0);

        if (Set.of('.', '?').contains(char1)) {
            possibilities += countPossibleArrangements(arrangement.substring(1), groups, memo);
        }

        if (!Set.of('#', '?').contains(char1)) {
            return possibilities;
        }

        if (num1 > arrangement.length()) {
            return possibilities;
        }

        if (arrangement.substring(0, num1).contains(".")) {
            return possibilities;
        }

        if (num1 == arrangement.length() || arrangement.charAt(num1) != '#') {
            String next = (num1 + 1 < arrangement.length()) ? arrangement.substring(num1 + 1) : "";
            possibilities += countPossibleArrangements(next, groups.subList(1, groups.size()), memo);
        }

        memo.put(key, possibilities);

        return possibilities;
    }

    public ConditionRecord unfold(int times) {
        initialArrangement = String.join("?", Collections.nCopies(times, initialArrangement));
        groups = Collections.nCopies(times, groups).stream()
                .flatMap(List::stream)
                .toList();

        return this;
    }
}
