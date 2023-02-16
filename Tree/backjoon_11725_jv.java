import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 트리의 부모 찾기
//트리문제
//각 노드의 부모노드 찾기, 루트는 1이다.

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    

    public static void main(String[] args)throws Exception{
    	
        int n = Integer.parseInt(br.readLine());
        
        //노드간 연결을 저장할 리스트
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            tree.add(new ArrayList<>()); //초기화
        }
    	
        //노드 연결 입력 받기
        for(int i = 0; i < n - 1; i++){
            st= new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        
        boolean[] visited = new boolean[n+1]; //방문여부
        int[] parentNode = new int[n+1]; //부모노드 저장, parentNode[x] = x의 부모노드

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        //루트노드부터 BFS로 진행
        while(!queue.isEmpty()){
            int v = queue.poll();
            for(int node : tree.get(v)){
                if(!visited[node]){
                    visited[node] = true;
                    queue.add(node);
                    parentNode[node] = v;
                }
            }
        }

        for(int i = 2; i <= n; i++){
            bw.write(Integer.toString(parentNode[i]) + "\n");
        }

        bw.close();

    } //End of main
    

} //End of Main
