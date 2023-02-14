import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 최소비용 구하기2
//DP문제
//다익스트라 이용
//A에서 B까지 가는 최소비용, 거쳐가는 도시의 수, 거쳐가는 도시번호를 순서대로 출력

class Edge implements Comparable<Edge>{
    int target;
    int weight;

    Edge(int target, int weight){
        this.target = target;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e){
        return this.weight - e.weight;
    }
}

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int LIMIT = 2_000_000_000;
    static int n, m, start, end;
    static List<List<Edge>> graph;
    static int[] prev;

    public static void main(String[] args)throws Exception{
    	
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
    	
        prev = new int[n+1]; //prev[x] = 최소경로상에서 x번째 도시에 도달하는 이전도시의 번호
        graph = new ArrayList<>(); //간선 연결정보
        
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>()); //graph초기화
        }

        for(int i =0; i < m; i++){ //간선 정보 입력
            st = new StringTokenizer(br.readLine());
        	
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph.get(a).add(new Edge(b, w)); //단방향 간선
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken()); //시작 도시
        end = Integer.parseInt(st.nextToken()); //도착 도시
        
        bw.write(dijkstra(start, end) + "\n"); //최소 비용 출력

        int idx = end; //맨끝도시부터 시작
        int cnt = 0; //거쳐가는 도시의 수
        Stack<Integer> stack = new Stack<>();

        while(idx != start){
            //도착 도시부터 출발도시까지 거슬러가면서 경유지 역추적
            stack.add(idx);
            idx = prev[idx];
            cnt++;
        }

        cnt++; //출발도시는 포함이 안됐기때문에 +1

        bw.write(cnt + "\n"); //거쳐가는 도시 숫자 출력

        bw.write(start + " "); //먼저 출발 도시부터 출력
        
        
        while(!stack.isEmpty()){
            bw.write(stack.pop() + " "); //거쳐간 도시들 출력
        }
        
        bw.close();
        
    } //End of main

    //다익스트라 알고리즘
    static int dijkstra(int start, int end){
        boolean[] visited = new boolean[n+1];

        int[] minDis = new int[n+1];
        Arrays.fill(minDis, LIMIT);
        minDis[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            int cur = pq.poll().target;

            if(!visited[cur]){
                visited[cur] = true;

                for(Edge edge : graph.get(cur)){

                    if(!visited[edge.target] && minDis[edge.target] > minDis[cur] + edge.weight){
                        minDis[edge.target] = minDis[cur] + edge.weight;
                        prev[edge.target] = cur; //prev[도착지]에 현재 도시 번호 저장
                        pq.add(new Edge(edge.target, minDis[edge.target]));
                    }
                }
            }
        }

        return minDis[end];

    } //End of dijkstra

} //End of Main
