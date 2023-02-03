package GraphSearch;
import java.io.*;
import java.util.*;

public class Main{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean visited[];
    
    public static void main(String[] args)throws IOException{
    	int n = Integer.parseInt(br.readLine()) + 1;
        int m = Integer.parseInt(br.readLine());
        LinkedList<Integer>[] graph = new LinkedList[n];
        visited = new boolean[n];
            
        for(int i = 0; i<m; i++){
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            if(graph[a] == null) {
            	graph[a] = new LinkedList<Integer>();
            }
            graph[a].add(b);
            if(graph[b] == null) {
            	graph[b] = new LinkedList<Integer>();
            }
            graph[b].add(a);
        }
        
        System.out.println(DFS(graph, 1) - 1);
    }
    
    public static int DFS(LinkedList[]arr, int v){
        int cnt = 0;
        visited[v] = true;
        cnt += 1;
        if(arr[v].size() > 0) {
	        for(int i : (LinkedList<Integer>)arr[v]){
	            if(!visited[i]){
	            cnt += DFS(arr, i);
	            }
	        }
        }
        return cnt;
    }
}

