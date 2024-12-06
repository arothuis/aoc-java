package nl.arothuis.aoc.year2024.day06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;

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

        Set<Coordinates> obstacles = new HashSet<>();
        Set<Coordinates> steps = new HashSet<>();
        Map<Coordinates, Map<Long, List<Range>>> ranges = new HashMap<>();

        while (guard.isWithin(Coordinates.origin(), size)) {
            steps.add(guard);

            if (canPlaceObstacle(ranges, direction, guard)) {
                obstacles.add(guard.add(direction));
            }
            
            Coordinates next = guard.add(direction);
            while (walls.contains(next)) {
                addToRanges(ranges, walls, size, direction, guard);
                direction = direction.rotateClockwise();
                next = guard.add(direction);
            } 
                
            guard = next;
        }

        System.out.println(obstacles);
        System.out.println(obstacles.size());

        return (long) steps.size();
    }

    @Override
    public Long solveB(String input) {
        return -1L;
    }

    private void addToRanges(
        Map<Coordinates, Map<Long, List<Range>>> ranges,
        Set<Coordinates> walls,
        Coordinates size,
        Coordinates direction,
        Coordinates position
    ) {
        Long axisPosition = direction.x() == 0 ? position.x() : position.y();
        Map<Long, List<Range>> currentRanges = ranges.getOrDefault(direction, new HashMap<>());
        List<Range> currentLine = currentRanges.getOrDefault(axisPosition, new ArrayList<>());

        Long start = direction.x() == 0 ? position.y() : position.x();
        Long end = start;
        
        // System.out.println();
        // System.out.println(direction);
        
        while (position.isWithin(Coordinates.origin(), size)) {
            // System.out.println(position);
            Long step = direction.x() == 0 ? position.y() : position.x();
            
            // if (currentLine.stream().anyMatch(r -> r.contains(step))) {
            //     break;
            // }

            if (walls.contains(position)) {
                break;
            }

            end = step;
            position = position.add(direction.inverse());
        }

        currentLine.add(Range.of(start, end));
        currentRanges.put(axisPosition, currentLine);
        ranges.put(direction, currentRanges);
    }

    private boolean canPlaceObstacle(
        Map<Coordinates, Map<Long, List<Range>>> ranges,
        Coordinates direction,
        Coordinates position
    ) {
        var targetDirection = direction.rotateClockwise();
        // System.out.println(position);
        // // System.out.println(ranges);
        // System.out.println(targetDirection);
        
        var step = targetDirection.x() == 0 ? position.y() : position.x();
        var axis = targetDirection.x() == 0 ? position.x() : position.y();

        var range = ranges
            .getOrDefault(targetDirection, Map.of())
            .getOrDefault(axis, List.of());

        // System.out.println(step);
        // System.out.println(ranges.get(targetDirection));
        // System.out.println(range);
        return range.stream().anyMatch(r -> r.contains(step));
    }
}