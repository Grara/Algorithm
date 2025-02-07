//N개의 숫자가 주어짐, 이 숫자들을 랜덤하게 순열로 만들었을 때 순열로 만든 숫자가 K에 의해 딱 나누어질 확률을 구해라

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static String[] Nums;
	static int K,N;
	static int MaxBitmask;
	static long[][] Dp;
	static int[][] Mods;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); //집합수
		Nums = new String[N]; //집합을 저장할 배열
		//배열 넣기
		for(int i=0;i<N;i++) {
			Nums[i]=br.readLine();
		}
		//나눌 수 K
		K=Integer.parseInt(br.readLine());
		//모든 지점 방문 비트마스크
		MaxBitmask = (1<<N)-1;
		//[방문지점][나머지] 기록을 위한 dp
		Dp=new long[1<<N][K];
		//[배열 순서][나머지]에 따른 나머지 값 계산 배열
		Mods = new int[N][K];
		//나머지배열과 dp -1로 초기화
		for(int j=0;j<K;j++) {
			for(int i=0;i<N;i++) {
				Mods[i][j]=-1;
			}
			for(int i=0;i<=MaxBitmask;i++) {
				Dp[i][j]=-1;
			}
		}
		//k로 나눈 나머지가 0인 수 계산 (모든 점 미방문, 나머지 0)
		long answer = dp(0, 0);
		//0이면 0/1 출력
		if(answer==0) {
			System.out.println("0/1");
		//0이 아니면 최대공약수를 이용하여 기약분수형태로 출력
		}else {
			//팩토리얼, 주어진 숫자들로 만들 수 있는 모든 경우의 수 계산
			long fac = 1;
			for(int i=2;i<=N;i++) {
				fac*=i;
			}
			//최대 공약수
			long gcd = getGcd(fac, answer);
			//기약분수 출력
			System.out.println(answer/gcd+"/"+fac/gcd);
			
		}
		br.close();
		
	}

	//dp를 이용하여 이미 계산한 값을 사용함
	public static long dp(int bitmask, int mod) {
		//이미 방문한 경우, dp값 리턴
		if(Dp[bitmask][mod]!=-1) {
			return Dp[bitmask][mod];
		}
		//모든 점을 방문한 경우, mod가 0이면 1, 아니면 0
		if(bitmask==MaxBitmask) {
			return mod==0?1:0;
		}
		//나머지가 0인 개수
		long count = 0;
		//아직 미방문 idx 조사
		for(int i=0;i<N;i++) {
			int idx = 1<<i;
			//아직 방문하지 않았다면
			if((bitmask&idx)==0) {
				/*
				방문여부 표시, mod 업데이트 하여 재귀한 값을 추가
				만들어 놓은 숫자 뒤에 새로운 숫자를 붙였을 때 만들어 놓은 숫자의 나머지를 안다면 새로운 숫자의 나머지도 dp로 구하는게 가능하다
				ex) abc와 xyz를 K로 나눈 나머지가 3일 때 abcdef 랑 xyzdef를 K로 나눈 나머지는 같다.
				*/
				count+=dp(bitmask|idx, getMod(i, mod));
			}
		}
		//dp에 count 저장하고 리턴
		return Dp[bitmask][mod]=count;
	}
	
	//나머지 구하기
	public static int getMod(int idx, int mod) {
		if(Mods[idx][mod]!=-1) return Mods[idx][mod];
		int curr = mod;
		int length = Nums[idx].length();
		for(int i=0;i<length;i++) {
			curr*=10; //10의 자리로 증가
			curr+=(Nums[idx].charAt(i)-'0'); //1의 자리 더하기
			curr%=K; //K로 나눈 나머지 값
		}
		//나머지 출력
		return Mods[idx][mod]=curr;
	}
	
	//최대공약수 구하기
	public static long getGcd(long n, long m) {
		if(m==0) return n;
		return getGcd(m, n%m);
	}
}
