//프로그래머스 - 에어컨
//냅색문제

import kotlin.math.min

class Solution {
    fun solution(temperature: Int, t1: Int, t2: Int, a: Int, b: Int, onboard: IntArray): Int {
        val inf = 999999999
        val _t1 = t1+10
        val _t2 = t2+10
        val _temperature = temperature + 10

        val dp = Array(onboard.size, {
            IntArray(51, {inf} )
        })

        dp[0][_temperature] = 0
        val toTemp = if(_temperature > _t2) -1 else 1 //에어컨 안켰을 때 실외온도로의 변화

        for(i in 1 until onboard.size){
            for(j in 0..50){
                var minCost = inf

                //해당 시간에 승객이 타고있고 온도가 희망온도사이일때, 또는 승객이 안타고있을 때
                if( (onboard[i] == 1 && j in _t1.._t2) || onboard[i] == 0){
                    if(j + toTemp in 0..50){ //에어컨 안켜서 실외온도로 향할 때
                        minCost = min(minCost, dp[i-1][j+toTemp])
                    }
                    if(j == _temperature){ //에어컨 안키고 실외온도랑 같을 때
                        minCost = min(minCost, dp[i-1][j])
                    }
                    if(j - toTemp in 0..50){ //실내온도가 희망온도 밖인 상태에서 에어컨 키고 희망온도로 향할 때
                        minCost = min(minCost, dp[i-1][j-toTemp] + a)
                    }
                    if(j in _t1.._t2){ //실내온도가 희망온도인 상태에서 에어컨틀었을 때
                        minCost = min(minCost, dp[i-1][j] + b)
                    }

                    dp[i][j] = minCost
                }
            }
        }

        var ans = inf
        for(i in 1..50){
            ans = min(ans, dp[onboard.size-1][i])
        }

        return ans
    }
}
