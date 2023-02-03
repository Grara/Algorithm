import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

//요세푸스 문제

fun main( ) {
    val input = readLine()!!.split(" ")
    val n = input[0].toInt()
    val k = input[1].toInt()
    val q = LinkedList<Int>()
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val ans = LinkedList<Int>()
    var cnt = 0

    for(i in 1..n){
        q.add(i)
    }

    //k번마다 ans의 마지막에 현재 큐의 첫번째를 뽑아서 넣음
    //그외엔 계속 첫번째를 마지막으로 보냄
    while (!q.isEmpty()){
        cnt += 1
        if (cnt == k) {
            ans.add(q.poll())
            cnt = 0
        } else q.add(q.poll())
    }

    bw.append(ans.)

    bw.flush()
    bw.close()
}