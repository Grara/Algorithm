package ShortestWay;
import java.io.*;
import java.util.*;

public class Main{
    
    static int N, M;
    static ArrayList<Node>[] graph;
    static long minDist[];
    static boolean has_minus_cycles;
    static int INF = 6000000;
    static int Start = 1;
    
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        minDist = new long[N + 1];
        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
            minDist[i] = INF;
        }
        
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b,c));
        }

        bell(Start);

        if(has_minus_cycles){
            System.out.println(-1);
        }else {
            for(int i = 1; i <= N; i++){
                if(i != Start){
                    if(minDist[i] == INF) System.out.println(-1);
                    else System.out.println(minDist[i]);
                }
            }
        }
    }

    private static void bell(int start){
        minDist[start] = 0;

        for(int i = 0; i < N - 1; i++){ //최대 방문할 수 있는 노드의 개수만큼
            for(int j = 1; j <= N; j++){ //j지점 주변에 있는 지점들을 갱신할 수 있는지 확인
                for(int k = 0; k < graph[j].size(); k++){ //j지점에서 출발할 수 있는 노선
                    Node next = graph[j].get(k);
                    
                    //최단 경로 갱신이 가능하다면 갱신
                    if(minDist[j] + next.weight < minDist[next.target] && minDist[j] != INF){
                        minDist[next.target] = minDist[j] + next.weight;
                    }
                }
            }
        }
        
        //최단 경로를 구했는데 , 한 번 더 갱신이 일어나면 음수 순환이 존재
        for(int j = 1; j <= N; j++){
            for(int k = 0; k < graph[j].size(); k++){
                Node next = graph[j].get(k);

                if(minDist[j] + next.weight < minDist[next.target] && minDist[j] != INF){
                    has_minus_cycles = true;
                    return;
                }
            }
        }

    }

}

class Node{
    int target, weight;

    public Node(int target, int weight){
        this.target = target;
        this.weight = weight;
    }
}