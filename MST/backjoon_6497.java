//전체비용에서 필요없는 간선을 걷어냈을 경우 절약할 수 있는 비용을 구해라

import java.io.*;
import java.util.*;



class Edge implements Comparable<Edge> { //간선 클래스
	int start; //시작 정점 번호
	int end; //도착 정점 번호
	int weight; //가중치

	Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		if (weight < o.weight) {
			return -1;
		}
		return 1;
	}

}

public class Main {
	static int[] parents;
	static PriorityQueue<Edge> edges = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//StringTokenizer st;
		Scanner sc = new Scanner(System.in);

		while(true){

			int m = sc.nextInt(); //정점개수
			int n = sc.nextInt(); //간선개수

			if(m == 0 && n == 0){
				break;
			}

			parents = new int[m]; //공통부모를 나타내는 배열 (Union Find용)
			for (int i = 0; i < m; i++) { //부모 배열 초기화
				parents[i] = i;
			}
			
			int currTotal = 0; //현재 거리 총합

			for(int i = 0; i < n; i++) { //기존 연결되어있는 간선 초기화
				int a = sc.nextInt();
				int b = sc.nextInt();
				int weight = sc.nextInt();
				edges.add(new Edge(a, b, weight));
				currTotal += weight;
			}

			int minTotal = 0;
			
			// 크루스칼 알고리즘 수행.
			while (!edges.isEmpty()) {
				Edge edge = edges.poll(); //가중치 오름차순 정렬 간선큐에서 하나씩 뽑음
				if (find(edge.start) != find(edge.end)) {
					minTotal += edge.weight;
					union(edge.start, edge.end);
				}
			}
			
			int ans = currTotal - minTotal;

			bw.append(Integer.toString(ans) + "\n");
			
		}

		bw.flush();
		bw.close();
	}

	public static int find(int x) {
		if (x == parents[x]) {
			return x;
		}

		return parents[x] = find(parents[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parents[y] = x;
		}
	}

}