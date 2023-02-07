import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제이름 : 경찰차
//DP문제
//두 경찰차가 모든 사건현장에 출동할 때의 최단 경로
//마지막 사건부터 거꾸로 거슬러서 계산해야함
//재귀를 이용

public class Main{

    static int[][] list = new int[1002][2]; //사건의 위치 저장
    static int N, W;

    //dp[x][y] = 첫 경찰차의 위치가 x번째 사건이고 두번째 경찰차의 위치가 y번째 사건일 경우
    //현재 위치에서 사건을 끝까지 해결할 때까지 이동하는 최단 거리
    static int [][] dp = new int[1002][1002];

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args)throws Exception{
        
        N = Integer.parseInt(br.readLine());
        W = Integer.parseInt(br.readLine());
        
        //사건 좌표 초기화
        for(int i = 1; i <= W; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            list[i][0] = Integer.parseInt(st.nextToken());
            list[i][1] = Integer.parseInt(st.nextToken());
        }

        bw.write(police(1, 0, 0) + "\n");

        int idx_one = 0, 
            idx_two = 0;
        
        
        for(int i = 1; i <= W; i++){
        	
            int one_remain = distance(1, idx_one, i);
            	
            //둘다 시작위치이면서 마지막 사건까지 해결하는 최단거리 - 첫번째 차가 시작위치에서 첫번째 사건으로 이동하는 거리 
            //= 첫 경찰차가 첫번째 사건 위치이면서 두번째가 시작위치일 경우 해결하는 최단거리
            //이 경우 첫번째 사건은 첫번째 경찰차가 해결한 것이다.
            if(dp[idx_one][idx_two] - one_remain == dp[i][idx_two]){
                idx_one = i;
                bw.write("1\n");
            }else{
                idx_two = i;
                bw.write("2\n");
            }
        }

        bw.close();

    } //End of main

    
    static int police(int idx, int one, int two){
        
        if(idx > W) return 0;

        if(dp[one][two] != 0) return dp[one][two];
        
        //첫번째 경찰차가 현재 사건으로 이동하는 최단 거리 = 
        //다음 사건이 일어났을 시 두 경찰차 중 한개의 차가 출동했을 시 최단거리 + 첫번째 경찰차가 현재 사건으로 이동하는 거리
        int one_move = police(idx+1, idx, two) + distance(1 , one, idx);

        int two_move = police(idx+1, one, idx) + distance(2, two, idx);
        
        //두 경찰차가 출동했을 시 더 최소인쪽을 고름
        dp[one][two] = Math.min(one_move, two_move);

        return dp[one][two];

    }
    
    //시작점부터 목표지점까지 거리 구하기
    static int distance(int sep, int start, int end){
        
        int x_start = list[start][0],
            y_start = list[start][1],
            x_end = list[end][0],
            y_end = list[end][1];
        
        if(start == 0){
            if(sep==1) x_start = y_start = 1;
            else x_start = y_start = N;
        }

        return Math.abs(x_start-x_end) + Math.abs(y_start - y_end);

    }
    
} //End of Main
