import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 트리
//정점의 갯수와 간선이 주어졌을 때 트리의 갯수를 구하라, 트리란 사이클이 없는 연결을 의미한다.
//DFS 이용

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    
    static int n, m;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); //노드 연결을 나타내는 그래프
    static boolean[] visited; //방문체크
    static int treeCnt; //트리 갯수
    static int caseCnt; //케이스 번호
    static boolean isNotTree; //트리여부
    
    public static void main(String[] args)throws Exception{
    	
        while(true){
        	n = sc.nextInt();
            m = sc.nextInt();
            
            //종료 조건
            if(n == 0)
                break;
            
            //초기화 시작
            treeCnt = 0;
            graph.clear();
            visited = new boolean[n+1];
            
            for(int i = 0; i <= n; i++){
                graph.add(new ArrayList<>());
            }
            //초기화 종료
            
            //간선 입력
            for(int i = 0; i < m; i++){
            	
                int a = sc.nextInt();
                int b = sc.nextInt();
                
                graph.get(a).add(b);
                graph.get(b).add(a);
            }
            
            
            for(int i = 1; i <=n; i++){
            	isNotTree = false;
            	if(!visited[i]) {
            		dfs(i, -1);
            		if(isNotTree == false)
                		treeCnt++;
            	}
            }
            
            bw.write("Case " + Integer.toString(++caseCnt) + ": ");
            
            switch(treeCnt){
                case 0: bw.write("No trees.\n");
                        break;
                case 1: bw.write("There is one tree.\n");
                        break;
                default : bw.write("A forest of " + Integer.toString(treeCnt)+ " trees.\n");
                        break;
            }
            
        }
        
        bw.close();

    } //End of main
    
    static void dfs(int cur, int prev){
        
    	//이미 방문체크한 노드를 재방문한다면 트리가 아님
        if(visited[cur]) { 
        	isNotTree = true;
        	return;
        }
        
        visited[cur] = true;

        for(int node : graph.get(cur)){
            if(node != prev) { //이전 트리는 방문안함
            	dfs(node, cur);
            }
        }

    } //End of dfs


} //End of Main
