import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class FileHandlerTest{


    @Test
    void getFilenames() {
        String[] fileNames = {"File01.txt", "File02.txt"};
        FileHandler mockFileHandler = Mockito.mock(FileHandler.class);
        when(mockFileHandler.getFilenames()).thenReturn(fileNames);
    }

    @Test
    void testGetFilenames(){
        String fileNames = "File01.txt";
        FileHandler mockFileHandler = Mockito.mock(FileHandler.class);
        when(mockFileHandler.getFilenames(1)).thenReturn(fileNames);
    }

    @Test
    void getContents() {
        String contents = "these are the contentes";
        FileHandler mockFileHandler = Mockito.mock(FileHandler.class);
        when(mockFileHandler.getContents('File01.txt')).thenReturn(contents);
    }
}