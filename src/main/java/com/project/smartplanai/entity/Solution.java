package com.project.smartplanai.entity;

public class Solution {

    public static int solution(int[][] lines) {

        int min=0 ,max = 0;
        for (int[] line : lines) {
            for (int j = 0; j < line.length; j++) {
                if (line[j] > max) {
                    max = line[j];
                } else if (line[j] < min) {
                    min = line[j];
                }
            }
        }
        int[] cntV = new int [max - min + 1];

        for(int[] line : lines){
            int start = line[0];
            int end = line[1];

            for(int i = start;i<end;i++){
                cntV[i-min] += 1;
            }
        }
        int cnt=0;
        for(int a : cntV){
             if(a>1){
                 cnt++;
             }
        }

        return cnt;
    }

    public static void main(String[] args) {

    }
}
