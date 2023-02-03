import java.io.*;
import java.util.*;

public class Main{
    
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static int map[][];
    static int dp[][];
    static int n;
    static int m;
    
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        m = Integer.parseInt(inputs[0]);
		n = Integer.parseInt(inputs[1]);
        map = new int[m][n];
        dp = new int[m][n];
        
        for(int i = 0; i < m; i++){
            inputs = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(inputs[j]);
                dp[i][j] = -1; //방문여부 표시를 위해 -1로 초기화
            }
        }

        System.out.println(dfs(0,0));
    }
    
    public static int dfs(int x, int y){
        
        if(x == n-1 && y == m-1){
            return 1; //목적지에서는 1을 반환
        }
        
        if(dp[y][x] != -1){
            return dp[y][x]; //방문한적이 있으면 바로 반환
        }
        
        dp[y][x] = 0; //방문한적이 없으면 방문 체크
        
        for(int i = 0; i < 4; i++){
            int tx = x + dx[i];
            int ty = y + dy[i];
            //현재 위치 기준으로 상하좌우 순서로 방문

           
            if(0 > tx || tx >= n || 0 > ty || ty >= m){
                continue; //맵 범위를 벗어나면 continue
            }

            if(map[ty][tx] < map[y][x]){
                dp[y][x] += dfs(tx, ty); //현재 위치보다 방문하려는 곳이 더 높이가 낮으면 해당dp값을 현재 dp값에 더한다.
            }
        }

        return dp[y][x];
    }
}