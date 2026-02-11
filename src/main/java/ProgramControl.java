import java.io.IOException;
import java.util.ArrayList;

public class ProgramControl {
    //return list of files, from Team Member B, Nathan Wattier
    public static String listFiles() throws IOException {
        StringBuilder out= new StringBuilder();
        FileHandler handler = new FileHandler();
        ArrayList<String> list = handler.getFilenames();
        for (int i=0; i < list.size(); ++i){
            out.append(String.format("%02d", (i + 1))).append(" ").append(list.get(i)).append("\n");
        }
        return out.toString();
    }

    //return contents of file using default key
    public static String retrieve(int num) throws IOException {
        FileHandler handler = new FileHandler();
        String filename = handler.getFilenames().get(num);
        String contents = handler.getContents(filename);
        return Cipher.decipher(contents);
    }

    //return contents of file corresponding to the int num,
    //deciphered from Team Member D, Ellie Kim
    public static String retrieve(int num, String key) throws IOException {
        FileHandler handler = new FileHandler();
        String filename = handler.getFilenames().get(num);
        String contents = handler.getContents(filename);
        return Cipher.decipher(contents, key);
    }
}
