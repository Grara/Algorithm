package GraphSearch;
import java.io.*;
import java.util.*;

public class Main{
    static boolean notVisited[][];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int t, m, n, k;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int cnt = 0;
    
    public static void main(String[] args)throws IOException{
        t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            String[] input = br.readLine().split(" ");
            m = Integer.parseInt(input[0]);
            n = Integer.parseInt(input[1]);
            k = Integer.parseInt(input[2]);
            notVisited = new boolean[n][m];
            cnt = 0;
            
            for(int j = 0; j < k; j++){
                String[] coor = br.readLine().split(" ");
                int x = Integer.parseInt(coor[0]);
                int y = Integer.parseInt(coor[1]);
                notVisited[y][x] = true;
            }
            
            for(int a = 0; a < n; a++){
                for(int b = 0; b < m; b++){
                    if(notVisited[a][b]){
                        cnt += 1;
                        DFS(a, b);
                    }
                }
            }
            
            System.out.println(cnt);
            
        }
    }
    
    public static void DFS(int v, int h){
        
    	notVisited[v][h] = false;
        
    	for(int i = 0; i < 4; i++ ){
            int rx = h + dx[i];
            int ry = v + dy[i];
            
    		if(0 <= ry && ry < n && 0 <= rx && rx < m) {
    			if(notVisited[ry][rx]) {
    			DFS(ry, rx);
    			}
    		}
    	}
        return;
    }    
}