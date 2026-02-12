import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

public class CipherTest {
    @TempDir
    Path tempDir;

    private Cipher cipher;

    @BeforeEach
    void setUp() {
        cipher = createCipherWithTempDir(tempDir);
    }

    // Helper method to create a Cipher instance with a custom cipher directory
    private Cipher createCipherWithTempDir(Path tempDir) {
        Cipher cipher = new Cipher();
        try {
            var field = Cipher.class.getDeclaredField("ciphersDir");
            field.setAccessible(true);
            field.set(cipher, tempDir);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cipher;
    }

    private void createCipherFile(String filename, String realLine, String cipherLine) throws IOException {
        Path filePath = tempDir.resolve(filename);
        Files.writeString(filePath, realLine + "\n" + cipherLine + "\n");
    }

    // Test: readCipherFile with valid input
    @Test
    @DisplayName("readCipherFile - should read and map cipher correctly")
    void testReadCipherFile() throws Exception {
        createCipherFile("test.txt", "abc", "xyz");
        var map = cipher.readCipherFile("test.txt");
        assertEquals(3, map.size());
        assertEquals('a', map.get('x'));
        assertEquals('b', map.get('y'));
        assertEquals('c', map.get('z'));
    }

    // Test: readCipherFile with missing file
    @Test
    @DisplayName("readCipherFile - should throw exception for missing file")
    void testReadCipherFileMissing() {
        assertThrows(Exception.class, () -> {
            cipher.readCipherFile("nonexistent.txt");
        });
    }

    // Test: readCipherFile with incomplete file
    @Test
    @DisplayName("readCipherFile - should throw exception for incomplete file")
    void testReadCipherFileIncomplete() throws IOException {
        Path filePath = tempDir.resolve("incomplete.txt");
        Files.writeString(filePath, "only one line");
        Exception exception = assertThrows(Exception.class, () -> {
            cipher.readCipherFile("incomplete.txt");
        });
        assertEquals("Missing second line", exception.getMessage());
    }

    // Test: decipher with valid input
    @Test
    @DisplayName("decipher - should decipher text correctly")
    void testDecipher() throws IOException {
        createCipherFile("test.txt", "abc", "xyz");
        String result = cipher.decipher("xyz", "test.txt");
        assertEquals("abc", result);
    }

    // Test: decipher preserves unmapped characters
    @Test
    @DisplayName("decipher - should preserve unmapped characters")
    void testDecipherUnmappedCharacters() throws IOException {
        createCipherFile("test.txt", "abc", "xyz");
        String result = cipher.decipher("x1y2z3", "test.txt");
        assertEquals("a1b2c3", result);
    }

    // Test: decipher with invalid file returns error
    @Test
    @DisplayName("decipher - should return error message for invalid file")
    void testDecipherInvalidFile() {
        String result = cipher.decipher("test", "nonexistent.txt");
        assertEquals("Error: invalid cipher file.", result);
    }

    // Test: decipher with default key
    @Test
    @DisplayName("decipher - should use default key when not specified")
    void testDecipherDefaultKey() throws IOException {
        createCipherFile("key.txt", "abc", "xyz");
        String result = cipher.decipher("xyz");
        assertEquals("abc", result);
    }

    // Test: decipher with empty input
    @Test
    @DisplayName("decipher - should handle empty input")
    void testDecipherEmptyInput() throws IOException {
        createCipherFile("test.txt", "abc", "xyz");
        String result = cipher.decipher("", "test.txt");
        assertEquals("", result);
    }
}
