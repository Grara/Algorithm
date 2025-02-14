//프로그래머스 - N+1 카드게임

import java.util.*;

public class Solution {

    public int solution(int coin, int[] cards) {
        boolean[] hasCard = new boolean[cards.length + 1]; //카드 전체 순회 시 체크용
        boolean[] isInit = new boolean[cards.length + 1]; //해당 숫자의 카드가 게임시작 시 갖고있는 카드인지

        //카드쌍을 담을 우선순위 큐
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if(o1.enabledRound != o2.enabledRound){
                    return o1.enabledRound - o2.enabledRound;
                } else{
                    return o1.cost - o2.cost;
                }
                
            }
        });

        int remainPair = 0; //낼 수 있는 카드쌍
        int initRange = (cards.length / 3) - 1; //시작 시 보유할 카드 인덱스 범위

        //카드들을 순회하면서 시작 시 보유한 카드쌍 수, 큐에 넣을 카드쌍 생성
        for(int i = 0; i < cards.length; i++){
            int num = cards[i];
            
            if(!hasCard[num]) hasCard[num] = true;
            if(i <= initRange) isInit[num] = true;

            int pairNum = cards.length - num + 1;
            int round;
            if((i - initRange) % 2 == 0){
                round = (i - initRange) / 2;
            } else {
                round = (i - initRange) / 2 + 1;
            }

            if(hasCard[num] && hasCard[pairNum]){
                if(isInit[num] && isInit[pairNum]){
                    remainPair += 1;
                } else if(!isInit[num] && !isInit[pairNum]){
                    pq.add(new Pair(round, 2));
                } else {
                    pq.add(new Pair(round, 1));
                }
            }
        }
        
        Pair first = pq.peek();
        
        int ans = 0;
        boolean isOver = false;

        //게임시작 시 한번 실행
        if(remainPair < first.enabledRound - 1){
            ans = remainPair + 1;
            isOver = true;
        }

        int currRound = first.enabledRound;
        remainPair = remainPair - (first.enabledRound - 1);
        int keep2cost = 0;

        //카드쌍은 3종류임
        //1.맨처음부터 보유한 카드쌍
        //2.카드중 하나는 처음에 보유, 하나는 라운드가 진행하면서 코인을 지불해야함(1코인)
        //3.둘 다 코인을 지불해야함(2코인)
        //가능하면 1코인으로 카드쌍을 맞추고 불가피할 때만 2코인짜리 쌍을 사용
        while(!isOver){
            
            //낼 수 있는 2코짜리 쌍 갯수
            int enable2cost = coin / 2 >= keep2cost ? keep2cost : coin / 2;
            
            Pair p = pq.poll();
            if(p == null){
                ans = currRound + remainPair + enable2cost;
                break;
            }

            int needPairCnt = p.enabledRound - currRound;

            if(remainPair + enable2cost >= needPairCnt) {
                if(remainPair >= needPairCnt) {
                    remainPair -= needPairCnt;
                } else {
                    int need2cost = needPairCnt - remainPair;
                    keep2cost -= need2cost;
                    coin -= need2cost * 2;
                    remainPair -= needPairCnt - need2cost;
                }
            } else {
                ans = currRound + remainPair + enable2cost;
                break;
            }

            currRound = p.enabledRound;
            
            if(p.cost == 1 && coin > 0){
                remainPair += 1;
                coin -= 1;
            } else {
                keep2cost += 1;
            }
        }

        int maxRound = (cards.length - (cards.length / 3)) / 2 + 1;
        ans = ans >= maxRound ? maxRound : ans;

        return ans;
    }

    class Pair {
        int enabledRound; //해당 쌍을 사용가능해지는 라운드
        int cost;
        Pair(int enabledRound, int cost){
            this.cost = cost;
            this.enabledRound = enabledRound;
        }
    }
}
