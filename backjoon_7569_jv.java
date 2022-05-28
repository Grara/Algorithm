import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.io.InputStreamReader;
import java.util.Queue;

//각 토마토의 좌표를 담을 클래스
class Dot{
	int x;
	int y;
	int z;
	Dot(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

public class Main{
    
    public static void main(String[] args)throws IOException{
    	//주변 토마토 탐색 시 동, 서, 남, 북, 위, 아래 순으로 진행
    	int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, -1, 1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};
        
        //탐색할 토마토를 담을 큐
    	Queue<Dot> Qu = new LinkedList<Dot>();
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
        String[] input1 = br.readLine().split(" ");
        int n = Integer.parseInt(input1[1]);
        int m = Integer.parseInt(input1[0]);
        int h = Integer.parseInt(input1[2]);
        
        int[][][] map = new int[h][n][m];;
        
        //모든 토마토의 완숙 가능여부
        boolean isImpossible = false;
        
        int ans = 0;
        
        //창고에 토마토 배치
        for(int i = 0; i < h; i++){
        	
        	for(int j = 0; j < n; j++){
        		
        		String[] input2 = br.readLine().split(" ");
        		
        		for(int k = 0; k < m; k++) {
        			map[i][j][k] = Integer.parseInt(input2[k]);
        			if(map[i][j][k] == 1) Qu.add(new Dot(k,j,i)); //처음 배치 시 익은 토마토면 큐에 넣는다.
        		}
            }
        }
       
        //BFS실행
        while(Qu.size() > 0){
           
        	Dot dot = Qu.poll();
        	int x = dot.x;
        	int y = dot.y;
        	int z = dot.z;
            
        	//큐에서 꺼낸 토마토위치에서 동, 서, 남, 북, 위, 아래 순으로 탐색
        	for(int i = 0; i < 6; i++){
        		int rx = x + dx[i];
        		int ry = y + dy[i];
        		int rz = z + dz[i];
        		
        		//창고의 범위를 벗어나면 건너띔
        		if(rx < 0 || m <= rx || ry < 0 || n <= ry || rz < 0 || h <= rz){
        			continue;
                }
        		
        		//탐색한 위치에 안익은 토마토가 있으면 현재 토마토의 위치의 날짜에 1을 더해서 해당 위치에 넣음
        		if(map[rz][ry][rx] == 0) {
        		map[rz][ry][rx] = map[z][y][x] + 1;
    			Qu.add(new Dot(rx, ry, rz)); //탐색한 안익은 토마토를 큐에 넣음
        		}
            }   
        }
        
        //창고를 순회하면서 제일 큰값을 찾아냄, 안익은 토마토가 발견되면 ans = -1 이후 종료
        for(int i = 0; i < h; i++){
        	if(isImpossible) break;
           
        	for(int j = 0; j < n; j++){
        		if(isImpossible) break;
        		
        		for(int k = 0; k < m; k++) {
	        		if(map[i][j][k] == 0){
	        			isImpossible = true;
	        			ans = -1;
	        			break;
	                }
	        		else if(ans < map[i][j][k]) ans = (map[i][j][k]-1);  
        		}
            }
        }
       
        System.out.println(ans);
    }
}
