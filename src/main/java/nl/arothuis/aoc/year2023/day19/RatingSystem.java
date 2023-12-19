package nl.arothuis.aoc.year2023.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class RatingSystem {
    private Workflow workflow;
    private List<Rating> ratings;

    public static RatingSystem fromString(String input) {
        var system = new RatingSystem();
        var parts = input.split("\n\n");

        system.workflow = Workflow.fromString(parts[0]);
        system.ratings = parts[1].lines().map(Rating::fromString).toList();

        return system;
    }

    public Stream<Rating> streamAcceptedParts() {
        return ratings.stream().filter(workflow::evaluate);
    }

    public Long countAcceptedRatings() {
        Map<String, Range> ranges = new HashMap<>();
        ranges.put("x", new Range(1, 4000));
        ranges.put("m", new Range(1, 4000));
        ranges.put("a", new Range(1, 4000));
        ranges.put("s", new Range(1, 4000));

        return workflow.countAcceptedRatings("in", ranges);
    }
}
