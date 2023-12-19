package nl.arothuis.aoc.year2023.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

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

                Predicate<Rating> conditional = rating -> true;
                String target = fragments[0];

                if (fragments.length > 1) {
                    var condition = fragments[0].split("[><]");
                    var lhs = condition[0];
                    var rhs = Integer.parseInt(condition[1]);
                    var op = fragments[0].charAt(lhs.length());
                    target = fragments[1];
                    conditional = rating -> op == '>' && rating.get(lhs) > rhs || op == '<' && rating.get(lhs) < rhs;
                }

                conditions.add(new Condition(conditional, target));
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
}
