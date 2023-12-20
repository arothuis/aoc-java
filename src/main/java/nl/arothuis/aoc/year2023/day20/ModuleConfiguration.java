package nl.arothuis.aoc.year2023.day20;

import nl.arothuis.aoc.core.MathHelper;

import java.util.*;

public class ModuleConfiguration {
    private final Map<String, Module> modules = new HashMap<>();
    private final Map<String, List<String>> targets = new HashMap<>();

    public static ModuleConfiguration fromString(String input) {
        var configuration = new ModuleConfiguration();
        List<String> conjunctions = new ArrayList<>();

        for (String line : input.split("\n")) {
            var side = line.split(" -> ");
            var destinations = Arrays.stream(side[1].split(", ")).toList();
            var address = side[0].substring(1);

            for (String destination : destinations) {
                configuration.targets.computeIfAbsent(destination, k -> new ArrayList<>()).add(address);
            }

            if (side[0].startsWith("&")) {
                configuration.modules.put(address, new Conjunction(address, destinations));
                conjunctions.add(address);
            } else if (side[0].startsWith("%")) {
                configuration.modules.put(address, new FlipFlop(address, destinations));
            } else {
                configuration.modules.put("broadcaster", new Broadcast(destinations));
            }
        }

        for (String conjunction : conjunctions) {
            for (String source : configuration.targets.getOrDefault(conjunction, new ArrayList<>())) {
                var module = (Conjunction) configuration.modules.get(conjunction);
                module.addInput(source);
            }
        }

        return configuration;
    }

    public long pressButton(long times) {
        long i = 0;
        long lowCount = 0;
        long highCount = 0;

        while (i < times) {
            List<Pulse> pulses = modules.get("broadcaster").receive("button", false);
            lowCount += 1;

            while (!pulses.isEmpty()) {
                Pulse pulse = pulses.remove(0);

                if (pulse.signal()) {
                    highCount++;
                } else {
                    lowCount++;
                }

                if (pulse.destination().equals("rx")) {
                    continue;
                }

                var module = modules.get(pulse.destination());
                pulses.addAll(module.receive(pulse.source(), pulse.signal()));
            }

            i++;
        }

        return lowCount * highCount;
    }

    public long pressButtonUntilRx() {
        Map<String, Long> cycles = new HashMap<>();
        String targetAddress = targets.get("rx").get(0);

        long i = 1;
        while (cycles.size() != targets.get(targetAddress).size()) {
            List<Pulse> pulses = modules.get("broadcaster").receive("button", false);

            while (!pulses.isEmpty()) {
                Pulse pulse = pulses.remove(0);

                if (pulse.destination().equals("rx")) {
                    continue;
                }

                if (pulse.destination().equals(targetAddress) && pulse.signal()) {
                    var value = cycles.getOrDefault(pulse.source(), 0L);
                    cycles.put(pulse.source(), i - value);
                }

                pulses.addAll(modules.get(pulse.destination()).receive(pulse.source(), pulse.signal()));
            }

            i++;
        }

        return cycles.values().stream().reduce(1L, MathHelper::lcm);
    }
}
