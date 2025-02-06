import java.io.*;
import java.util.*;

public class Main {

	static int n, allVisitBit, INF = 987654321;
	static int[][] weight;
	static int[][] dp;


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		allVisitBit = (1<<n) -1;
		weight = new int[n][n];
		dp = new int[n][allVisitBit];
		
		for(int i=0; i<n; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				weight[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(tsp(0,1)); // 0번도시 부터 탐색 시작 (check: 0001)
	}
	
	static int tsp(int x, int check) {

		// 모든 도시 방문 완료
		if(check == allVisitBit) {
			if(weight[x][0] == 0) return INF; // 경로 없으면 INF로 탐색 무효화 (Math.min)
			/*
			경로가 존재하면 w[x][0]
			왜? => 원순열을 구하는거기때문에 모든 도시에서 출발하는 경우의 수를 구할 필요가 없음 => 즉, 마지막은 결국 첫번째 도시로 돌아오게 돼있음
			*/
			else return weight[x][0]; // 
		}
        
		// 이미 방문한 도시 
		if(dp[x][check] != -1) return dp[x][check];
        
		// 해당 도시에 출석 표시
		dp[x][check] = INF;
        
		// 방문하지 않은 도시 탐색 
		for(int i=0; i<n; i++) {
			// next : i 도시 방문
			int next = check | (1<<i); 
            
			// 경로가 없거나 i 도시를 이미 방문했을 경우 continue 
			if(weight[x][i] ==0 || (check & (1<<i)) != 0) continue;

			dp[x][check] = Math.min(dp[x][check], tsp(i, next) + weight[x][i]);
		}
		
		return dp[x][check];
	}
}
