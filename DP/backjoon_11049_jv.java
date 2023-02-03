package DP;
import java.util.Scanner;
import java.lang.Math;

//행렬이 여러개 주어지며, 모든 행렬을 곱했을 때 최소 연산횟수를 구해라

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][2]; //행렬들의 배열
        int[][] dp = new int[n][n]; //dp[i][j] : i번째 행렬부터 j번째 행렬을 곱한 최소값
        
        for(int i = 0; i < n; i++){
            matrix[i][0] = sc.nextInt(); //행렬의 행 갯수
            matrix[i][1] = sc.nextInt(); //행렬의 열 갯수
        }

        for(int i = 1; i <= n; i++){ //i+1개씩 묶어서
            for(int from = 0; from + i < n; from++){ //어디부터
                int to = from + i;
                dp[from][to] = Integer.MAX_VALUE;
                for(int divide = from; divide < to; divide++){ //구간을 나눠서 계산
                    int currentCost = dp[from][divide] + dp[divide + 1][to] + ( matrix[from][0] * matrix[divide][1] * matrix[to][1] );
                    dp[from][to] = Math.min(dp[from][to], currentCost);
                    //dp[from][to] : 이전에 구한 최소비용과 구간을 나눠서 현재 구한 비용 중 더 작은것
                }
            }
        }
        System.out.println(dp[0][n-1]); //처음부터 끝까지 행렬을 곱했을 때 최소값
    }
}