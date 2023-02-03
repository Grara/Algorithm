import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*

fun main( ) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val DQ : Deque<String> = LinkedList<String>()
    repeat(readLine()!!.toInt()) {
        val input = readLine()!!.split(" ")

        when(input[0]){
            "push_front" -> DQ.addFirst(input[1])
            "push_back" ->  DQ.addLast(input[1])
            "pop_front" -> if(DQ.isNotEmpty()) bw.append(DQ.pollFirst()+"\n") else bw.append("-1\n")
            "pop_back" -> if(DQ.isNotEmpty()) bw.append(DQ.pollLast()+"\n") else bw.append("-1\n")
            "size" -> bw.append("${DQ.size}\n")
            "empty" -> if(DQ.isEmpty()) bw.append("1\n") else bw.append("0\n")
            "front" -> if(DQ.isNotEmpty()) bw.append("${DQ.peekFirst()}\n") else bw.append("-1\n")
            "back" -> if(DQ.isNotEmpty()) bw.append("${DQ.peekLast()}\n") else bw.append("-1\n")
        }
    }

    bw.flush()
    bw.close()
}