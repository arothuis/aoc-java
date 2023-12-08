package nl.arothuis.aoc.year2023.day08;

public class Journey {
    private final String route;
    private int stepsTaken = 0;

    public Journey(String route) {
        this.route = route;
    }

    public int next() {
        var step = route.charAt(stepsTaken % route.length()) == 'L' ? 0 : 1;
        stepsTaken++;

        return step;
    }

    public int getStepsTaken() {
        return stepsTaken;
    }
}
