package ShortestWay;
import java.io.*;
import java.util.*;

class Road implements Comparable<Road>{
	public int end; //도착 지점
	public int weight; //가중치
	
	Road(int end, int weight){
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Road r) {
		return this.weight - r.weight;
	}
}

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m, t;
    static int s, g, h;
    static int LIMIT = 1000_000_000; //거리 제한
    static List<List<Road>> graph;
	static int[] minDist;
    
    public static void main(String[] args)throws IOException{
        
    	int T = Integer.parseInt(br.readLine());

    	for(int i = 0; i < T; i++) {
    		
    		String[] input_1st = br.readLine().split(" ");
    		n = Integer.parseInt(input_1st[0]);
    		m = Integer.parseInt(input_1st[1]);
    		t = Integer.parseInt(input_1st[2]);
    		
    		String[] input_2nd = br.readLine().split(" ");
    		s = Integer.parseInt(input_2nd[0]);
    		g = Integer.parseInt(input_2nd[1]);
    		h = Integer.parseInt(input_2nd[2]);
    		
			minDist = new int[n+1];
			Arrays.fill(minDist, LIMIT);

    		graph = new ArrayList<>();
    		for(int v = 0; v <= n; v++) {
    			graph.add(new ArrayList<>());
    		}
    		

    		for(int j = 0; j < m; j++) {
    			String[] inputs = br.readLine().split(" ");
        		int a = Integer.parseInt(inputs[0]);
        		int b = Integer.parseInt(inputs[1]);
        		int d = Integer.parseInt(inputs[2]);
        		
        		//a와 b가 각각 g, h일 경우 가중치를 홀수로 만들어줌
        		if((a == g && b == h) || (a == h && b == g)) {
        			graph.get(a).add(new Road(b, d*2 - 1));
        			graph.get(b).add(new Road(a, d*2 - 1));
        		}
        		
        		//나머지는 2를 곱해서 짝수로 만들어 줌
        		else {
        			graph.get(a).add(new Road(b, d*2));
        			graph.get(b).add(new Road(a, d*2));
        		}
    		}
    		
    		List<Integer> targetList = new ArrayList<>();
    		for(int k = 0; k < t; k++) {
    			targetList.add(Integer.parseInt(br.readLine()));
    		}
    		
    		Collections.sort(targetList); //오름차순 정렬
    		
			dijkstra();

    		for(int target : targetList) {
                //결과가 홀수일 경우 g->h경로를 거쳤다는 뜻이 됨, 따라서 정답에 추가
    			if(minDist[target] % 2 == 1) bw.append(target + " ");
    		}
    		
    		bw.append("\n");
    	}
        
        bw.close();
    }
    
    public static void dijkstra(){
    	
    	//교차로 방문 여부
        boolean[] visited = new boolean[n+1];
        
        minDist[s] = 0;
        
        PriorityQueue<Road> queue = new PriorityQueue<>();
        queue.add(new Road(s, 0));
        
        while(!queue.isEmpty()){
            
        	//현재 위치 : 큐에서 꺼낸 도로의 도착지점
        	Road curRoad = queue.poll();
        	int curPoint = curRoad.end;
        	
            if(!visited[curPoint]) {
            	
            	visited[curPoint] = true; //방문 체크
            	
            	for(Road road : graph.get(curPoint)) {
            		if(!visited[road.end] && minDist[road.end] > minDist[curPoint] + road.weight) {
            			minDist[road.end] = minDist[curPoint] + road.weight;
            			queue.add(new Road(road.end, minDist[road.end]));
            		}
            	}
            }
        }
    }    
}