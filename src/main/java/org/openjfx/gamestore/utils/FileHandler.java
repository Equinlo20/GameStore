package org.openjfx.gamestore.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
//import org.openjfx.gamestore.App;

public class FileHandler {

    private static final String PATHDIRECTORY = "src/main/resources/files/";
    private static final String FILEEXTENSION = ".txt";

    public static File getFile(String fileName) {
        File file = new File(PATHDIRECTORY, fileName + FILEEXTENSION);

        return file;
    }

    public static String getPathFile(String fileName) {
        File file = getFile(fileName);
        String path = "";
        try {
            path = file.toURI().toURL().getPath();
        } catch (MalformedURLException ex) {
            System.out.println(ex);
        }
        return path;
    }

    public static void deleteFile(String fileName) {
        File file = getFile(fileName);
        file.delete();
    }

    public static boolean createFile(String fileName) {
        File file = getFile(fileName);
        boolean created = false;
        try {
            created = file.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return created;
    }

    public static List<String> getDataFIle(String fileName) {
        File file = getFile(fileName);

        List<String> data = new ArrayList<>();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();

            while (line != null) {
                data.add(line);
                line = br.readLine();
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        
        return data;
    }
    
    public static boolean addDataFile(List<String> data, String fileName){
        boolean success = false;
        File file = getFile(fileName);
        if (!file.exists()) {
            createFile(fileName);
        }
        
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (int i = 0; i < data.size(); i++) {
                if (i < data.size() - 1) {
                    bw.write(data.get(i) + "\n");
                }else{
                    bw.write(data.get(i));
                }
            }
            
            bw.close();
            fw.close();
            success = true;
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        return success;
    }
    
    public static boolean updateDataFile(List<String> data,String fileName){
        File file = getFile(fileName);
        
        if (file.exists()) {
            file.delete();
        }
        
       boolean success = addDataFile(data, fileName);
        
        return success;
    }
}
