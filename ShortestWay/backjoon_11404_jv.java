package ShortestWay;
import java.io.*;
import java.util.*;

public class Main{
    
    
    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int INF = 2_000_000_00; //2억
        int ans = INF;

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        int[][] minDist = new int[V+1][V+1]; //minDist[i][j] = i지점에서 j지점을 가는 최소거리
        
        for(int i = 1; i <= V; i++){
            for(int j = 1; j <= V; j++){
                minDist[i][j] = INF;
            }
        }

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            minDist[start][end] = Math.min(minDist[start][end], weight);
        }

        //각 노드마다(경유지)
        for(int i = 1; i <= V; i++){
            
            //각 출발지마다
            for(int j = 1; j <= V; j++){
                if(i == j) continue;
                
                //각 도착지마다
                for(int k = 1; k <= V; k++){
                    
                    //도착지가 출발지 혹은 경유지와 같다면
                    if(j == k || i == k) continue;

                    //[출발->경유->도착]이 [출발->도착]보다 더 짧다면 거리 갱신
                    if(minDist[j][i] + minDist[i][k] < minDist[j][k]){
                        minDist[j][k] = minDist[j][i] + minDist[i][k];
                    }
                }
            }
        }

        //순환이 가능한지 체크
        for(int i = 1; i <= V; i++){ //출발지
            for(int j = 1; j <= V; j++){ //도착지

                //출발->도착으로 가는길이 있고 반대도 있을 경우
                if(minDist[i][j]!=INF && minDist[j][i]!=INF) {

                    //순환 최단거리 갱신
                    minDist[i][i] = Math.min(minDist[i][i], minDist[i][j] + minDist[j][i]);
                    ans = Math.min(minDist[i][i], ans); //정답 갱신
                }    
            }
        }
        
        //순환이 불가능하면 -1
        ans = (ans < INF) ? ans : -1;

        System.out.println(ans);

    } //close main

} //close Main
