package nl.arothuis.aoc.year2023.day20;

import java.util.ArrayList;
import java.util.List;

public class FlipFlop implements Module {
    private final String address;
    private final List<String> destinations;
    private boolean isOn = false;

    public FlipFlop(String address, List<String> destinations) {
        this.address = address;
        this.destinations = destinations;
    }

    @Override
    public List<Pulse> receive(String source, boolean signal) {
        if (signal) {
            return new ArrayList<>();
        }

        isOn = !isOn;

        return destinations.stream().map(destination -> new Pulse(address, destination, isOn)).toList();
    }
}
