package com.ea.newworld.issue;

public class Issue {
    private String issueName;
    private FeatureString featureString;

    public Issue(String name, FeatureString features){
        issueName = name;
        featureString = features;
    }

    public String getName(){
        return issueName;
    }

    public FeatureString getFeatureString(){
        return featureString;
    }

    public boolean match(String log){
        String feature = featureString.getFeatures();
        int length = feature.length();
        int index = 0;
        for(int i = 0; i < length; i++){
            index = log.indexOf(feature.charAt(i), index);
            if(index == -1){
                return false;
            }
            index++;
        }

        return true;
    }
}
