package com.ea.newworld.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hirschberg {
    public Hirschberg() {

    }

    /**
     * Algorithm B as described by Hirschberg.	 * returns the last line of the Needleman-Wunsch score matrix Score(i,j):
     *
     * @param m
     * @param n
     * @param a
     * @param b
     * @return
     */
    public int[] algB(int m, int n, List<String> a, List<String> b) {

        // Step 1
        int[][] k = new int[2][n+1];
        for( int j=0; j<=n; j++) {
            k[1][j] = 0;
        }

        // Step 2
        for(int i=1; i<=m; i++) {
            // Step 3
            for(int j=0; j<=n; j++) {
                k[0][j] = k[1][j];
            }

            // Step 4
            for(int j=1; j<=n; j++) {
                if(a.get(i-1).equalsIgnoreCase(b.get(j-1))) {
                    k[1][j] = k[0][j-1] + 1;
                }else{
                    k[1][j] = max(k[1][j-1], k[0][j]);
                }
            }
        }

        //Step 5
        return k[1];

    }

    /**
     * This method returns the maximum number between two numbers.
     *
     * @param x
     * @param y
     * @return
     */
    public int max(int x, int y) {
        if(x>y) {
            return x;
        }else{
            return y;
        }
    }

    /**
     * Algorithm C as described by Hirschberg
     *
     * @param m
     * @param n
     * @param a
     * @param b
     * @return
     */
    public List<String> algC(int m, int n, List<String> a, List<String> b) {
        int i=0;
        int j=0;
        List<String> c = new ArrayList<String>();

        // Step 1
        if( n==0 ) {
            c = new ArrayList<String>();
        } else if( m==1 ) {
            c = new ArrayList<String>();
            for( j=0; j<n; j++ ) {
                if( a.get(0).equalsIgnoreCase(b.get(j)) ) {
                    c.add(a.get(0));
                    break;
                }
            }

            // Step 2
        } else {
            i= (int) Math.floor(((double)m)/2);

            // Step 3
            int[] l1 = algB(i, n, a.subList(0,i), b);
            int[] l2 = algB(m-i, n, reverseList(new ArrayList<String>( a.subList(i, m)) ), reverseList( new ArrayList<String>(b)) );

            // Step 4
            int k = findK(l1, l2, n);

            // Step 5
            List<String> c1 = algC(i, k, a.subList(0, i), b.subList(0, k));
            List<String> c2 = algC(m-i, n-k, a.subList(i, m), b.subList(k, n));

            c1.addAll(c2);
            c = c1;
        }

        return c; // The LCS
    }


    /**
     * This method takes a string as input reverses it and
     * returns the result
     *
     * @param in
     * @return
     */
    public String reverseString(String in) {
        String out = "";

        for(int i=in.length()-1; i>=0; i--) {
            out = out+in.charAt(i);
        }

        return out;
    }

    public List<String> reverseList(List<String> in){
        Collections.reverse(in);
        return in;
    }

    /**
     * This method finds the index of the maximum sum of L1 and L2,
     * as described by Hirschberg
     *
     * @param l1
     * @param l2
     * @param n
     * @return
     */
    public int findK(int[] l1, int[] l2, int n) {
        int m = 0;
        int k = 0;

        for(int j=0; j<=n; j++) {
            if(m < (l1[j]+l2[n-j])) {
                m = l1[j]+l2[n-j];
                k = j;
            }
        }

        return k;
    }

//
//    /**
//     * The main method for the algorithm
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        if(args.length != 2) {
//            System.err.println("Usage: Enter two strings X and Y");
//        }else{
//            String x = args[0];
//            String y = args[1];
//            Hirschberg alg = new Hirschberg();
//            System.out.println("LCS: " + alg.algC(x.length(), y.length(), x, y)); //computes & prints out the result
//        }
//    }
}
