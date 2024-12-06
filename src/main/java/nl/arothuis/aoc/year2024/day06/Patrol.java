package nl.arothuis.aoc.year2024.day06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.arothuis.aoc.core.Coordinates;

public class Patrol {
    private Coordinates size;
    private Coordinates start;
    private Set<Coordinates> walls = new HashSet<>();

    public static Patrol from(String input) {
        Patrol patrol = new Patrol();

        int x = 0;
        int y = 0;

        for (String row : input.split("\n")) {
            x = 0;

            for (String cell : row.split("")) {
                if (cell.equals("#")) {
                    patrol.walls.add(new Coordinates(x, y));
                }

                if (cell.equals("^")) {
                    patrol.start = new Coordinates(x, y);
                }

                x++;
            }

            y++;
        }

        patrol.size = new Coordinates(x, y);

        return patrol;
    }

    public List<Coordinates> determineRoute() {
        List<Coordinates> route = new ArrayList<>();
        Coordinates position = start;
        Coordinates direction = Coordinates.northwards();

        while (position.isWithin(Coordinates.origin(), size)) {            
            route.add(position);

            Coordinates next = position.add(direction);
            while (walls.contains(next)) {
                direction = direction.rotateClockwise();
                next = position.add(direction);
            }     

            position = next;
        }

        return route;
    }

    public Set<Coordinates> determineObstacles() {
        List<Coordinates> route = determineRoute();
        Set<Coordinates> obstacles = new HashSet<>();

        // Optimize later ;)
        for (Coordinates candidate : route) {
            walls.add(candidate);
            
            Set<Move> seen = new HashSet<>();
            Coordinates position = start;
            Coordinates direction = Coordinates.northwards();
            
            while (position.isWithin(Coordinates.origin(), size)) {            
                var move = new Move(direction, position);
                if (seen.contains(move)) {
                    obstacles.add(candidate);
                    break;
                }
                
                seen.add(move);
    
                Coordinates next = position.add(direction);
                while (walls.contains(next)) {
                    direction = direction.rotateClockwise();
                    next = position.add(direction);
                }     
    
                position = next;
            }

            walls.remove(candidate);
        }

        return obstacles;
    }
}
