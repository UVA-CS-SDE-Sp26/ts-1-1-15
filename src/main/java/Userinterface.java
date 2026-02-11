import javax.sound.midi.SysexMessage;
import java.io.IOException;

public class Userinterface {


    public static String determineOutput(String[] arguments) throws IllegalArgumentException {
        if (arguments.length == 0) {
            try {
                return ProgramControl.listFiles();
            }
            catch (IOException e){
                System.out.println(e);
            }
        }

        else if (arguments.length == 1) {
            try {
                return ProgramControl.retrieve(Integer.parseInt(arguments[0]));
            }

            catch (NumberFormatException e) {
                System.out.println("Error: Please enter the number corresponding to the file you wish to decipher.");
            }
            catch (IOException e){
                System.out.println(e);
            }
        }

        else if (arguments.length == 2) {
            try {
                return ProgramControl.retrieve(Integer.parseInt(arguments[0]), arguments[1]);
            }

            catch (NumberFormatException e) {
                System.out.println("Error: First argument must be the number of the file.");
            }
            catch (IllegalArgumentException e) { // assuming retrieve throws this for invalid key
                System.err.println("Error: The key '" + arguments[1] + "' is invalid.");
            }
            catch (IOException e){
                System.out.println(e);
            }
        }

        else {
            throw new IllegalArgumentException("Attempted to run program with too many arguments.");
        }
        return "Program failed due to invalid input.";
    }

}


