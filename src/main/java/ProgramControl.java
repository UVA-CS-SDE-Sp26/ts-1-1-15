import java.io.IOException;
import java.util.ArrayList;

public class ProgramControl {
    //return list of files, from Team Member B, Nathan Wattier
    public static String listFiles() {
        StringBuilder out= new StringBuilder();
        FileHandler handler = new FileHandler();
        try {
            ArrayList<String> list = handler.getFilenames();
            for (int i=0; i < list.size(); ++i){
                out.append(String.format("%02d", (i + 1))).append(" ").append(list.get(i)).append("\n");
            }
            return out.toString();
        }
        catch (Exception e){
            return "Error: "+e;
        }
    }

    //return contents of file using default key
    public static String retrieve(int num) throws IOException {
        FileHandler handler = new FileHandler();
        try{
            //String filename = handler.getFilenames().get(num-1);
            String filename = handler.getFilenames(num-1);
            if (filename == null){
                return "";
            }
            String contents = handler.getContents(filename);
            return Cipher.decipher(contents);
        }
        catch (Exception e){
            return "Error: "+e;
        }
    }

    //return contents of file corresponding to the int num,
    //deciphered from Team Member D, Ellie Kim
    public static String retrieve(int num, String key) throws IOException {
        FileHandler handler = new FileHandler();
        try {
            //String filename = handler.getFilenames().get(num-1);
            String filename = handler.getFilenames(num-1);
            if (filename == null){
                return "";
            }
            String contents = handler.getContents(filename);
            return Cipher.decipher(contents, key);
        }
        catch (Exception e){
            return "Error: "+e;
        }
    }
}
