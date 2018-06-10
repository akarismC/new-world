package com.ea.newworld.issue;

import java.util.List;

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
//        String feature = featureString.getFeatures();
//        int length = feature.length();
//        int index = 0;
//        for(int i = 0; i < length; i++){
//            index = log.indexOf(feature.charAt(i), index);
//            if(index == -1){
//                System.out.println("Strings unable to match:" + feature.substring(i));
//                return false;
//            }
//            index++;
//        }

        List<String> list = featureString.getList();
        int index = 0;
        for(int i = 0; i < list.size(); i++){
            index = log.indexOf(list.get(i), index);
            if(index == -1){
                System.out.println("Strings unable to match:");
                for(int j = i; j < list.size(); j++){
                    System.out.print(list.get(j));
                }
                System.out.println();
                return false;
            }
            index = index + list.get(i).length();
        }
        return true;
    }
}
