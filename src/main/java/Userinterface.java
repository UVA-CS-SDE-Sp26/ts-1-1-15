public class Userinterface {


    public static String displayFiles() {
        //print available files
        return ProgramControl.listFiles();
    }

    public static String decipherDefault() {
        //print contents of file using default decipher

    }

    public static String decipherSpecified() {
        //print contents of file using specified decipher
        return "";
    }

    public static String determineOutput(int numArgs) throws IllegalArgumentException {
        //Determine which output based on numArgs
        if (numArgs == 0)  {
            return displayFiles();
        }
        
        else if (numArgs == 1)  {
            //Also check type of argument?
            return decipherDefault();
        }
        
        else if (numArgs == 2)  {
            //Also check type of argument?
            return decipherSpecified();
        }
        
        else {
            throw new IllegalArgumentException("Attempted to run program with too many arguments.");
        }
    }

}
