package com.ea.newworld;

import com.ea.newworld.issue.Issue;
import com.ea.newworld.utils.InputProcessor;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    private static Issue[] knownIssues = {
            new Issue("Issue_A",
                    "ENTER; fusionhandler HP; handlerPath:/pids/[0-9]+/cartCheckout\n" +
                            "ENTER; PostCartCheckoutHandler.preProcess(..) HP;\n" +
                            "ENTER; CartCheckoutServiceImpl.preCheckout(..) HP;\n" +
                            "ENTER; CartCheckoutServiceImpl.validateCommon(..) HP;\n" +
                            "RETURN; CartCheckoutServiceImpl.validateCommon(..);com.ea.fusion.commerce.CartChangedException\n" +
                            "RETURN; CartCheckoutServiceImpl.preCheckout(..);com.ea.fusion.commerce.CartChangedException\n" +
                            "RETURN; PostCartCheckoutHandler.preProcess(..);com.ea.fusion.commerce.CartChangedException\n" +
                            "RETURN; fusionhandler\n")
    } ;
    public static void main(String[] args){
        System.out.println(Arrays.toString(args));
        long startTime = System.currentTimeMillis();


        HashMap<String, String> map= InputProcessor.divideBySequenceId(args[0]);

        int unknownId = 0;
        List<Issue> unknownIssues = new ArrayList<>();
        List<Issue> knownAndAlreadyFound = new ArrayList<>();
        for(Map.Entry<String, String> entry: map.entrySet()){
            if(knownIssues[0].match(entry.getValue())){
                if(!knownAndAlreadyFound.contains(knownIssues[0])){
                    knownAndAlreadyFound.add(knownIssues[0]);
                    System.out.println("Found known issue: " + knownIssues[0].getName());
                    System.out.println("Feature string:");
                    System.out.println(knownIssues[0].getFeatureString());
                    System.out.println();
                }
            }else{
                String name = "unknown_issue_ " + unknownId;
                Boolean found = false;
                for(Issue issue : unknownIssues){
                    if(issue.match(entry.getValue())){
                        found = true;
//                        System.out.println("Found unknown issue: " + issue.getName());
//                        System.out.println("SequenceId:" + entry.getKey());
//                        System.out.println("Feature string:");
//                        System.out.println(issue.getFeatureString());
//                        System.out.println();
                        break;
                    }
                }
                if(!found){
                    Issue unknownIssue = new Issue(name, entry.getValue());
                    unknownIssues.add(unknownIssue);

                    System.out.println("Found unknown issue: " + name);
                    System.out.println("Feature string:");
                    System.out.println(entry.getValue());
                    System.out.println();
                    unknownId++;
                }

            }
        }


        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time cost:" + TimeUnit.MILLISECONDS.toMinutes(duration));
    }
}
