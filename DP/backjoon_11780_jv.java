import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 플로이드2
//DP문제
//플로이드 와샬 활용
//모든도시간 서로 이동하는 최소비용, 거쳐가는 도시의 수, 거쳐가는 도시번호를 순서대로 출력

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    static int LIMIT = 1000_000;
    static int n, m;
    static int[][] cost = new int[101][101];
    static int[][] route = new int[101][101];

    public static void main(String[] args)throws Exception{
    	
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
    	
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                if(i!=j)
                    cost[i][j] = 1000001;
            }
        }

        for(int i = 1; i <= m; i++){ //간선 입력
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cost[a][b] = Math.min(cost[a][b], c);
            route[a][b] = a;
        }

        //플로이드 와샬
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(cost[i][j] > cost[i][k] + cost[k][j]){
                        cost[i][j] = cost[i][k] + cost[k][j];
                        route[i][j] = route[k][j];
                    }
                }
            }
        }

        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                if(cost[i][j] <= 100000){
                    bw.write(cost[i][j] + " ");
                }else{
                    bw.write("0 ");
                }
            }
            bw.write("\n");
        }

        for(int i = 1; i <= n; i++){
            path(i, n);
        }

        bw.close();

    } //End of main

    //거쳐간 도시 수와 거쳐간 도시 역추적
    static void path(int start, int n) throws Exception{
        
        for(int end = 1; end <= n; end++){
            //시작점과 도착점이 같으면 0
            if(start == end)
                bw.write("0\n");
            else{
                Stack<Integer> stack = new Stack<>();
                int idx = route[start][end];
                while(idx != 0){
                    stack.push(idx);
                    idx = route[start][idx];
                }

                if(stack.empty())
                    bw.write("0");
                else{
                    bw.write((stack.size() + 1) + " ") ;
                    while(!stack.empty()){
                        bw.write(stack.pop() + " ");
                        
                    }
                    bw.write(Integer.toString(end));
                }
                bw.write("\n");
            }
        }
    } //End of path
    

} //End of Main
