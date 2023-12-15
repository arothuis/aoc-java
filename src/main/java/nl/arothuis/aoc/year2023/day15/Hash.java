package nl.arothuis.aoc.year2023.day15;

public class Hash {
    private Hash() {
    }

    public static int calculate(String input) {
        int output = 0;

        for (int ascii : input.toCharArray()) {
            output += ascii;
            output *= 17;
            output %= 256;
        }

        return output;
    }
}
