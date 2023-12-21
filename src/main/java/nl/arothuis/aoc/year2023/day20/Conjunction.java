package nl.arothuis.aoc.year2023.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Conjunction implements Module {
    private final String address;
    private final Map<String, Boolean> inputsReceived = new HashMap<>();
    private final List<String> destinations;

    public Conjunction(String address, List<String> destinations) {
        this.address = address;
        this.destinations = destinations;
    }

    public void addInput(String address) {
        inputsReceived.put(address, false);
    }

    @Override
    public List<Pulse> receive(String source, boolean signal) {
        inputsReceived.put(source, signal);
        boolean outputSignal = !inputsReceived.values().stream().allMatch(Boolean::booleanValue);

        return destinations.stream().map(destination -> new Pulse(address, destination, outputSignal)).toList();
    }
}
