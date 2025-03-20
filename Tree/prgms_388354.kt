//프로그래머스 - 홀짝트리
val dp1 = HashMap<Int, HashMap<Int, Boolean>>() //홀짝dp dp[i][j] = 부모가 i일때 j와 자식들은 전부 홀짝인가?
val dp2 = HashMap<Int, HashMap<Int, Boolean>>() //역홀짝dp

class Solution {

    fun solution(nodes: IntArray, edges: Array<IntArray>): IntArray {

        val map = HashMap<Int, Node>()

        nodes.forEach {
            map[it] = Node(it)
            dp1[it] = HashMap()
            dp2[it] = HashMap()
        }

        edges.forEach {
            val a = it[0]
            val b = it[1]
            map[a]?.links?.add(map[b])
            map[b]?.links?.add(map[a])
        }

        var oddEvenCnt = 0
        var reverseCnt = 0

        map.forEach{num, node ->
            val isOddEven = node.isOddEvenWithChildren(node, true)
            if(isOddEven) oddEvenCnt++
            val isReverse = node.isOddEvenWithChildren(node, false)
            if(isReverse) reverseCnt++
        }

        return intArrayOf(oddEvenCnt, reverseCnt)
    }
}

class Node constructor(num:Int){
    val num = num
    val links = ArrayList<Node?>()
    //isNormal = 홀짝, 역홀짝 탐색 구분
    fun isOddEvenWithChildren(parent:Node, isNormal: Boolean): Boolean{
        val dp = if(isNormal) dp1 else dp2
        val pNum = parent.num

        if(dp[pNum]?.get(num) != null) {
            return dp[pNum]!!.get(num)!!
        }

        //내가 부모일 경우는 연결된 다른 모든 노드가 자식노드임
        //내가 자식일 경우는 -1
        var cnt = if(pNum == num) links.size else links.size - 1
        if(cnt < 0) cnt = 0

        //홀짝트리 탐색일 때는 num과 cnt의 2로 나눈값이 같아야 됨, 역홀짝일 경우 반대
        if((num % 2 == cnt % 2) == isNormal) {
            for(i in 0..links.size-1){
                val item = links.get(i)
                if(item != parent){ //부모는 탐색안함
                    var flag = item!!.isOddEvenWithChildren(this, isNormal)
                    if(!flag){ //탐색도중 한개라도 false면 false
                        dp[pNum]?.set(num, false)
                        return false
                    }
                }
            }
            dp[pNum]?.set(num, true) //전부 true여야 true
            return true

        } else{
            dp[pNum]?.set(num, false)
            return false
        }
    }
}
