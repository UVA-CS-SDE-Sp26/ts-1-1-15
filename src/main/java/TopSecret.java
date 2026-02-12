/**
 * Commmand Line Utility
 */
public class TopSecret {

    public static void main(String[] args) {

        try {
            System.out.println(Userinterface.determineOutput(args));
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
