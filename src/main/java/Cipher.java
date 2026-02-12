import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Cipher {

    private static Path ciphersDir = Paths.get("ciphers");

    public static HashMap<Character, Character> readCipherFile(String key) throws Exception{
        File file = ciphersDir.resolve(key).toFile();
        Scanner scanner = new Scanner(file);
   //reads the cipher file and returns the mapping


        if (!scanner.hasNextLine()) {
            scanner.close();
            throw new Exception("Missing first line");
        }
        String realLine = scanner.nextLine();

        if (!scanner.hasNextLine()) {
            scanner.close();
            throw new Exception("Missing second line");
        }
        String cipherLine = scanner.nextLine();


        scanner.close();

       // validateFile(realLine, cipherLine); maybe add later

        HashMap<Character, Character> map = new HashMap<>();

        for (int i = 0; i < cipherLine.length(); i++) { //for mapping the actual real to the cipher
            map.put( cipherLine.charAt(i),realLine.charAt(i) );
        }

        return map;
    }



    public static String decipher(String input){
        return decipher(input, "key.txt");
    }




//decipher using a certain file

    public static String decipher(String input, String keyFile) {
        try {
            HashMap<Character, Character> cipherMap = readCipherFile(keyFile);

            StringBuilder output = new StringBuilder();

            for (char c : input.toCharArray()) {
                if (cipherMap.containsKey(c)) {
                    output.append(cipherMap.get(c));
                } else {
                    output.append(c);
                }
            }
            return output.toString();

        } catch (Exception e) {
            return "Error: invalid cipher file.";
        }
    }





}
