package com.ea.newworld.issue;

import com.ea.newworld.utils.Hirschberg;

public class FeatureString {
    private String features;

    public FeatureString(String string){
        features = preprocess(string);
    }

    public void updateWithFile(String input){
        System.out.println(" *** start to update feature string with new log *** ");

        input = preprocess(input);
//        int m = features.length();
//        int n = input.length();
//
//        int L[][] = new int[m+1][n+1];
//
//        for (int i=0; i<=m; i++)
//        {
//            for (int j=0; j<=n; j++)
//            {
//                if (i == 0 || j == 0)
//                    L[i][j] = 0;
//                else if (features.charAt(i-1) == input.charAt(j-1))
//                    L[i][j] = L[i-1][j-1] + 1;
//                else
//                    L[i][j] = L[i-1][j] >= L[i][j-1] ? L[i-1][j] : L[i][j-1];
//            }
//        }
//
//        String result = "";
//        int start = n;
//
//        for(int i = m; i > 0; i--){
//            for(int j = start; j > 0; j--){
//                if(L[i][j] == L[i-1][j]){
//                    break;
//                }else if( L[i][j] == L[i][j-1]){
//                    continue;
//                }
//                else if(L[i][j] == (L[i-1][j-1] + 1)){
//                    result = input.charAt(j-1) + result;
//                    start = j-1;
//                    break;
//                }
//            }
//        }
//
//        features = result;
        Hirschberg hirschberg = new Hirschberg();
        features = hirschberg.algC(features.length(), input.length(), features, input);

        System.out.println(" *** end updating feature string with new log *** ");
    }

    private String preprocess(String origin){
        String result = origin.replaceAll("\\d\\d\\/\\d\\d\\/\\d\\d \\d\\d:\\d\\d:\\d\\d:\\d\\d\\d: ", "");
        result = result.replaceAll("\\[SEQID:\\d+\\] \\[REQID:Integration-Tests\\] \\[ATAG:.+\\]: INFO ", "");
        return result;
    }

    public String getFeatures(){
        return features;
    }

    @Override
    public String toString() {
        return features;
    }
}
