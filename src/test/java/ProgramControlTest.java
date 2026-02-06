import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

class ProgramControlTest {

    @Test
    void listFiles() {
        // Arrange
        ArrayList<String> mockFiles = new ArrayList<>(Arrays.asList(
                "document1.txt",
                "secret.txt",
                "notes.txt"
        ));

        try (MockedStatic<FileHandler> mockedFileHandler = Mockito.mockStatic(FileHandler.class)) {
            mockedFileHandler.when(FileHandler::getFilenames).thenReturn(mockFiles);
            String result = ProgramControl.listFiles();
            String expected = "01 document1.txt\n02 secret.txt\n03 notes.txt\n";

            assertEquals(expected, result);
            mockedFileHandler.verify(FileHandler::getFilenames, times(1));
        }
    }

    @Test
    void listFilesEmpty() {
        ArrayList<String> emptyList = new ArrayList<>();
        try (MockedStatic<FileHandler> mockedFileHandler = Mockito.mockStatic(FileHandler.class)) {
            mockedFileHandler.when(FileHandler::getFilenames).thenReturn(emptyList);
            String result = ProgramControl.listFiles();
            assertEquals("", result);
        }
    }

    @Test
    void retrieve() {
        ArrayList<String> mockFiles = new ArrayList<>(Arrays.asList(
                "file1.txt",
                "file2.txt"
        ));
        String encryptedContent = "data";
        String decryptedContent = "Hello World";
        try (MockedStatic<FileHandler> mockedFileHandler = Mockito.mockStatic(FileHandler.class);
             MockedStatic<Cipher> mockedCipher = Mockito.mockStatic(Cipher.class)) {
            mockedFileHandler.when(FileHandler::getFilenames).thenReturn(mockFiles);
            mockedFileHandler.when(() -> FileHandler.getContents("file1.txt")).thenReturn(encryptedContent);
            mockedCipher.when(() -> Cipher.decipher(encryptedContent)).thenReturn(decryptedContent);
            String result = ProgramControl.retrieve(0);

            assertEquals(decryptedContent, result);
            mockedFileHandler.verify(() -> FileHandler.getContents("file1.txt"), times(1));
            mockedCipher.verify(() -> Cipher.decipher(encryptedContent), times(1));
        }
    }

    @Test
    void retrieveWithKey() {
        ArrayList<String> mockFiles = new ArrayList<>(Arrays.asList(
                "file1.txt",
                "file2.txt"
        ));
        String encryptedContent = "encrypted stuff";
        String decryptedContent = "secret message";
        String customKey = "secretkey";
        try (MockedStatic<FileHandler> mockedFileHandler = Mockito.mockStatic(FileHandler.class);
             MockedStatic<Cipher> mockedCipher = Mockito.mockStatic(Cipher.class)) {
            mockedFileHandler.when(FileHandler::getFilenames).thenReturn(mockFiles);
            mockedFileHandler.when(() -> FileHandler.getContents("file2.txt")).thenReturn(encryptedContent);
            mockedCipher.when(() -> Cipher.decipher(encryptedContent, customKey)).thenReturn(decryptedContent);
            String result = ProgramControl.retrieve(1, customKey);

            assertEquals(decryptedContent, result);
            mockedFileHandler.verify(() -> FileHandler.getContents("file2.txt"), times(1));
            mockedCipher.verify(() -> Cipher.decipher(encryptedContent, customKey), times(1));
        }
    }
}