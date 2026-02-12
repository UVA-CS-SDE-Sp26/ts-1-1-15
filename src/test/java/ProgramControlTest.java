import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;

class ProgramControlTest {

    /*@Test
    void listFiles() {
        ArrayList<String> mockFiles = new ArrayList<>(Arrays.asList(
                "document1.txt",
                "secret.txt",
                "notes.txt"
        ));
        try (MockedConstruction<FileHandler> controller = Mockito.mockConstruction(FileHandler.class)) {
            String result = ProgramControl.listFiles();
            FileHandler createdMock = controller.constructed().get(0);
            when(createdMock.getFilenames()).thenReturn(mockFiles);
            String expected = "01 document1.txt\n02 secret.txt\n03 notes.txt\n";
            assertEquals(expected, result);
            verify(createdMock).getFilenames();
        }
    }*/

    /*@Test
    void listFilesEmpty() {
        ArrayList<String> emptyList = new ArrayList<>();
        try (MockedConstruction<FileHandler> controller = Mockito.mockConstruction(FileHandler.class)) {
            FileHandler createdMock = controller.constructed().get(0);
            when(createdMock.getFilenames()).thenReturn(emptyList);
            String result = ProgramControl.listFiles();
            assertEquals("", result);
        }
    }*/

    /*@Test
    void retrieve() {
        ArrayList<String> mockFiles = new ArrayList<>(Arrays.asList(
                "file1.txt",
                "file2.txt"
        ));
        String encryptedContent = "data";
        String decryptedContent = "Hello World";
        FileHandler mockHandler = Mockito.mock(FileHandler.class);
        when(mockHandler.getFilenames()).thenReturn(mockFiles);
        when(mockHandler.getContents("file1.txt")).thenReturn(encryptedContent);
        try (MockedStatic<Cipher> mockedCipher = Mockito.mockStatic(Cipher.class);
             MockedConstruction<FileHandler> mockedConstruction = Mockito.mockConstruction(FileHandler.class,
                     (mock, context) -> {
                         when(mock.getFilenames()).thenReturn(mockFiles);
                         when(mock.getContents("file1.txt")).thenReturn(encryptedContent);
                     })) {
            mockedCipher.when(() -> Cipher.decipher(encryptedContent)).thenReturn(decryptedContent);
            String result = ProgramControl.retrieve(0);
            assertEquals(decryptedContent, result);
            mockedCipher.verify(() -> Cipher.decipher(encryptedContent), times(1));
        }
    }*/

    /*@Test
    void retrieveWithKey() {
        ArrayList<String> mockFiles = new ArrayList<>(Arrays.asList(
                "file1.txt",
                "file2.txt"
        ));
        String encryptedContent = "encrypted stuff";
        String decryptedContent = "secret message";
        String customKey = "secretkey";
        try (MockedConstruction<FileHandler> mockedConstruction = Mockito.mockConstruction(FileHandler.class,
                (mock, context) -> {
                    when(mock.getFilenames()).thenReturn(mockFiles);
                    when(mock.getContents("file2.txt")).thenReturn(encryptedContent);
                });
             MockedStatic<Cipher> mockedCipher = Mockito.mockStatic(Cipher.class)) {
            mockedCipher.when(() -> Cipher.decipher(encryptedContent, customKey)).thenReturn(decryptedContent);
            String result = ProgramControl.retrieve(1, customKey);
            assertEquals(decryptedContent, result);
            mockedCipher.verify(() -> Cipher.decipher(encryptedContent, customKey), times(1));
        }
    }*/
}