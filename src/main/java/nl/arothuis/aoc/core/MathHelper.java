package nl.arothuis.aoc.core;

public interface MathHelper {
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}
