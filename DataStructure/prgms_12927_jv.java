package DataStructure;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //최대 힙
        Arrays.stream(works).forEach(s->pq.add(s)); //pq에 works를 때려박기
        
        for(int i = 0; i < n; i++){ //
            if(!pq.isEmpty()){
                int m = pq.poll(); 
                if(m-1 != 0) pq.add(m-1); //pq에서 꺼내서 1감소 시키고 다시 집어 넣는다.
                else continue;
            }
        }
        
        return pq.stream().mapToLong(i -> i * i).sum();
    }
}