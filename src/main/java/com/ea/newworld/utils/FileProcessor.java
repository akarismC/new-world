package com.ea.newworld.utils;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileProcessor {
    private static FileProcessor instance;
    private static String path = "D:\\new-world\\src\\main\\resources\\";

    private FileProcessor(){}

    public static FileProcessor getInstance(){
        if(instance == null){
            instance = new FileProcessor();
        }
        return instance;
    }

    public String readFile(String filename) throws Exception{
        String result;

            byte[] inputByte = Files.readAllBytes(Paths.get(path + filename));
            result = new String(inputByte);
            System.out.println(result);


        return result;
    }
}
