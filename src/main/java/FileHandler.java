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
    public ArrayList<String> getFilenames() {
        try (Stream<Path> stream = Files.list(dataPath)) {
            return stream
                    .filter(Files::isRegularFile) // makes sure we are not accessing a subdirectory

                    /* sorts files alphabetically, if the files all have the same name except for number,
                    this will put them in order: File01.txt, File02.txt etc */
                    .sorted(Comparator.comparing(path -> path.getFileName().toString()))

                    .map(path -> path.getFileName().toString())   // convert Path -> String
                    .collect(Collectors.toCollection(ArrayList::new)); // Collect all Strings into arraylist

        }

        // what if data directory not found
        catch (IOException e) {
            System.out.println("Error reading directory: " + e.getMessage());
            return new ArrayList<>();
        }

        // what if file names could not be read
        catch (Exception e) {
            System.out.println("Unexpected error in getFilenames(): " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }

    }


    // Return the name of the nth file
    public String getFilenames(int n) {
        try {
            ArrayList<String> files = getFilenames();

            //invald index
            if (n < 0 || n >= files.size()) {
                System.err.println("Invalid file index: " + (n+1));
                return null;
            }

            return files.get(n);

        // catch excess errors
        } catch (Exception e) {
            System.out.println("Unexpected error in getFilename(): " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Return contents of a file by name
    public String getContents(String fileName){
        try {
            // possible file name check
            if (fileName == null || fileName.isBlank()) {
                System.out.println("Filename cannot be null or empty.");
                return "";
            }

            Path filePath = dataPath.resolve(fileName);

            // path existance and sub-directory check
            if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
                System.out.println("File not found: " + fileName);
                return "";
            }

            return Files.readString(filePath);

        // since directly access files, needs to catch additional IO exception errors
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return "";

        // catch excess errors
        } catch (Exception e) {
            System.out.println("Unexpected error in getContents(): " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}
