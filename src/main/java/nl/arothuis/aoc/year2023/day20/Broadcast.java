package nl.arothuis.aoc.year2023.day20;

import java.util.ArrayList;
import java.util.List;

public class Broadcast implements Module {
    private final String address = "broadcaster";
    private final List<String> destinations;

    public Broadcast(List<String> destinations) {
        this.destinations = destinations;
    }

    @Override
    public List<Pulse> receive(String source, boolean signal) {
        return new ArrayList<>(destinations.stream().map(destination -> new Pulse(address, destination, signal)).toList());
    }
}
