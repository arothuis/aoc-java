package nl.arothuis.aoc.year2023.day02;

public class Bag {
    int red;
    int green;
    int blue;

    public Bag() {
    }

    public Bag(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public void addCubeCount(String name, int number) {
        if (name.equals("red")) {
            red = number;
        } else if (name.equals("blue")) {
            blue = number;
        } else if (name.equals("green")) {
            green = number;
        } else {
            throw new RuntimeException("Color not supported: " + name);
        }
    }

    public boolean fitsIn(Bag other) {
        return red <= other.red && green <= other.green && blue <= other.blue;
    }

    public Bag maxCubes(Bag other) {
        return new Bag(Math.max(red, other.red), Math.max(green, other.green), Math.max(blue, other.blue));
    }

    public int power() {
        return red * green * blue;
    }
}
