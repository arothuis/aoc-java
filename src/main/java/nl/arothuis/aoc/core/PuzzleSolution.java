package nl.arothuis.aoc.core;

public interface PuzzleSolution<T, U> {
    T solveA(String input);

    U solveB(String input);
}
