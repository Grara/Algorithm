import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 트리의 지름
//트리문제
//가장 거리가 먼 정점 찾기
//트리는 모두 이어져있다
//모든 트리로부터 공통적으로 먼 정점을 찾은 후 그 정점에서 가장 먼 정점을 찾으면됨

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
        //트리에서 간선의 갯수는 [노드의 갯수 -1]개
        for(int i = 0; i < n-1; i++){
            st= new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken()); //부모노드
            int child = Integer.parseInt(st.nextToken()); //자식노드
            int w = Integer.parseInt(st.nextToken()); //가중치

            graph.get(parent).add(new Node(child, w));
            graph.get(child).add(new Node(parent, w));
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
