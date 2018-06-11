package com.ea.newworld;

import com.ea.newworld.issue.FeatureString;
import com.ea.newworld.issue.Issue;
import com.ea.newworld.utils.FileProcessor;
import com.ea.newworld.utils.InputProcessor;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        System.out.println(Arrays.toString(args));
        long startTime = System.currentTimeMillis();

        Issue issue = InputProcessor.getInstance().getIssue(args);

        System.out.println(issue.getFeatureString());

        String toMatch = InputProcessor.getInstance().getLogToMatch(args);

        if(issue.match(toMatch)){
            System.out.println("The log matches issue: " + issue.getName());
        }else{
            System.out.println("The log does not match issue: " + issue.getName());
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time cost:" + TimeUnit.MILLISECONDS.toMinutes(duration));
    }
}
