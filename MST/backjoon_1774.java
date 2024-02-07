//이미 연결된 간선이 존재할 때, MST를 만들기위해 추가할 간선들의 최소비용 합을 구하라

import java.io.*;
import java.util.*;

class Point { //정점 클래스
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> { //간선 클래스
	int start; //시작 정점 번호
	int end; //도착 정점 번호
	double weight; //가중치

	Edge(int start, int end, double weight) {
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
	static Point[] points;
	static PriorityQueue<Edge> edges = new PriorityQueue<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//StringTokenizer st;
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); //정점개수
		int M = sc.nextInt(); //이미 있는 간선개수

		points = new Point[N]; //정점들
		parents = new int[N]; //공통부모를 나타내는 배열 (Union Find용)

		for (int i = 0; i < N; i++) { //부모 배열 초기화
			parents[i] = i;
		}

		for(int i = 0; i < N; i++) { //정점 초기화
			int x = sc.nextInt();
			int y = sc.nextInt();
			points[i] = new Point(x, y);
		}
		
		for(int i = 0; i < M; i++) { //기존 연결되어있는 간선 초기화
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			union(a, b);
		}

		for (int i = 0; i < N; i++) { //모든 정점들을 연결
			for (int j = i + 1; j < N; j++) {
				double weight = distance(points[i], points[j]);
				edges.add(new Edge(i, j, weight));
			}
		}

		double ans = 0;
		
		// 크루스칼 알고리즘 수행.
		while (!edges.isEmpty()) {
			Edge edge = edges.poll(); //가중치 오름차순 정렬 간선큐에서 하나씩 뽑음
			if (find(edge.start) != find(edge.end)) {
				ans += edge.weight;
				union(edge.start, edge.end);
			}
		}

		bw.write(String.format("%.2f", ans) + "\n");
		bw.flush();
		bw.close();
	}

	public static double distance(Point p1, Point p2) { //정점간의 거리 구하기
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
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