package nl.arothuis.aoc.year2024.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import nl.arothuis.aoc.core.PuzzleSolution;

public class Solution implements PuzzleSolution<Long, Long> {
    private static BinaryOperator<Long> mul = (a, b) -> a * b;
    private static BinaryOperator<Long> add = (a, b) -> a + b;
    private static BinaryOperator<Long> app =(a, b) -> Long.valueOf(a.toString() + b.toString());

    @Override
    public Long solveA(String input) {
        return parseEquations(input)
            .parallelStream()
            .filter(eq -> canBeEvaluated(List.of(mul, add), eq))
            .mapToLong(eq -> eq.get(0))
            .sum();
    }

    @Override
    public Long solveB(String input) {
        return parseEquations(input)
            .parallelStream()
            .filter(eq -> canBeEvaluated(List.of(mul, add, app), eq))
            .mapToLong(eq -> eq.get(0))
            .sum();    
        }

    private List<List<Long>> parseEquations(String input) {
        List<List<Long>> equations = new ArrayList<>();

        for (String line : input.split("\n")) {
            String[] parts = line.split(": ");
            List<Long> equation = new ArrayList<>();

            equation.add(Long.valueOf(parts[0]));
            for (String number : parts[1].split(" ")) {
                equation.add(Long.valueOf(number));
            }
            
            equations.add(equation);
        }

        return equations;
    }

    private boolean canBeEvaluated(List<BinaryOperator<Long>> ops, List<Long> equation) { 
        List<Long> numbers = equation.subList(1, equation.size());
        
        // Optimize later ;)
        // e.g. don't even try if parts of the equation were already seen as too large
        for (int i = 0; i < Math.pow(ops.size(), numbers.size() - 1); i++) {
            Long actual = numbers.get(0);

            for (int j = 0; j < numbers.size() - 1; j++) {
                int index = (i / (int) Math.pow(ops.size(), numbers.size() - j - 2)) % ops.size();
                actual = ops.get(index).apply(actual, numbers.get(j + 1));

                if (actual > equation.get(0)) {
                    break;
                }
            }
                        
            if (actual.equals(equation.get(0))) {
                return true;
            }
        }
        
        return false;
    }
}