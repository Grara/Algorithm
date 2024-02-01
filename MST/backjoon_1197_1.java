//크루스칼 알고리즘으로 풀었음
//그래프가 주어졌을 때 최소 스패닝 트리를 구해라

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
    int start;
    int end;
    int weight;

    Edge(int start, int end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e){
        return this.weight - e.weight;
    }
}

public class Main{

    static int[] parents;
    static Queue<Edge> edges;

    public static void main(String[] args) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(); //정점개수
        int E = sc.nextInt(); //간선개수
        parents = new int[V+1]; //각 정점의 최상위 부모
        int total = 0;
        edges = new PriorityQueue<>();

        for(int i =0; i <= V; i++){
            parents[i] = i;
        }

        for(int i = 0; i < E; i++){
            int A = sc.nextInt(); //연결할 정점 A
            int B = sc.nextInt(); //연결할 정점 B
            int C = sc.nextInt(); //가중치

            edges.add(new Edge(A, B, C));
        }
        
        while(!edges.isEmpty()){
            Edge e = edges.poll();
            if(find(e.start) == find(e.end)){
                continue;
            }

            union(e.start, e.end);
            total += e.weight;
        }
        
        System.out.println(total);
    }

    static int find(int x){
        if(parents[x] == x){
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    static void union(int node1, int node2){
        int x = find(node1);
        int y = find(node2);

        if(x == y){
            return;
        }

        if(x < y) parents[y] = x;
        else parents[x] = y;
    }
}