//연결된 모든 노드가 얼리어댑터일 때 해당 노드도 얼리어댑터가 됨
//모든 노드를 얼리어댑터로 만드는데 필요한 얼리어댑터 노드의 최소 개수

import java.io.*
import java.lang.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

var n : Int =0
var links = ArrayList<ArrayList<Int>>()
lateinit var isParent : Array<Boolean>
lateinit var isEarlyAdapter : Array<Boolean>
var ans = 0

fun main() {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sc: Scanner = Scanner(System.`in`)
    n = sc.nextInt() //정점 개수
    isParent = Array(n+1) {false}
    isEarlyAdapter = Array(n+1) {false}

    repeat(n + 1){
        links.add(ArrayList())
    }

    repeat(n-1){
        val a = sc.nextInt()
        val b = sc.nextInt()
        links.get(a).add(b)
        links.get(b).add(a)
    }

    dfs(1)
    println(ans)
}

fun dfs(num: Int) {
    val currNode = links.get(num)
    isParent[num] = true
    currNode.forEach(){
        if(!isParent[it]){
            dfs(it)
            if(!isEarlyAdapter[it]) {
                isEarlyAdapter[num] = true
            }
        }
    }
    if(isEarlyAdapter[num]) ans++
}
