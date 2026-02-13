
public class Userinterface {


    public static String determineOutput(String[] arguments) throws IllegalArgumentException {

        if (arguments.length == 0) {
                return ProgramControl.listFiles();
        }

        else if (arguments.length == 1) {
            try {
                return ProgramControl.retrieve(Integer.parseInt(arguments[0]));
            }

            catch (NumberFormatException e) {
                return "Error: First argument must be the number of the file you wish to decipher.";
            }
        }

        else if (arguments.length == 2) {
            try {
                return ProgramControl.retrieve(Integer.parseInt(arguments[0]), arguments[1]);
            }

            catch (NumberFormatException e) {
                return "Error: First argument must be the number of the file you wish to decipher.";
            }
        }

        else {
            throw new IllegalArgumentException("Attempted to run program with too many arguments.");
        }
    }

}


