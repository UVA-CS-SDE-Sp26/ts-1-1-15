import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserinterfaceTest {

    MockedStatic<ProgramControl> pcMock;


    @BeforeEach
    void setUp() {
        pcMock = Mockito.mockStatic(ProgramControl.class);
        pcMock.when(ProgramControl::listFiles).thenReturn("file1 \n file2 \n file3");
        pcMock.when(() -> ProgramControl.retrieve(1)).thenReturn("The file contents deciphered with default cipher");
        pcMock.when(() -> ProgramControl.retrieve(1, "Special cipher")).thenReturn("The file contents with specified cipher");
    }


    @Test
    void determineOutput() {

        assertEquals(Userinterface.determineOutput(0), ProgramControl.listFiles());
        assertEquals(Userinterface.determineOutput(1), ProgramControl.retrieve(1));
        assertEquals(Userinterface.determineOutput(2), ProgramControl.retrieve(1, "Special cipher"));
        assertThrows(IllegalArgumentException.class, () -> Userinterface.determineOutput(-1));

    }

    @Test
    void displayFiles() {
        try (MockedStatic<ProgramControl> pcMock = Mockito.mockStatic(ProgramControl.class)) {
            assertEquals(Userinterface.displayFiles(), ProgramControl.listFiles());
        }

    }

    @Test
    void decipherDefault() {
        try (MockedStatic<ProgramControl> pcMock = Mockito.mockStatic(ProgramControl.class)) {
            assertEquals(Userinterface.decipherDefault(), ProgramControl.retrieve(1));
        }
    }

    @Test
    void decipherSpecified() {
        try (MockedStatic<ProgramControl> pcMock = Mockito.mockStatic(ProgramControl.class)) {
            assertEquals(Userinterface.decipherSpecified(), ProgramControl.retrieve(1, "Special cipher"));
        }
    }

}