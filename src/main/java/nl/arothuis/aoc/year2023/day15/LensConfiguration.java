package nl.arothuis.aoc.year2023.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LensConfiguration {
    private Map<Integer, List<Lens>> boxes = new HashMap<>();

    public static LensConfiguration fromString(String input) {
        LensConfiguration configuration = new LensConfiguration();
        String[] instructions = input.split(",");

        for (String instruction : instructions) {
            if (instruction.contains("=")) {
                String[] parts = instruction.split("=");
                String label = parts[0];
                int hash = Hash.calculate(label);
                int focalLength = Integer.parseInt(parts[1]);
                List<Lens> box = configuration.boxes.getOrDefault(hash, new ArrayList<>());

                boolean replaced = false;
                for (int i = 0; i < box.size(); i++) {
                    if (box.get(i).label().equals(label)) {
                        box.set(i, new Lens(label, focalLength));
                        replaced = true;
                    }
                }

                if (!replaced) {
                    box.add(new Lens(label, focalLength));
                }

                configuration.boxes.put(hash, box);

                continue;
            }

            String label = instruction.substring(0, instruction.length() - 1);
            int hash = Hash.calculate(label);

            if (!configuration.boxes.containsKey(hash)) {
                continue;
            }

            var box = configuration.boxes.get(hash);
            box.removeIf(lens -> lens.label().equals(label));
        }

        return configuration;
    }

    public Long calculateFocusingPower() {
        long result = 0L;

        for (var entry : boxes.entrySet()) {
            int boxNumber = entry.getKey();
            List<Lens> lenses = entry.getValue();

            for (int i = 0; i < lenses.size(); i++) {
                result += (long) (boxNumber + 1) * (i + 1) * lenses.get(i).focalLength();
            }
        }

        return result;
    }
}
