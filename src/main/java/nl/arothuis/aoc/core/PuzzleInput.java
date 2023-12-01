package nl.arothuis.aoc.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PuzzleInput {
    public static String getFromFile(String year, String day) {
        var filePath = "nl/arothuis/aoc/year" + year + "/day" + day + ".txt";
        var inputStream = PuzzleInput.class
                .getClassLoader()
                .getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IllegalArgumentException(
                    String.format("File path '%s' not found", filePath)
            );
        }

        var result = new ByteArrayOutputStream();

        try {
            byte[] buffer = new byte[1024];
            for (int length; (length = inputStream.read(buffer)) != -1; ) {
                result.write(buffer, 0, length);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        return result.toString(StandardCharsets.UTF_8);
    }
}
