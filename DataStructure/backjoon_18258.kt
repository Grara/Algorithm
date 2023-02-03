import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main( ) {
    val n : Int = readLine()!!.toInt()
    val q : Queue<Int> = LinkedList<Int>()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    for(i in 0 until n){
        val op = readLine()!!

        if (op.contains("push")){
            q.offer(op.split(" ")[1].toInt())
        }

        else {
            when (op) {
                "pop" -> if (q.isEmpty()) bw.append("-1\n") else bw.append("${q.poll()}\n")
                "size" -> bw.append("${q.size}\n")
                "empty" -> if (q.isEmpty()) bw.append("1\n") else bw.append("0\n")
                "front" -> if (!q.isEmpty()) bw.append("${q.peek()}\n") else bw.append("-1\n")
                "back" -> if (!q.isEmpty()) bw.append("${q.last()}\n") else bw.append("-1\n")
            }
        }
    }
    bw.flush()
    bw.close()
}