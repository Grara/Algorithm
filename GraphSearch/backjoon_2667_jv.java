package GraphSearch;
import java.io.*;
import java.util.*;

public class Main{
    static boolean visited[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer> cntList = new ArrayList<Integer>();
    static Deque<Integer> Qu;
    static int n;
    //동서남북 4방향 탐색을 위한 배열
    static int[] x = {1, -1, 0, 0};
    static int[] y = {0, 0, -1, 1};
    
    public static void main(String[] args)throws IOException{
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];
        
        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split("");
            for(int j = 0; j < n; j++) {
            	if(input[j].equals("0")) {
            		visited[i][j] = true;
            	}
            	else visited[i][j] = false;
            }
        }
        
        for(int i = 0; i < n; i++) {
        	for(int j = 0; j < n; j++) {
        		if(!visited[i][j]) {
        			cntList.add(DFS(i,j,0));
        		}
        	}
        }
        
        Collections.sort(cntList);
        
        System.out.println(cntList.size());
        for(int i : cntList) {
        	System.out.println(i);
        }
        
    }
    
    public static int DFS(int h, int v, int prevCnt){
    	int cnt = prevCnt;
    	cnt += 1;
    	visited[h][v] = true;
        
        //동서남북 탐색
    	for(int i = 0; i < 4; i++ ){
            int rx = h+x[i];
            int ry = v+y[i];
    		if(0 <= rx && rx < n && 0 <= ry && ry < n) {
                if(!visited[rx][ry]) {
    			cnt = DFS(rx, ry, cnt);
    			}
    		}
    	}
    	
        return cnt;
    }    
}