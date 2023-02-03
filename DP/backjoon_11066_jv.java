package DP;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st = null;

    public static void main(String[] args) throws Exception {
        int t;

        t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int k; //소설의 갯수
            int[] novel; //각 소설의 비용
            int[] sum; //소설 비용의 누적 합
            int[][] dp; //i번째 소설부터 j번째 소설 까지를 합치는 최소비용

            k = Integer.parseInt(br.readLine());
            novel = new int[k + 1];
            dp = new int[k + 1][k + 1];
            sum = new int[k + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= k; i++) {
                novel[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + novel[i];
            }

            for (int n = 1; n <= k; n++) { //몇장을 묶을 것인가?
                for (int from = 1; from + n <= k; from++) { //어디부터 묶을 것인가?
                    int to = from + n;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int divide = from; divide < to; divide++) { //범위안에서 여러 지점으로 나눠서 최소값 구하기
                        dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }

            System.out.println(dp[1][k]);
        }

    }

}