package nl.arothuis.aoc.year2024.day05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Clean this up later ;)
public class SafetyManual {
    private final Map<Long, Set<Long>> rules = new HashMap<>();
    private final List<List<Long>> updates = new ArrayList<>();

    private SafetyManual() {
    }

    public static SafetyManual from(String input) {
        var manual = new SafetyManual();

        var parts = input.split("\n\n");
        for (String rule : parts[0].split("\n")) {
            var pages = rule.split("\\|");
            var left = Long.valueOf(pages[0]);
            var right = Long.valueOf(pages[1]);

            var pageRule = manual.rules.getOrDefault(left, new HashSet<>());
            pageRule.add(right);
            manual.rules.put(left, pageRule);
        }

        for (String updateData : parts[1].split("\n")) {
            List<Long> update = new ArrayList<>();
            
            for (String pageData : updateData.split(",")) {
                update.add(Long.valueOf(pageData));
            }

            manual.updates.add(update);
        }

        return manual;
    }

    public Long calculateValidUpdates() {
        return this.validateUpdates()
            .valid()
            .stream()
            .mapToLong(u -> u.get(u.size() / 2))
            .sum();
    }

    public Long calculateCorrectedUpdates() {
        var results = validateUpdates();

        List<List<Long>> fixed = new ArrayList<>();
        for (var toFix : results.invalid()) {
            List<Long> next = new ArrayList<>();
            for (var page : toFix) {
                if (next.isEmpty()) {
                    next.add(page);
                    continue;
                }

                boolean added = false;
                for (int i = 0; i < next.size(); i++) {
                    if (rules.getOrDefault(page, new HashSet<>()).contains(next.get(i))) {
                        next.add(i, page);
                        added = true;
                        break;
                    }                
                }

                if (!added) {
                    next.add(page);
                }
            }

            fixed.add(next);
        }

        return fixed.stream()
            .mapToLong(u -> u.get(u.size() / 2))
            .sum();
    }

    public Result validateUpdates() {
        List<List<Long>> validUpdates = new ArrayList<>();
        List<List<Long>> invalidUpdates = new ArrayList<>();

        for (var update : this.updates) {
            Set<Long> seen = new HashSet<>();
            boolean valid = true;

            for (var page : update) {
                var pageRules = this.rules.getOrDefault(page, new HashSet<>());
                var intersect = new HashSet<>(seen);
                intersect.retainAll(pageRules);

                if (intersect.size() > 0) {
                    invalidUpdates.add(update);
                    valid = false;
                    break;
                }
                
                seen.add(page);
            }

            if (valid) {
                validUpdates.add(update);
            }
        }

        return new Result(validUpdates, invalidUpdates);
    }
}
