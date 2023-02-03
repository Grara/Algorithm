import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int idx;
    int cost;
    Node(int idx, int cost){
        this.idx = idx;
        this.cost = cost;
    }

    public int compareTo(Node n){
        return this.cost - n.cost;
    }

}

public class Main{
    
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt() + 1;
        int E = sc.nextInt();
        int K = sc.nextInt();
        List<Node>[] graph = new ArrayList[V];
        int[] dist = new int[V];

        for(int i = 0; i < graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < E; i++){
            String[] input = br.readLine().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            int w = Integer.parseInt(input[2]);
            graph[u].add(new Node(v, w));
        }

        Arrays.fill(dist, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(K, 0));
        dist[K] = 0;
        
        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            if(dist[curNode.idx] < curNode.cost){
                continue;
            }
            for(int i = 0; i < graph[curNode.idx].size(); i++){
                Node nextNode = graph[curNode.idx].get(i);
                if(dist[nextNode.idx] > curNode.cost + nextNode.cost){
                    dist[nextNode.idx] = curNode.cost + nextNode.cost;
                    pq.add(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
        
        for(int i = 1; i < dist.length; i++){
            if(dist[i] != Integer.MAX_VALUE){
                bw.append(dist[i] + "\n");
            }
            else bw.append("INF\n");
        }
        bw.flush();
    }
}