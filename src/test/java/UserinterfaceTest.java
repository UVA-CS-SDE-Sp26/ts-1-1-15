import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserinterfaceTest {

    private ProgramControl mockArgs;

    @BeforeEach
    void setUp() {

        mockArgs = Mockito.mock(ProgramControl.class);
        Mockito.when(ProgramControl.listfiles()));


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