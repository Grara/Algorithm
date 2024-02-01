//랜덤한 좌표에 별자기가 배치될 때 별자리를 만드는 최소 비용
//간선이 따로 주어지지않음
//최대 100개의 별까지 주어지므로 그냥 일단 모든 별을 연결해서 계산..
//피타고라스의 법칙 이용

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Point {
	int num;
	double x;
	double y;

	Point(int num, double x, double y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> {
	int start;
	int end;
	double weight;

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
	static int[] parent;
	static ArrayList<Edge> edgeList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Point[] points = new Point[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			points[i] = new Point(i, x, y);
		}
		
		// 모든 별들 간의 간선과 비용 정보를 edgeList에 넣어 둔다.
		edgeList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double weight = distance(points[i], points[j]);

				edgeList.add(new Edge(points[i].num, points[j].num, weight));
			}
		}
		Collections.sort(edgeList); // 간선의 비용을 기준으로 오름차순 정렬.

		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		double ans = 0;
		
		// 크루스칼 알고리즘 수행.
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);

			if (find(edge.start) != find(edge.end)) {
				ans += edge.weight;
				union(edge.start, edge.end);
			}
		}

		bw.write(ans + "\n");
		bw.flush();
		bw.close();
		br.close();
	}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
	}

}