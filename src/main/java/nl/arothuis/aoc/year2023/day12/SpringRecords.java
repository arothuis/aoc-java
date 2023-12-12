package nl.arothuis.aoc.year2023.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpringRecords {
    private List<Arrangement> arrangements = new ArrayList<>();

    public static SpringRecords fromString(String input) {
        var records = new SpringRecords();

        records.arrangements = Arrays.stream(input.split("\n"))
                .map(Arrangement::fromString)
                .toList();

        System.out.println(records.arrangements);

        return records;
    }
}
