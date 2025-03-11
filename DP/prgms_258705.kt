//프로그래머스 - 산 모양 타일링

class Solution {

    fun solution(n: Int, tops: IntArray): Int {
        val MOD = 10007
        val dp1 = IntArray(n)
        val dp2 = IntArray(n)
        dp1[0] = 1
        dp2[0] = 2 + tops[0]

        for(i: Int in 1..n - 1){
            dp1[i] = (dp1[i-1] + dp2[i-1]) % MOD
            dp2[i] = (
                        (dp1[i-1] * (1 + tops[i])) +
                        (dp2[i-1] * (2 + tops[i]))
                     ) % MOD
        }

        return (dp1[n-1] + dp2[n-1]) % MOD
    }
}