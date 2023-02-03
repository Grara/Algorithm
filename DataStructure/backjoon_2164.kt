import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main( ) {
    val n : Int = readLine()!!.toInt()
    val q : Queue<Int> = LinkedList<Int>()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    for(i in 1..n){
        q.add(i)
    }

    while (q.size != 1){
        q.poll()
        q.add(q.poll())
    }

    println(q.poll())
}