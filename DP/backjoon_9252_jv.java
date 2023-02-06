import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제이름 : LCS2
//DP문제
//두 문자열의 공통 부분 최장 수열

public class Main{

    static int[][] dp;
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args)throws Exception{

        String str1 = br.readLine();
        String str2 = br.readLine();

        LCS(str1, str2);
        getAnswerString(str1, str1.length(), str2.length());

        bw.close();
        

    } //End of main

    static void LCS(String str1, String str2) throws Exception{
        int n1 = str1.length();
        int n2 = str2.length();

        dp = new int[n1+1][n2+1];
        int max = -1;
        for(int i = 1; i < n1+1; i++){
            for(int j = 1; j < n2+1; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        bw.write(dp[n1][n2] + "\n");
    } //End of LCS

    static void getAnswerString(String str, int i, int j) throws Exception{
        Stack<Character> st = new Stack<>();
        while(i > 0 && j > 0){
            if(i == 0 || j == 0) break;
            
            if(dp[i][j] == dp[i-1][j]) i--;
            else if(dp[i][j] == dp[i][j-1]) j--;
            else {
                st.push(str.charAt(i-1));
                i--;
                j--;
            }
        }
        
        while(!st.isEmpty()){
            bw.write(st.pop());
        }
    } //End of getAnswerString

} //End of Main
