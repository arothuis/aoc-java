package nl.arothuis.aoc.year2023.day19;

import java.util.*;

public class Workflow {
    private final HashMap<String, Rule> rules = new HashMap<>();

    public static Workflow fromString(String input) {
        var workflow = new Workflow();
        for (String line : input.split("\n")) {
            var parts = line.split("\\{");
            var label = parts[0];

            var expressions = parts[1].substring(0, parts[1].length() - 1).split(",");

            List<Condition> conditions = new ArrayList<>();
            for (String expression : expressions) {
                String[] fragments = expression.split(":");

                String lhs = null;
                Integer rhs = null;
                Character op = null;
                String target = fragments[0];

                if (fragments.length > 1) {
                    var condition = fragments[0].split("[><]");
                    lhs = condition[0];
                    rhs = Integer.parseInt(condition[1]);
                    op = fragments[0].charAt(lhs.length());
                    target = fragments[1];
                }

                conditions.add(new Condition(lhs, op, rhs, target));
            }

            workflow.rules.put(label, new Rule(conditions));
        }

        return workflow;
    }

    public boolean evaluate(Rating rating) {
        String currentLabel = "in";

        while (!Set.of("R", "A").contains(currentLabel)) {
            Rule rule = rules.get(currentLabel);
            currentLabel = rule.apply(rating);
        }

        return currentLabel.equals("A");
    }

    public Long countAcceptedRatings(String label, Map<String, Range> ranges) {
        if (label.equals("A")) {
            return ranges.values().stream().mapToLong(Range::sizeInclusive).reduce(1, (a, b) -> a * b);
        }

        if (label.equals("R")) {
            return 0L;
        }

        return rules.get(label).conditions().stream()
                .reduce(0L, (partialCount, condition) -> {
                            Range range = ranges.get(condition.lhs());

                            if (!condition.hasOp()) {
                                return partialCount + countAcceptedRatings(condition.target(), new HashMap<>(ranges));
                            }

                            long rhs = condition.rhs();
                            Range accept = condition.isGreaterThan() ? range.newStart(rhs + 1) : range.newEnd(rhs - 1);
                            Range reject = condition.isGreaterThan() ? range.newEnd(rhs) : range.newStart(rhs);

                            Map<String, Range> acceptedRanges = new HashMap<>(ranges);
                            acceptedRanges.put(condition.lhs(), accept);
                            ranges.put(condition.lhs(), reject);

                            return partialCount + countAcceptedRatings(condition.target(), acceptedRanges);
                        },
                        Long::sum
                );
    }
}
