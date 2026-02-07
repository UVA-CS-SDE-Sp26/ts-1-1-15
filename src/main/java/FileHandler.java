import java.util.ArrayList;

public class FileHandler {

ArrayList<String> FileNames = new ArrayList<>();


public ArrayList<String> getFilenames() {
    return FileNames;

}

public String getFilenames(int num){
    return FileNames.get(num);
}


private String parseFile(String filePath){
    String fileContent = "";
    return fileContent;

}

// creates path then calls parser
public String getContents(String filename) {
    String path = filename;
    return parseFile(path);

}


}
