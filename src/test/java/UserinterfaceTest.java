import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserinterfaceTest {

    public ProgramControl mockProgramControl;

    @BeforeEach
    void setUp() {

        mockProgramControl = Mockito.mock(ProgramControl.class);
        Mockito.when(ProgramControl.listFiles()).thenReturn("file1 \n file2 \n file3");



    }

    @Test
    void determineOutput() {

    }

    @Test
    void displayFiles() {

    }

    @Test
    void decipherDefault() {
    }

    @Test
    void dechiperSpecified() {
    }

    @Test
    void tooManyArgs() {
    }

}