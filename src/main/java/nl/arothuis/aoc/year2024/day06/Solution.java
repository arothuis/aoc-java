package nl.arothuis.aoc.year2024.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nl.arothuis.aoc.core.Coordinates;
import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    @Override
    public Long solveA(String input) {
        Set<Coordinates> walls = new HashSet<>();
        Coordinates guard = new Coordinates(0, 0);

        int x = 0;
        int y = 0;
        for (String row : input.split("\n")) {
            x = 0;

            for (String cell : row.split("")) {
                if (cell.equals("#")) {
                    walls.add(new Coordinates(x, y));
                }

                if (cell.equals("^")) {
                    guard = new Coordinates(x, y);
                }

                x++;
            }

            y++;
        }

        Coordinates size = new Coordinates(x, y);
        Coordinates direction = Coordinates.northwards();

        Set<Coordinates> steps = new HashSet<>();
        while (guard.isWithin(Coordinates.origin(), size)) {
            System.out.println(guard);
            steps.add(guard);
            
            Coordinates next = guard.add(direction);
            while (walls.contains(next)) {
                direction = direction.rotateClockwise();
                next = guard.add(direction);
            } 
                
            guard = next;
        }

        return (long) steps.size();
    }

    @Override
    public Long solveB(String input) {
        return -1L;
    }
}