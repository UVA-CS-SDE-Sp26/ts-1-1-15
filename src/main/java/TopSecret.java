/**
 * Commmand Line Utility
 */
public class TopSecret {

    public static void main(String[] args) {

        if (args.length == 0)  {
            ProgramControl.listFiles();
        }

        else if (args.length == 1)  {
            //Also check type of argument?
            ProgramControl.retrieve(Integer.parseInt(args[0]));
        }

        else if (args.length == 2)  {
            //Also check type of argument?
            ProgramControl.retrieve(Integer.parseInt(args[0]), args[1]);
        }

        else {
            throw new IllegalArgumentException("Attempted to run program with too many arguments.");
        }
    }


}
