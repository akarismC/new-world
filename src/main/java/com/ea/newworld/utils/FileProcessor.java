package com.ea.newworld.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    private static String path = "D:\\new-world\\src\\main\\resources\\";
    private String storeFileName = "issues.log";

    private FileProcessor(){}

    public static String readFile(String filename) {
        String result="";

        try{
            byte[] inputByte = Files.readAllBytes(Paths.get(path + filename));
            result = new String(inputByte);
        }catch (IOException e){
            System.out.println("exception happened when reading file");
            e.printStackTrace();
            System.exit(0);
        }


        return result;
    }

    public static List<String> readFileByLines(String filename){
        List<String> result = new ArrayList<>();
        try{
            result = Files.readAllLines(Paths.get(path + filename));
        }catch (IOException e){
            System.out.println("exception happened when reading file");
            e.printStackTrace();
            System.exit(0);
        }
        return result;
    }

    public void saveIssueToFile(){


    }
}
