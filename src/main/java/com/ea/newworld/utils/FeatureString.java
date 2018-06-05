package com.ea.newworld.utils;

import java.util.List;

public class FeatureString {
    private String features;

    public FeatureString(String string){
        features = string;
    }

    public void updateWithFile(String input){
        int m = features.length();
        int n = input.length();

        String L[][] = new String[m+1][n+1];

        for (int i=0; i<=m; i++)
        {
            for (int j=0; j<=n; j++)
            {
                if (i == 0 || j == 0)
                    L[i][j] = "";
                else if (features.charAt(i-1) == input.charAt(j-1))
                    L[i][j] = L[i-1][j-1] + features.charAt(i-1);
                else
                    L[i][j] = L[i-1][j].length() >= L[i][j-1].length() ? L[i-1][j] : L[i][j-1];
            }
        }
        features = L[m][n];
    }

    @Override
    public String toString() {
        return features;
    }
}
