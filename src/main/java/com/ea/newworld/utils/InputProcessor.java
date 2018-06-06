package com.ea.newworld.utils;

import com.ea.newworld.issue.FeatureString;
import com.ea.newworld.issue.Issue;

import java.io.IOException;

public class InputProcessor {
    private String issueIndicator = "-issue"; // known issue name and input files, pattern "-issue issuename file1.txt file2.txt ..."
    private String logIndicator = "-log"; // the log file to be identified, pattern "-log file.txt"
    private static InputProcessor instance;

    private InputProcessor(){}

    public static InputProcessor getInstance(){
        if(instance == null){
            instance = new InputProcessor();
        }
        return instance;
    }

    private String getIssueName(String[] args){
        String result = null;
        for(int i=0; i<args.length; i++){
            if(args[i].equalsIgnoreCase(issueIndicator)){
                try{
                    result = args[i+1];
                    break;
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Usage: -issue issuename file1.txt file2.txt ... -log log.txt");
                    System.exit(0);
                }
            }
        }

        if(result == null){
            System.out.println("Usage: -issue issuename file1.txt file2.txt ... -log log.txt");
            System.exit(0);
        }
        return result;
    }

    private FeatureString getFeature(String[] args){
        FeatureString result = null;
        for(int i = 0; i < args.length; i++){
            if(args[i].equalsIgnoreCase(issueIndicator)){
                try{
                    String content = FileProcessor.getInstance().readFile(args[i+2]);
                    result = new FeatureString(content);
                    for (int index = i+3; index < args.length; index++){
                        if(args[index].equalsIgnoreCase(logIndicator)){
                            break;
                        }
                        content = FileProcessor.getInstance().readFile(args[index]);
                        result.updateWithFile(content);
                    }
                }catch (IOException e){
                    System.out.println("exception happened when reading file");
                    e.printStackTrace();
                    System.exit(0);
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Usage: -issue issuename file1.txt file2.txt ... -log log.txt");
                    System.exit(0);
                }

                break;
            }
        }

        if(result == null){
            System.out.println("Usage: -issue issuename file1.txt file2.txt ... -log log.txt");
            System.exit(0);
        }

        return result;
    }

    public Issue getIssue(String[] args){
        System.out.println(" *** start to get issue name and feature string *** ");

        // get issue name and input files
        String issueName = getIssueName(args);
        FeatureString featureString = getFeature(args);
        Issue issue = new Issue(issueName, featureString);

        System.out.println(" *** end getting issue name and feature string *** ");

        return issue;
    }

    public String getLogToMatch(String[] args){
        System.out.println(" *** start to get the content of log to match *** ");

        String result = null;
        for(int i = 0; i < args.length; i++){
            if(args[i].equalsIgnoreCase(logIndicator)){
                try {
                    result = FileProcessor.getInstance().readFile(args[i+1]);
                }catch (IOException e){
                    System.out.println("exception happened when reading file");
                    e.printStackTrace();
                    System.exit(0);
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Usage: -issue issuename file1.txt file2.txt ... -log log.txt");
                    System.exit(0);
                }

                break;
            }
        }

        if(result == null){
            System.out.println("Usage: -issue issuename file1.txt file2.txt ... -log log.txt");
            System.exit(0);
        }

        System.out.println(" *** end getting the content of log to match *** ");
        return result;
    }
}
