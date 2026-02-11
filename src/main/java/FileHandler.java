import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {

    private final Path dataPath;

    public FileHandler() {
        // data folder is beside the project folder
        this.dataPath = Paths.get("..", "data").toAbsolutePath().normalize();
    }

    // Return all filenames as ArrayList<String>
    public ArrayList<String> getFilenames() throws IOException {
        try (Stream<Path> stream = Files.list(dataPath)) {
            return stream
                    .filter(Files::isRegularFile)
                    .sorted(Comparator.comparing(path -> path.getFileName().toString()))
                    .map(path -> path.getFileName().toString()) // convert Path -> String
                    .collect(Collectors.toCollection(ArrayList::new));
        }
    }

    // Return the name of the nth file
    public String getFilenames(int n) throws IOException {
        ArrayList<String> files = getFilenames();

        if (n < 0 || n >= files.size()) {
            throw new IndexOutOfBoundsException("Invalid file index: " + n);
        }

        return files.get(n);
    }

    // Return contents of a file by name
    public String getContents(String fileName) throws IOException {
        Path filePath = dataPath.resolve(fileName);

        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }

        return Files.readString(filePath);
    }
}
