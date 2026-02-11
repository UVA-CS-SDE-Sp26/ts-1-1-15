import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest{

    // create a fake directory to access so filehandler can be tested properly
    private FileHandler createFileHandlerWithTempDir(Path tempDir) {
        FileHandler fileHandler = new FileHandler();
        try {
            var field = FileHandler.class.getDeclaredField("dataPath");
            field.setAccessible(true);
            field.set(fileHandler, tempDir);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fileHandler;
    }

    // helper to delete the fake directory
    private void deleteDirectory(Path path) throws IOException {
        Files.walk(path)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(p -> p.toFile().delete());
    }


    @Test
    void getFilenames_returnsSortedFilenames() throws IOException {
        // name of folder in root of project
        Path tempDir = Files.createTempDirectory("data");
        Files.writeString(tempDir.resolve("File02.txt"), "Content 2");
        Files.writeString(tempDir.resolve("File01.txt"), "Content 1");

        // make directory with path data
        FileHandler fileHandler = createFileHandlerWithTempDir(tempDir);

        ArrayList<String> filenames = fileHandler.getFilenames();

        assertEquals(2, filenames.size());
        assertEquals("File01.txt", filenames.get(0));
        assertEquals("File02.txt", filenames.get(1));

        deleteDirectory(tempDir);
    }

    @Test
    void getFilenames_withIndex_returnsCorrectFile() throws IOException {
        // name of folder in root with project
        Path tempDir = Files.createTempDirectory("data");
        Files.writeString(tempDir.resolve("File01.txt"), "Content 1");
        Files.writeString(tempDir.resolve("File02.txt"), "Content 2");

        // create temporary directory
        FileHandler fileHandler = createFileHandlerWithTempDir(tempDir);

        String filename = fileHandler.getFilenames(1);

        // check if File02 is the second file
        assertEquals("File02.txt", filename);

        deleteDirectory(tempDir);
    }

    @Test
    void getFilenames_withInvalidIndex_throwsException() throws IOException {
        // create temporary directory
        Path tempDir = Files.createTempDirectory("data");
        FileHandler fileHandler = createFileHandlerWithTempDir(tempDir);

        // what if index > amount files
        assertThrows(IndexOutOfBoundsException.class, () ->
                fileHandler.getFilenames(10)
        );

        deleteDirectory(tempDir);
    }

    @Test
    void getContents_returnsFileContent() throws IOException {
        // make temp directory
        Path tempDir = Files.createTempDirectory("data");
        Files.writeString(tempDir.resolve("File01.txt"), "Content 1");
        FileHandler fileHandler = createFileHandlerWithTempDir(tempDir);

        String contents = fileHandler.getContents("File01.txt");

        // check if file contents accessed properlu
        assertEquals("Content 1", contents);

        deleteDirectory(tempDir);
    }

    @Test
    void getContents_invalidFile_throwsException() throws IOException {
        // make temp direcotry
        Path tempDir = Files.createTempDirectory("data");
        FileHandler fileHandler = createFileHandlerWithTempDir(tempDir);

        assertThrows(IllegalArgumentException.class, () ->
                fileHandler.getContents("DoesNotExist.txt")
        );

        deleteDirectory(tempDir);
    }


}