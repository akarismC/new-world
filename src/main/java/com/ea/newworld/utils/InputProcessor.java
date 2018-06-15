package com.ea.newworld.utils;

import java.util.HashMap;
import java.util.List;

public class InputProcessor {

    private InputProcessor(){}

//    param : log file name
//    return : a hashmap of sequence id and log message
    public static HashMap<String, String> divideBySequenceId(String filename){
        HashMap<String, String> strings = new HashMap<>();

        List<String> content = FileProcessor.readFileByLines(filename);

        for (String s : content){
            String[] data = getIdAndLog(s);
            if(strings.containsKey(data[0])){
                String val = strings.get(data[0]);
                strings.put(data[0], val+data[1]);
            }else{
                strings.put(data[0], data[1]);
            }
        }

        return strings;
    }

    private static String[] getIdAndLog(String line){
        if(!line.matches("\\[SEQID:\\d+\\] .+")){
            System.out.println("invalid log: " + line);
        }

        int idBegin = line.indexOf("[SEQID:");
        int idEnd = line.indexOf("]");

        String[] result = new String[2];
        result[0] = line.substring(idBegin+7, idEnd);
        result[1] = line.substring(idEnd+2)+ "\n";

        return result;
    }
}
