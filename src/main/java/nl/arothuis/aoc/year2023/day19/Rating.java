package nl.arothuis.aoc.year2023.day19;

public record Rating(int x, int m, int a, int s) {
    public static Rating fromString(String input) {
        var categories = input.substring(1, input.length() - 1).split(",");

        return new Rating(
                Integer.parseInt(categories[0].split("=")[1]),
                Integer.parseInt(categories[1].split("=")[1]),
                Integer.parseInt(categories[2].split("=")[1]),
                Integer.parseInt(categories[3].split("=")[1])
        );
    }

    public int get(String input) {
        return switch (input) {
            case "x" -> x;
            case "m" -> m;
            case "a" -> a;
            case "s" -> s;
            default -> throw new RuntimeException("Category not supported");
        };
    }
}
