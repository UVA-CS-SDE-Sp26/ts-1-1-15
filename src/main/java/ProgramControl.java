import java.util.ArrayList;

public class ProgramControl {
    //return list of files, from Team Member B, Nathan Wattier
    public static String listFiles(){
        StringBuilder out= new StringBuilder();
        ArrayList<String> list = FileHandler.getFilenames();
        for (int i=0; i < list.size(); ++i){
            out.append(String.format("%02d", (i + 1))).append(" ").append(list.get(i));
        }
        return out.toString();
    }

    //return contents of file using default key
    public static String retrieve(int num){
        String filename = FileHandler.getFilenames().get(num);
        String contents = FileHandler.getContents(filename);
        return Cipher.decipher(contents);
    }

    //return contents of file corresponding to the int num,
    //deciphered from Team Member D, Ellie Kim
    public static String retrieve(int num, String key){
        String filename = FileHandler.getFilenames().get(num);
        String contents = FileHandler.getContents(filename);
        return Cipher.decipher(contents, key);
    }
}
