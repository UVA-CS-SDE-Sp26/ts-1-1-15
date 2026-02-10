import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserinterfaceTest {

    MockedStatic<ProgramControl> pcMock;
    String[] test1;
    String[] test2;


    @BeforeEach
    void setUp() {
        pcMock = Mockito.mockStatic(ProgramControl.class);
        pcMock.when(ProgramControl::listFiles).thenReturn("file1 \n file2 \n file3");
        pcMock.when(() -> ProgramControl.retrieve(1)).thenReturn("The file contents deciphered with default cipher");
        pcMock.when(() -> ProgramControl.retrieve(1, "Special key")).thenReturn("The file contents with specified cipher");

        test1 = new String[1];
        test1[0] = "01";

        test2 = new String[2];
        test2[0] = "02";
        test2[1] = "key1";




    }

    @Test
    void determineOutput() {

        assertEquals(Userinterface.determineOutput(test1), ProgramControl.listFiles());
        assertEquals(Userinterface.determineOutput(1), ProgramControl.retrieve(1));
        assertEquals(Userinterface.determineOutput(2), ProgramControl.retrieve(1, "Special key"));
        assertThrows(IllegalArgumentException.class, () -> Userinterface.determineOutput(-1));

    }
    }