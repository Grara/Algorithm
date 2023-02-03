package DP;
import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = toInt(br.readLine());
        int[] seq = new int[n+1];
        String[] seqInput = br.readLine().split(" ");
        int q = toInt(br.readLine());
        boolean[][] dp = new boolean[n+1][n+1]; // i번째부터 j번쨰 수열은 펠린드롬인가 체크
        
        for(int i = 0; i < n; i++) seq[i+1] = toInt(seqInput[i]);
        
        //dp처리 시작
        for(int i = 0; i <= n; i++){
            dp[i][i] = true; //길이가 1인 수열은 모두 펠린드롬
        }

        for(int i = 0; i < n; i++){
            if(seq[i] == seq[i+1]) dp[i][i+1] = true; //길이가 2인 수열은 값이 같으면 펠린드롬
        }
        
        for(int i = 1; i < n; i++){ //몇개씩 묶을 것 인가?
            for(int j = 1; j <= n-i; j++){ //어디부터 묶을 것 인가?
                if(seq[j] == seq[j+i] && dp[j+1][j+i-1]) dp[j][j+i] = true; 
                //묶은 범위의 시작과 끝이 같은 숫자고, dp[시작+1][끝-1]이 true면 펠린드롬
            }
        }
        //dp처리 끝
        
        for(int i = 0; i < q; i++){
            int[] ques = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int s = ques[0];
            int e = ques[1];
            if(dp[s][e]) bw.append("1\n");
            else bw.append("0\n");
        }
        
        bw.close();
    }
    

    
    public static int toInt(String str){
        return Integer.parseInt(str);
    }
}