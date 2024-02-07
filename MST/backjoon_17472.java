//2차원 배열의 맵에 바다칸과 섬칸이 주어짐
//각 섬을 이을 수 있는 다리의 최소비용을 구하라

import java.io.*;
import java.util.*;

class Point{ //좌표 클래스
	int x;
	int y;

	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge> { //다리 클래스
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
	static int N, M;
	static int[] parents;
	static LinkedList<Point> pq = new LinkedList<>();
	static PriorityQueue<Edge> edges = new PriorityQueue<>();
	static int[][] map;
	static boolean[][] visited;
	static int seqNum = 1;
	static int[] dirX = {1, -1, 0, 0};
	static int[] dirY = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); //정점개수
		M = sc.nextInt(); //간선개수

		map = new int[N][M]; //지도
		visited = new boolean[N][M]; //방문여부

		//지도 초기화
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				int val = sc.nextInt();
				map[i][j] = val;
				if(val == 0){
					visited[i][j] = true; //바다는 방문처리
				}
			}
		}
		
		//각 섬을 구분하기위해 넘버링
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				if(!visited[i][j]){
					bfs(i, j);
					seqNum++;
				}
			}
		}

		//구분된 섬의 개수에 맞춰서 공통부모 배열 초기화 
		parents = new int[seqNum];
		for(int i = 0; i < seqNum; i++){
			parents[i] = i;
		}

		//위쪽부터 가로방향으로 순회
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				if(map[i][j] == 0) continue; //현재칸이 바다일 경우 스킵
				int start = map[i][j]; 	//시작섬번호
				int weight = 0; 		//길이
				int end = 0;			//도착섬 번호
				for(int k = j+1; k < M; k++){ //현재칸부터 가로 끝까지 탐색
					if(map[i][k] == map[i][j]) break; //같은섬이면 종료
					else if(map[i][k] == 0) {
						weight++; //바다면 거리 +1
					}
					else {
						end = map[i][k]; //다른섬에 닿으면 도착섬 설정 후 종료
						break;
					}
				}
				if(weight >= 2 && end != 0){ //다리의 길이가 2이상이며, 도착섬이 있을 경우
					Edge e = new Edge(start, end, weight);
					edges.add(e); //우선순위큐에 다리 추가
				}
			}
		}

		//왼쪽부터 세로방향으로 순회
		for(int i = 0; i < M; i++){
			for(int j = 0; j < N; j++){
				if(map[j][i] == 0) continue;
				int start = map[j][i];
				int weight = 0;
				int end = 0;
				for(int k = j+1; k < N; k++){
					if(map[k][i] == map[j][i]) break;
					else if(map[k][i] == 0){
						weight++;
					} 
					else {
						end = map[k][i];
						break;
					}
				}
				if(weight >= 2 && end != 0){
					Edge e = new Edge(start, end, weight);
					edges.add(e); //우선순위큐에 다리 추가
				}
			}
		}

		int ans = 0;

		//크루스칼 알고리즘
		while (!edges.isEmpty()) {
			Edge edge = edges.poll();
			if (find(edge.start) != find(edge.end)) {
				ans += edge.weight;
				union(edge.start, edge.end);
			}
		}

    //공통부모 갱신
		for(int i = 1; i < seqNum; i++){
			parents[i] = find(i);
		}

		//연결안된 다리가 있을 경우 -1로
		int commParent = parents[1];
		for(int i = 1; i < seqNum; i++){
			if(parents[i] != commParent) ans = -1; 
		}

		bw.append(Integer.toString(ans));
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

		if (x > y) {
			parents[y] = x;
		} else {
			parents[x] = y;
		}
	}

	//섬을 구분하기위한 BFS
	public static void bfs(int x, int y){
		pq.add(new Point(x, y));
		
		while(!pq.isEmpty()){
			Point curr = pq.poll();
			map[curr.x][curr.y] = seqNum;
			visited[curr.x][curr.y] = true;

			for(int i = 0; i < 4; i++){ //4방향탐색
				int nx = curr.x + dirX[i];
				int ny = curr.y + dirY[i];
				if(nx >= N || ny >= M || nx < 0 || ny < 0 || visited[nx][ny]){
					continue;
				}
				visited[nx][ny] = true;
				pq.add(new Point(nx, ny));
			}
		}
	}

}