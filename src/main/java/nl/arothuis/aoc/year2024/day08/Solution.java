package nl.arothuis.aoc.year2024.day08;

import java.util.stream.Stream;

import nl.arothuis.aoc.core.Coordinates;
import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {

    @Override
    public Long solveA(String input) {
        return (long) AntennaMap.from(input)
            .calculateAntinodes((antennae, size) -> antennae
                .parallelStream()
                .flatMap(source -> antennae.parallelStream()
                    .filter(target -> !source.equals(target))
                    .map(target -> target.add(target.subtract(source)))
                    .filter(next -> next.isWithin(Coordinates.origin(), size))
                )
            )
            .size();
    }

    @Override
    public Long solveB(String input) {
        return (long) AntennaMap.from(input)
            .calculateAntinodes((antennae, size) -> antennae
                .parallelStream()
                .flatMap(source -> antennae.parallelStream()
                    .filter(target -> !source.equals(target))
                    .flatMap(target -> 
                        Stream.concat(
                            Stream.of(source),
                            Stream.iterate(target.add(target.subtract(source)), next -> next.add(target.subtract(source)))
                                .takeWhile(next -> next.isWithin(Coordinates.origin(), size))
                        )
                    )
                    )
                )
            .size();
    }
}