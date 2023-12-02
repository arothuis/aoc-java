package nl.arothuis.aoc.core;

import java.nio.file.Files;
import java.nio.file.Paths;

public interface PuzzleInput {
    static String getFromFile(String year, String day) {
        var filePath = "nl/arothuis/aoc/year" + year + "/day" + day + ".txt";
        var resource = PuzzleInput.class
                .getClassLoader()
                .getResource(filePath);

        try {
            return Files.readString(Paths.get(resource.toURI()));
        } catch (Exception e) {
            throw new RuntimeException("Cannot read file: " + filePath, e);
        }
    }
}
