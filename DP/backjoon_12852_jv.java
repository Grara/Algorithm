package DP;
import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//DP 활용
//가장 긴 증가하는 부분수열의 길이와 수열의 구성요소 모두 구하기

public class Main{

    static int N;
    static int[] seq, dp, tmp;

    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
    	
        seq = new int[N+1]; //수열
        dp = new int[N+1]; //dp[i] = i번째 숫자까지 만들수 있는 최장 수열 길이 저장
        tmp = new int[N+1]; //최장 수열을 구성하는 i번째 숫자의 바로 이전 숫자 위치를 저장

        int result = 0; //최장 수열 길이
        int resultIdx = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //
        for(int i = 0; i < N; i++){
            seq[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            
            //i번째까지 순회
            for(int j = 0; j < i; j++){

                //i번째 숫자가 j번째 숫자보다 크면서 dp[j]에 1을 더한 값이 dp[i]보다 클 경우
                if(seq[i] > seq[j] && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    tmp[i] = j;
                }
            }

            if(dp[i] > result){
                result = dp[i]; //최장 수열길이 저장
                resultIdx = i;
            }
        }

        int[] ans = new int[result];
        int index = resultIdx;
        
        //tmp를 역으로 순회하면서 순열의 구성요소들을 ans에 넣음
        for(int i = result - 1; i >= 0; i--){
            ans[i] = seq[index];
            index = tmp[index];
        }

    	bw.write(result + "\n");
        
        for(int n : ans){
            bw.write(n + " ");
        }
        bw.close();

    } //End of main


} //End of Main
