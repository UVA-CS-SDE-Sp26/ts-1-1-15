import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserinterfaceTest {

    public String availableFiles;
    public String defaultDecipheredContents;
    public String specificDecipheredContents;

    @BeforeEach
    void setUp() {

        availableFiles = "file1 \n file2 \n file 3";
        defaultDecipheredContents = "Hello World!";
        specificDecipheredContents = "Good Morning!";

        Userinterface mockArgs = Mockito.mock(Userinterface.class);
        Mockito.when(Userinterface.determineOutput(0)).thenReturn(availableFiles);
        Mockito.when(Userinterface.determineOutput(1)).thenReturn(defaultDecipheredContents);
        Mockito.when(Userinterface.determineOutput(2)).thenReturn(specificDecipheredContents);

    }

    @Test
    void determineOutput() {




    }

    @Test
    void noArg() {

    }

    @Test
    void oneArg() {
    }

    @Test
    void twoArg() {
    }

    @Test
    void moreArg() {
    }

}