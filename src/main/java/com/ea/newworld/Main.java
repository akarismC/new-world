package com.ea.newworld;

import com.ea.newworld.utils.FeatureString;
import com.ea.newworld.utils.FileProcessor;

import java.util.Arrays;

public class Main {
    public static void main(String[] args){
        System.out.println(Arrays.toString(args));
        String content;
        FeatureString featureString = null;
        for (String arg : args
             ) {
            try {
                content = FileProcessor.getInstance().readFile(arg);
            }catch (Exception e){
                System.out.println("exception happened when reading file" );
                e.printStackTrace();
                return;
            }

            if(featureString == null){
                featureString = new FeatureString(content);
                continue;
            }else {
                featureString.updateWithFile(content);
            }

        }

        System.out.println(featureString);

    }
}
