package GraphSearch;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.io.InputStreamReader;
import java.util.Queue;

class Dot{
	int x;
	int y;
	
	Dot(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main{
    
    public static void main(String[] args)throws IOException{
    	int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
    	Queue<Dot> Qu = new LinkedList<Dot>();
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[1]);
        int m = Integer.parseInt(input1[0]);
        
        int[][] map = new int[n][m];;
        
        boolean isImpossible = false;
        
        int ans = 0;
        
        for(int i = 0; i < n; i++){
        	String[] input2 = br.readLine().split(" ");
        	for(int j = 0; j < m; j++){
               map[i][j] = Integer.parseInt(input2[j]);
            }
        }
        
        //맨처음에 익어있는 토마토를 모두 큐에 넣음
        for(int i = 0; i < n; i++){
        	for(int j = 0; j < m; j++){
        		if(map[i][j] == 1) Qu.add(new Dot(j, i));
            }
        }
       
        //BFS실행
        while(Qu.size() > 0){
            
            //큐 맨앞의 위치 정보
        	Dot dot = Qu.poll();
        	int x = dot.x;
        	int y = dot.y;
           
        	for(int i = 0; i < 4; i++){
        		//동서남북 탐색
                int rx = x + dx[i];
        		int ry = y + dy[i];
        		if(0 <= rx && rx < m && 0 <= ry && ry < n && map[ry][rx] == 0){
        			//해당위치에 안익은 토마토가 있다면 익게한 뒤 현재 토마토의 완숙일 + 1
                    map[ry][rx] = map[y][x] + 1;
        			Qu.add(new Dot(rx, ry));
                }
            }   
        }
        
        //창고를 순회하면서 가장 완숙일이 긴 토마토를 찾아냄, 안익은 토마토가 있으면 ans = -1
        for(int i = 0; i < n; i++){
        	if(isImpossible) break;
           
        	for(int j = 0; j < m; j++){
        	   
                
        		if(map[i][j] == 0){
        			isImpossible = true;
        			ans = -1;
        			break;
                }
        		else if(ans < map[i][j]) ans = map[i][j]-1;  
            }
        }
       
        System.out.println(ans);
    }
}

