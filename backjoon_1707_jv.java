import java.util.*;
import java.io.*;

public class Main{
    static ArrayList<Integer> graph[];
    static int visited[];
    static boolean visited2[];
    static boolean isOver = false;
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int t = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < t; i++){
            String[] input1 = br.readLine().split(" ");
            int v = Integer.parseInt(input1[0]) + 1;
            int e = Integer.parseInt(input1[1]);
            graph = new ArrayList[v];
            visited = new int[v]; //각 노드에 1과 2를 넣기위한 배열
            visited2 = new boolean[v]; //각 노드의 방문여부(큐에 넣고 '꺼낸적'이 있는가)
            isOver = false; //각 케이스의 이분그래프 여부 체크
            
            for(int j = 0; j < v; j++){
                graph[j] = new ArrayList<Integer>();
            }
            
            for(int j = 0; j < e; j++){
                String[] input2 = br.readLine().split(" ");
                int a = Integer.parseInt(input2[0]);
                int b = Integer.parseInt(input2[1]);
                graph[a].add(b);
                
                graph[b].add(a);
            }
            //모든 노드를 BFS로 탐색
            for(int j = 0; j < v; j++){
                if(visited[j] == 0) BFS(j);
            }
            //BFS로 탐색 후 isOver가 false면 이분그래프
            if(isOver) bw.append("NO\n");
            else bw.append("YES\n");
            
        }
        
        bw.close();
    }
    
    public static void BFS(int n){
    	LinkedList<Integer> qu = new LinkedList<Integer>();
    	visited[n] = 1; //해당 노드에 1을 대입
        visited2[n] = true; //방문처리
        qu.add(n);
        
        while(!qu.isEmpty()){
            if(isOver) break;
            int m = qu.poll();
            int thisSet = visited[m];
            if(0 < graph[m].size()){
                for(int i = 0; i < graph[m].size(); i++){
                    int v = graph[m].get(i);
                    //해당 노드가 0이면
                    if(visited[v] == 0){
                        qu.add(v);
                        visited[v] = 2 / thisSet; //현재 노드가 1이면 2를, 2면 1을 대입
                    }
                    //해당 노드가 1 or 2이며, 큐에서 꺼낸적이 없고 현재노드와 값이 같다면
                    else if(!visited2[v] && visited[v] == thisSet){
                        isOver = true; //현재 케이스는 이분그래프가 아님
                    }
                }
            }
        }
    }
}