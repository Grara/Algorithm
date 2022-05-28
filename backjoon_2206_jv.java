import java.io.*;
import java.util.*;

//현재 칸의 위치, 이동한 횟수, 벽 파괴여부를 담을 클래스
class Square{
    int y;
    int x;
    int cnt;
    boolean Destroyed;
    Square(int y, int x, int cnt, boolean Destroyed){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
        this.Destroyed = Destroyed;
    }
}

public class Main{
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n;
    static int m;
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Square> qu = new LinkedList<Square>();
        String[] input1 = br.readLine().split(" ");
        n = Integer.parseInt(input1[0]);
        m = Integer.parseInt(input1[1]);
        boolean[][][] visited = new boolean[2][n][m];
        //벽을 부수고 이동한 경우와 안부수고 이동한 경우를 모두 계산하기 위해 visited를 3차원 배열로
        int ans = 2147483647;
        boolean[][] map = new boolean[n][m];
        
        for(int i = 0; i < n; i++){
            String[] input = br.readLine().split("");
            for(int j = 0; j < m; j++){
                if(input[j].equals("1")){
                    map[i][j] = true;
                }
            }
        }
        
        qu.add(new Square(0,0,1,false));
        visited[0][0][0] = true;
        
        while(!qu.isEmpty()){
            Square sq = qu.poll();
            int y = sq.y;
            int x = sq.x;
            int cnt = sq.cnt;
            boolean d = sq.Destroyed;
            
            if(y == n-1 && x == m-1 && cnt < ans) ans = cnt; 
            //목적지 도착하면 종료

            for(int i = 0; i < 4; i++){
                int ry = y + dy[i];
                int rx = x + dx[i];
                if(0 <= ry && ry < n && 0 <= rx && rx < m){
                    //벽이 아닌경우
                    if(!map[ry][rx]) {
                    	//벽을 파괴한적이 없으면
                        if(d == false && !visited[0][ry][rx]) {
                    		visited[0][ry][rx] = true; //벽을 안부수고 이동한 경우의 수로 이어짐
                    		qu.add(new Square(ry,rx, cnt+1, d));
                    	}
                    	
                        //벽을 파괴한적이 있으면
                    	else if(d == true && !visited[1][ry][rx]){
                    		visited[1][ry][rx] = true;//벽을 부수고 이동한 경우의 수로 이어짐
                    		qu.add(new Square(ry,rx, cnt+1, d));
                    	}
                    }
                    
                    //벽일 경우, 벽을 파괴한적이 없으면
                    else if(d == false){
                        qu.add(new Square(ry,rx, cnt+1, true));
                        visited[1][ry][rx] = true;//벽을 부수고 이동한 경우의 수로 이어짐
                    }
                }
            }
        }
        if(ans == 2147483647) ans = -1; //목적지에 도착못하면 -1
        
        System.out.println(ans);
    }
    
}