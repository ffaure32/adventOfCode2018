import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class InputLoader {
    public static List<String> loadInputList(String fileName) {
        try {
        Path path = Paths.get(InputLoader.class.getClassLoader()
                .getResource(fileName).toURI());
            return Files.readAllLines(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<String> loadInputStream(String fileName) {
        try {
            Path path = Paths.get(InputLoader.class.getClassLoader()
                    .getResource(fileName).toURI());
            return Files.lines(path);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadInputAsString(String fileName) {
        try {
            Path path = Paths.get(InputLoader.class.getClassLoader()
                    .getResource(fileName).toURI());
            return new String(Files.readAllBytes(path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
