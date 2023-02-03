package GraphSearch;
import java.io.*;
import java.util.*;

class MoveInfo implements Comparable<MoveInfo>{
	int point; //도착 지점
	int second; //도착 지점에 도달한 시간
	
	MoveInfo(int point, int second){
		this.point = point;
		this.second = second;
	}
	
	@Override
	public int compareTo(MoveInfo m) {
		return this.second - m.second;
	}
}

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;
    static int LIMIT = 500000; //거리 제한

    public static void main(String[] args)throws IOException{
       
    	String[] input_1st = br.readLine().split(" ");
        N = Integer.parseInt(input_1st[0]);
        K = Integer.parseInt(input_1st[1]);
        
        int ans = dijkstra(N, K);
        
        System.out.println(ans);
    }
    
    public static int dijkstra(int start, int end){
    	
    	//지점 방문 여부
        boolean[] visited = new boolean[LIMIT];
    	
        //minSec[i] = 시작 위치부터 i번 지점에 도착한 최소시간 
        int[] minSec = new int[LIMIT];
        Arrays.fill(minSec, LIMIT); //초기화, 무조건 500000초보다는 덜 걸림
        minSec[start] = 0;
        
        PriorityQueue<MoveInfo> queue = new PriorityQueue<>();
        queue.add(new MoveInfo(start, 0));
        
        while(!queue.isEmpty()){
            
        	//현재 위치 : 큐에서 꺼낸 이동정보의 도착지점 
        	MoveInfo curMove = queue.poll();
        	int curPoint = curMove.point;
            
        	//목표지점에 도달하면 종료
        	if(curPoint == K) {
        		return curMove.second;
        	}
        	
        	//현재 위치가 150000보다 크면 더 탐색 안함
        	if(curPoint > 150000) {
        		continue;
        	}
        	
            if(!visited[curPoint]) {
            	
            	visited[curPoint] = true; //방문 체크
            	
            	//[현재 위치 * 2] 지점에 갈 것인지 결정 
            	if(!visited[curPoint*2] && minSec[curPoint] < minSec[curPoint*2]) {
            		minSec[curPoint*2] = minSec[curPoint];
            		queue.add(new MoveInfo(curPoint*2, minSec[curPoint*2]));
            	}
            	
            	//[현재 위치 + 1] 지점에 갈 것인지 결정
            	if(!visited[curPoint+1] && minSec[curPoint] + 1 < minSec[curPoint+1]) {
            		minSec[curPoint+1] = minSec[curPoint] + 1;
            		queue.add(new MoveInfo(curPoint+1, minSec[curPoint+1]));
            	}
            	
            	//[현재 위치 - 1] 지점에 갈 것인지 결정
            	if(0 < curPoint && !visited[curPoint-1] && minSec[curPoint] + 1 < minSec[curPoint-1]) {
            		minSec[curPoint-1] = minSec[curPoint] + 1;
            		queue.add(new MoveInfo(curPoint-1, minSec[curPoint-1]));
            	}
            }
        }
        
        return minSec[end]; //의미없는 return문
        
    }    
}