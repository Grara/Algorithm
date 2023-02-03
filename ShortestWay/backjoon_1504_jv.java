import java.io.*;
import java.util.*;

//간선 정보
class Edge implements Comparable<Edge>{
    int target; //도착 정점
    int dis; //거리

    Edge(int target,int dis){
        this.target = target;
        this.dis = dis;
    }
    
    //우선순위큐에서 사용하기 위함
    //간선 거리 오름차순
    @Override
    public int compareTo(Edge e) {
    	return this.dis - e.dis;
    }
}

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, E;
    static List<List<Edge>> graph;
    static int v1, v2;
    static int LIMIT = 2000000000; //거리의 최대치

    public static void main(String[] args)throws IOException{
       
    	String[] input_1st = br.readLine().split(" ");
        N = Integer.parseInt(input_1st[0]);
        E = Integer.parseInt(input_1st[1]);
        
        //각 정점간의 연결 정보를 담은 List
        graph = new ArrayList<>();
        
        //graph 초기화
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        
        //간선은 양방향이기때문에 a->b, b->a 로 가는 간선정보를 넣어줌
        for(int i = 0; i < E; i++){
            String[] input_edge = br.readLine().split(" ");
            int a = Integer.parseInt(input_edge[0]);
            int b = Integer.parseInt(input_edge[1]);    
            int c = Integer.parseInt(input_edge[2]);
            
            graph.get(a).add(new Edge(b, c));
            graph.get(b).add(new Edge(a, c));
        }
        
        String[] input_last = br.readLine().split(" ");
        v1 = Integer.parseInt(input_last[0]);
        v2 = Integer.parseInt(input_last[1]);
        
        //양방향이기때문에 v1->v2 한번만 알아내면 됨
        int v1_To_v2 =  dijkstra(v1, v2);
        
        //1 -> v1 -> v2 -> 마지막까지 가는 최소거리
        Long result1 = 0L;
        result1 += dijkstra(1, v1);
        result1 += v1_To_v2;
        result1 += dijkstra(v2, N);
        
        //1 -> v2 -> v1 -> 마지막까지 가는 최소거리
        Long result2 = 0L;
        result2 += dijkstra(1, v2);
        result2 += v1_To_v2;
        result2 += dijkstra(v1, N);
        
        //갈 수 없다면 -1, 아니라면 둘 중 더 최소거리로
        if(LIMIT <= result1 && LIMIT <= result2) System.out.println(-1);
        else System.out.println(Math.min(result1, result2));
        
    }
    
    public static int dijkstra(int start, int end){
    	
    	//정점 방문여부 체크
        boolean[] visited = new boolean[N+1];
    	
        //minDis[i] = 시작 정점부터 i번 정점에 도착하는 최소거리 
        int[] minDis = new int[N+1];
        Arrays.fill(minDis, LIMIT); //초기화
        minDis[start] = 0; //출발점->출발점으로 가는거리 : 0
        
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(start, 0));
        
        while(!queue.isEmpty()){
            
        	//현재 정점 : 큐에서 꺼낸 간선의 도착지점 
        	int curNode = queue.poll().target;
            
            if(!visited[curNode]) {
            	
            	visited[curNode] = true;
            	
            	//현재 방문한 정점에서 다른 정점으로 연결된 간선들 확인
	            for(Edge edge : graph.get(curNode)){
	            	
	            	//간선의 목표 정점에 방문한적이 없고, 
	            	//[출발점 -> 현재 정점에 도착한 최소거리] + [간선의 길이]가 [출발점 -> 목표 정점에 도착한 최소거리]보다 짧다면
	                if(!visited[edge.target] && minDis[edge.target] > minDis[curNode] + edge.dis){
	                	minDis[edge.target] = minDis[curNode] + edge.dis; //출발점 -> 목표 정점에 도착한 최소거리 갱신
	                    queue.add(new Edge(edge.target, minDis[edge.target]));
	                }
	            }
            }
        }
        
        return minDis[end];
        
    }    
}