import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserinterfaceTest {

    private MockedStatic<ProgramControl> pcMock;
    private String[] test0;
    private String[] test1;
    private String[] test2;
    private String[] test3;


    @BeforeEach
    void setUp() {

        pcMock = Mockito.mockStatic(ProgramControl.class);

        pcMock.when(ProgramControl::listFiles).thenReturn("file1 \n file2 \n file3");

        pcMock.when(() -> ProgramControl.retrieve(1)).thenReturn("The file contents deciphered with default cipher");

        pcMock.when(() -> ProgramControl.retrieve(2, "key1")).thenReturn("The file contents with specified cipher");

        test0 = new String[0];

        test1 = new String[1];
        test1[0] = "01";

        test2 = new String[2];
        test2[0] = "02";
        test2[1] = "key1";

        test3 = new String[3];

    }

    @AfterEach
    void tearDown() {

        pcMock.close();

    }

    @Test
    void determineOutput() {

        assertEquals("file1 \n file2 \n file3", Userinterface.determineOutput(test0));

        assertEquals("The file contents deciphered with default cipher", Userinterface.determineOutput(test1));

        assertEquals("The file contents with specified cipher", Userinterface.determineOutput(test2));

        assertThrows(IllegalArgumentException.class, () -> Userinterface.determineOutput(test3));

    }
}