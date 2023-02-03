package GraphSearch;
import java.io.*;
import java.util.*;

public class Main{
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer> cntList = new ArrayList<Integer>();
    static Deque<int[]> Qu = new ArrayDeque<int[]>();
    static int n, m;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean visited[][];
    static int map[][];
    
    public static void main(String[] args)throws IOException{
       String[] input1 = br.readLine().split(" ");
       n = Integer.parseInt(input1[0]);
       m = Integer.parseInt(input1[1]);
       visited = new boolean[n][m];
       map = new int[n][m];
        
       for(int i = 0; i < n; i++){
           String[] input2 = br.readLine().split("");
           for(int j = 0; j < m; j++){
               if(input2[j].equals("0")){
                   visited[i][j] = true;
               }
               else map[i][j] = 1;
           }
       }
       
       BFS(0,0);
       //BFS로 탐색하면서 숫자 1씩 증가, 목표지점의 숫자 출력 

       System.out.println(map[n-1][m-1]);
    }
    
    
    public static void BFS(int v, int h){
    	visited[v][h] = true;
        Qu.add(new int[]{v, h, 1});
        while(Qu.size() > 0){
            int[] coor = Qu.poll();
            int x = coor[1];
            int y = coor[0];
            int cnt = coor[2];
            map[y][x] = cnt;
            
            for(int i = 0; i < 4; i++){
                int rx = x + dx[i];
                int ry = y + dy[i];
                if(0 <= rx && rx < m && 0 <= ry && ry < n && !visited[ry][rx]){
                    visited[ry][rx] = true;
                    Qu.add(new int[]{ry, rx, cnt + 1});
                }
            }
        }
        
        return;
    }    
}