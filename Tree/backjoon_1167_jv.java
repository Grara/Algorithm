import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 트리의 지름
//트리문제
//가장 거리가 먼 정점 찾기

class Node{
    int e;
    int weight;

    Node(int e, int weight){
        this.e = e;
        this.weight = weight;
    }
}

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    static int max = 0; //노드간 최장 거리
    static int commonLongNode; //모든 정점으로부터 가장 먼 노드

    public static void main(String[] args)throws Exception{
    	
        int n = Integer.parseInt(br.readLine());
        
        //노드간 연결을 저장할 리스트
        graph = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<>()); //초기화
        }
    	
        //노드 연결 입력 받기
        for(int i = 0; i < n; i++){
            st= new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()); //출발점
            while(true){
                int target = Integer.parseInt(st.nextToken()); //도착지
                if(target == -1) //-1을 입력받으면 종료
                    break;
                int w = Integer.parseInt(st.nextToken()); //간선 가중치
                graph.get(start).add(new Node(target, w));
            }
        }

        
        visited = new boolean[n+1]; //방문여부
        dfs(1, 0); //1부터 dfs 시작

        visited = new boolean[n+1]; //방문여부
        dfs(commonLongNode, 0); //commonLongNode부터 dfs 시작

        System.out.println(max);

    } //End of main
    
    //dfs
    public static void dfs(int x, int len){
        
        if(len > max){ //현재 누적된 길이가 최장거리보다 길면
            max = len; //최장거리 갱신
            commonLongNode = x; //공통 최장 정점 갱신
        }

        visited[x] = true;

        for(Node n : graph.get(x)){
            if(!visited[n.e]){
                visited[n.e] = true;
                dfs(n.e, n.weight + len);
            }
        }
    } //End of dfs


} //End of Main
