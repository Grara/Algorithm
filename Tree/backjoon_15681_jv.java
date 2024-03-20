//정점과 간선들의 연결과 루트가 주어진다. (가중치 X)
//특정 정점이 주어졌을 때 해당 정점을 기준으로하는 서브트리의 정점의 갯수를 구해라 (해당 정점 포함)

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N, R, Q;
	static List<List<Integer>> links = new ArrayList<>();
	static boolean[] isParent;
	static int[] subTreeCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		R = sc.nextInt();
		Q = sc.nextInt();

		isParent = new boolean[N+1];
		subTreeCnt = new int[N+1];
		for(int i = 0; i <= N; i++){
			links.add(new ArrayList<>());
		}

		for(int i = 0; i < N-1; i++){
			int U = sc.nextInt();
			int V = sc.nextInt();
			
			links.get(U).add(V);			
			links.get(V).add(U);
		}

		getSubTreeCount(R);

		for(int i = 0; i < Q; i++){
			int U = sc.nextInt();
			
			bw.append(Integer.toString(subTreeCnt[U]));
			bw.append("\n");
		}

		bw.close();

	}

	public static int getSubTreeCount(int num){
		int result = 1;
		isParent[num] = true;
		for(int node : links.get(num)){
			if(!isParent[node]){
				result += getSubTreeCount(node);
			}
		}
		subTreeCnt[num] = result;
		return result;
	}

}
