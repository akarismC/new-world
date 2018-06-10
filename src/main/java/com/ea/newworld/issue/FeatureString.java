package com.ea.newworld.issue;

import com.ea.newworld.utils.Hirschberg;

import java.util.*;

public class FeatureString {
    private String features;
    private List<String> featureList;
    private HashMap<String, String> classMap = new HashMap<String, String>();

    public FeatureString(String string){
        featureList = preprocess(string);
        //features = preprocess(string);
    }

    public void updateWithFile(String input){
        System.out.println(" *** start to update feature string with new log *** ");

        List<String> list = preprocess(input);
        int m = featureList.size();
        int n = list.size();

        int L[][] = new int[m+1][n+1];

        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = 0;
                else if (featureList.get(i-1).equalsIgnoreCase(list.get(j-1)))
                    L[i][j] = L[i-1][j-1] + 1;
                else
                    L[i][j] = L[i-1][j] >= L[i][j-1] ? L[i-1][j] : L[i][j-1];
            }
        }

        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        int start = n;

        for(int i = m; i > 0; i--){
            for(int j = start; j > 0; j--){
                if(L[i][j] == L[i-1][j]){
                    break;
                }else if( L[i][j] == L[i][j-1]){
                    continue;
                }
                else if(L[i][j] == (L[i-1][j-1] + 1)){
                    result.add(list.get(j-1));
                    sb.append(list.get(j-1));
                    start = j-1;
                    break;
                }
            }
        }

        featureList = result;
        features = sb.toString();
//        Hirschberg hirschberg = new Hirschberg();
//        features = hirschberg.algC(features.length(), input.length(), features, input);

        System.out.println(" *** end updating feature string with new log *** ");
    }

    private List<String> preprocess(String origin){
        String result = origin.replaceAll("\\d\\d\\/\\d\\d\\/\\d\\d \\d\\d:\\d\\d:\\d\\d:\\d\\d\\d: ", "");
        result = result.replaceAll("\\[SEQID:\\d+\\] \\[REQID:Integration-Tests\\] \\[ATAG:.+\\]: INFO  ", "");
        result = result.replaceAll("-|\\(|\\)|:", " ");
        result = result.replaceAll(" +", " ");
        //result = result.replaceAll("\\r?\\n", "");
        features = result;
        return replaceClassNames(result);
    }

    private List<String> replaceClassNames(String s){
//        String[] strings = s.split("\\r?\\n");
//        List<String> result = new ArrayList<>();
//        for(String line : strings){
//            result.addAll(Arrays.asList(line.split(" ")));
//        }
        List<String> result = Arrays.asList(s.split(" "));
        return result;
    }

    public String getFeatures(){
        return features;
    }

    public List<String> getList() { return  featureList;}

    @Override
    public String toString() {
        return features;
    }
}
