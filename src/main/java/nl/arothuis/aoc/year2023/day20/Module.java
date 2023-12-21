package nl.arothuis.aoc.year2023.day20;

import java.util.List;

public interface Module {
    List<Pulse> receive(String source, boolean signal);
}
