import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class CipherTest {

    @Test
    void testSimpleDecipher() throws Exception {

        try (MockedStatic<FileHandler> mockedFileHandler =
                     Mockito.mockStatic(FileHandler.class)) {

            // fake cipher key file contents
            mockedFileHandler.when(() ->
                            FileHandler.getContents("ciphers.key.txt"))
                    .thenReturn("abcde\nbcdea");

            String input = "bcdea";
            String expected = "abcde";

            String actual = Cipher.decipher(input);

            assertEquals(expected, actual);
        }
    }
}
