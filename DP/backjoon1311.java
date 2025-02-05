//N명의 사람과 N개의 일이 주어짐, 한명당 한개의 일만 할 수 있음, 사람마다 각 일을 수행할 때 비용이 다름
//모든인원이 일을 했을 때 최소비용을 구해라

import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);
    static int N;
    static int[][] costArr;
    static int[][] dp;
    static int INF = Integer.MAX_VALUE;

    public static void main(String[] args)throws IOException{
        N = sc.nextInt();
        costArr = new int[N][N];
        dp = new int[N][1<<N];

        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i], INF); //dp배열 초기화

            for(int j = 0; j < N; j++){
                costArr[i][j] = sc.nextInt(); //비용배열 값 넣기
            }
        }

        //첫번째 사람이 각각의 일을 했을 때 dp값 설정
        for(int i = 0; i < N; i++){
            dp[0][1<<i] = costArr[0][i];
        }

        for(int i = 1; i < N; i++){ //두번째 사람부터 순회 시작
            
            for(int j = 0; j < 1<<N; j++){ //dp애서 이전에 진행한 일의 조합을 확인
                /*
                dp에 해당 조합에 대한 기록이 없으면 스킵
                왜? => 기존에 일을 했던 조합에 현재 인원이 기존에 안했던 일을 했을 때 비용을 구하기 위함임
                */
                if(dp[i-1][j] == INF) continue; 
                
                for(int k = 0; k < N; k++){ //현재 조합에서 현재 사림이 어떤일을 작업할지 순회
                    if((j & 1<<k) != 0) continue; //이전에 다른사람이 했던 일이면 스킵
                    dp[i][j | 1<<k] = Math.min(dp[i][j | 1<<k], dp[i-1][j] + costArr[i][k]);
                }
            }
        }
        
        System.out.println(dp[N-1][(1<<N)-1]);
    }
}
