package DataStructure;
import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder()); //최대부터 출력
        PriorityQueue<Integer> minQ = new PriorityQueue<>(); //최소부터 출력
        
        for(String op : operations){
            String[] cmd = op.split(" ");
            
            if(cmd[0].equals("I")){
                //삽입이면 두개의 큐 모두 해줌
                maxQ.add(toInt(cmd[1]));
                minQ.add(toInt(cmd[1]));
            }
            else if(maxQ.isEmpty()) continue; //뽑아야되는데 큐가 비어있으면 무시

            else if(toInt(cmd[1]) == 1) minQ.remove(maxQ.poll()); //"D 1"이면 최대큐에서 뽑고 해당값을 최소큐에서 제거

            else if(toInt(cmd[1]) == -1) maxQ.remove(minQ.poll());//"D -1"이면 반대로
        }
        
        if(maxQ.isEmpty()) return new int[] {0,0}; //마지막에 큐가 비었으면 [0,0]
        else return new int[] {maxQ.peek(), minQ.peek()}; //아니라면 [최대값, 최소값]
    }

    static int toInt(String st){
        return Integer.parseInt(st);
    }
}