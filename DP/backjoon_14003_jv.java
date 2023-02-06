import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//DP문제
//이분탐색과 LIS 활용
//가장 긴 증가하는 부분수열의 길이와 수열의 구성요소 모두 구하기

public class Main{

    public static void main(String[] args)throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
    	
        List<Integer> list = new ArrayList<>();
        int[] seq = new int[N];
        int[] indexArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //
        for(int i = 0; i < N; i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }

        list.add(Integer.MIN_VALUE);

        for(int i = 0; i < N; i++){
            int num = seq[i];
            int left = 0;
            int right = list.size()-1;

            if(num > list.get(list.size()-1)){
                list.add(num);
                indexArr[i] = list.size()-1;
            }
            
            if(num < list.get(list.size()-1)){
                int mid = (right + left) / 2;

                while(left < right){
                    if(list.get(mid) >= num) right = mid;
                    else left = mid;
                }
                list.set(right, num);
                indexArr[i] = right;
            }
        }

        Stack<Integer> stack = new Stack<>();

        int idx = list.size()-1;

        for(int i = N-1; i >= 0; i--){
            if(indexArr[i] == idx){
                idx--;
                stack.push(seq[i]);
            }
        }

        sb.append(list.size()-1 + "\n");

    	while(!stack.isEmpty()){
            sb.append(stack.pop() + " ");
        }

        bw.write(sb.toString());
        bw.close();

    } //End of main


} //End of Main
