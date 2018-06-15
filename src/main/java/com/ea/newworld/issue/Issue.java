package com.ea.newworld.issue;

import java.util.List;

public class Issue {
    private String issueName;
    private String featureString;

    public Issue(String name, String features){
        issueName = name;
        featureString = features;
    }

    public String getName(){
        return issueName;
    }

    public String getFeatureString(){
        return featureString;
    }

    public boolean match(String log){

        return featureString.equalsIgnoreCase(log);
    }
}
