//트리에서 인접하지않은 정점들로만 집합을 만들 때 최대 가중치 집합과 해당 집합에 포함된 정점들을 구해라

import java.io.*
import java.lang.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

fun main() {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sc: Scanner = Scanner(System.`in`)

    val n = sc.nextInt() //정점 개수
    val weights = Array(n + 1) {0} //각 노드의 가중치
    val isParent = Array(n + 1) {false} //방문여부 체크용
    val links = ArrayList<ArrayList<Int>>() //각 노드간의 연결

    // [n][0] = n번 정점이 포함안됐을 때 서브트리의 최대값
    // [n][1] = n번 정점을 포함했을 때
    val maxValues = Array(n + 1) {Array(2) {0} }

    // [n][0] = n번 정점이 포함안됐을 때 서브트리의 구성 정점들
    // [n][1] = n번 정점을 포함했을 때
    val maxValueNodes = Array(n + 1) {Array(2) {ArrayList<Int>()} }

    var ansValue = 0 //최대값
    lateinit var ansList : List<Int> //최대 집합에 포함되는 정점들

    for(i in 1 .. n){
        weights[i] = sc.nextInt()
    }

    repeat(n + 1){
        links.add(ArrayList())
    }

    repeat(n-1){
        val a = sc.nextInt()
        val b = sc.nextInt()
        links.get(a).add(b)
        links.get(b).add(a)
    }

    fun dfs(num: Int) {
        val currNode = links.get(num)
        isParent[num] = true
        var hasChild = false
        maxValues[num][1] = weights[num]
        maxValueNodes[num][1].add(num)
        currNode.forEach(){ it ->
            if(!isParent[it]){
                hasChild = true
                dfs(it)

                //본인포함 불가일 때 최대값 = 자식 포함 불가/가능 최대값 중 큰거
                maxValues[num][0] += max(maxValues[it][0], maxValues[it][1])
                if(maxValues[it][0] > maxValues[it][1]) {
                    maxValueNodes[num][0].addAll(maxValueNodes[it][0])
                } else {
                    maxValueNodes[num][0].addAll(maxValueNodes[it][1])
                }

                //본인포함 가능일 때 최대값 = 본인 + 자식포함 불가 시 최대값
                maxValues[num][1] += maxValues[it][0]
                maxValueNodes[num][1].addAll(maxValueNodes[it][0])
            }
        }

        if(!hasChild){
            maxValues[num][0] = 0
            maxValues[num][1] = weights[num]
        }

        if(ansValue < maxValues[num][0]){
            ansValue = maxValues[num][0]
            ansList = maxValueNodes[num][0]
        }
        if(ansValue < maxValues[num][1]){
            ansValue = maxValues[num][1]
            ansList = maxValueNodes[num][1]
        }
    }

    dfs(1)
    ansList = ansList.sorted()

    println(ansValue)
    println(ansList.joinToString(" "))
}
