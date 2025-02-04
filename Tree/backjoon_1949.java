//N개의 마을에 인구수가 주어짐, 각 마을은 트리구조로 연결
//우수마을을 선정해야하는데 가능한 우수마을 인구수의 최대총합을 늘려야함
//우수마을끼리는 인접해서는 안됨

import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Scanner sc = new Scanner(System.in);
    static int N;
    static List<List<Integer>> links = new ArrayList<>(); //마을간의 연결
    static int[] populArr; //각 마을의 인구수
    static boolean[] visited; //방문여부
    static int[] usuTotal; //해당 마을이 우수마을일 때 본인 + 하위트리 최대값
    static int[] notUsuTotal; //해당 마을이 우수마을이 아닐 때 본인 + 하위트리 최대값

    public static void main(String[] args)throws IOException{
      
        N = sc.nextInt();
        int arrLen = N + 1;
        int edgeLen = N - 1;

        populArr = new int[arrLen];
        visited = new boolean[arrLen];
        usuTotal = new int[arrLen];
        notUsuTotal = new int[arrLen];

        for(int i = 1; i < arrLen; i++){
            populArr[i] = sc.nextInt();
        }
        
        for(int i = 0; i < arrLen; i++){
            links.add(new ArrayList<>());
        }
        
        for(int i = 0; i < edgeLen; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            links.get(a).add(b);
            links.get(b).add(a);
        }
        dfs(1);
        
        int ans = Math.max(usuTotal[1], notUsuTotal[1]);
        System.out.println(ans);
    }
    
    public static void dfs(int num){
        List<Integer> currNode = links.get(num);
        visited[num] = true;
        for(int childNum : currNode){
            if(!visited[childNum]){
                dfs(childNum);
                usuTotal[num] += notUsuTotal[childNum];
                //본인이 우수마을이 아니면 자식이 우수마을일때랑 아닐때 둘 중 선택이 가능함
                notUsuTotal[num] += Math.max(usuTotal[childNum], notUsuTotal[childNum]);
            }
        }
        usuTotal[num] += populArr[num];
        return;
    }
}
