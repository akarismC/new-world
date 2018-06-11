package com.ea.newworld.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessor {
    private static FileProcessor instance;
    private static String path = "D:\\new-world\\src\\main\\resources\\";
    private String storeFileName = "issues.log";

    private FileProcessor(){}

    public static FileProcessor getInstance(){
        if(instance == null){
            instance = new FileProcessor();
        }
        return instance;
    }

    public String readFile(String filename) throws IOException {
        String result;

        byte[] inputByte = Files.readAllBytes(Paths.get(path + filename));
        result = new String(inputByte);

        return result;
    }

    public void saveIssueToFile(){


    }
}
