
package org.openjfx.gamestore.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
//import org.openjfx.gamestore.App;

public class FileHandler {
    private static final String PATHDIRECTORY = "src/main/resources/files/";
    private static final String FILEEXTENSION = ".txt";
    
    public static File getFile(String fileName){
        File file = new File(PATHDIRECTORY, fileName + FILEEXTENSION);
        
        return file;
    }
    
    public static String getPathFile(String fileName){
        File file = getFile(fileName);
        String path = "";
        try {
            path = file.toURI().toURL().getPath();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return path;
    }
    
    public static void deleteFile(String fileName){
        File file = getFile(fileName);
        file.delete();
    }
    
    public static boolean createFile(String fileName){
        File file = getFile(fileName);
        boolean created = false;
        try {
            created = file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return created;
    }
}
