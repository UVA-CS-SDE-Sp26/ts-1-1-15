public class Userinterface {


    public static String determineOutput(String[] arguments) throws IllegalArgumentException {
        //Determine which output based on numArgs
        if (arguments.length == 0)  {
            return ProgramControl.listFiles();
        }
        
        else if (arguments.length == 1)  {
            //Also check type of argument?
            return ProgramControl.retrieve(Integer.parseInt(arguments[0]));
        }
        
        else if (arguments.length == 2)  {
            //Also check type of argument?
            return ProgramControl.retrieve(Integer.parseInt(arguments[0]), arguments[1]);
        }
        
        else {
            throw new IllegalArgumentException("Attempted to run program with too many arguments.");
        }
    }

}
