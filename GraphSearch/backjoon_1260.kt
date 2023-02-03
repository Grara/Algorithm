import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main( ) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m, v) = br.readLine().split(" ").map{it.toInt()}
    val graphs = Array(n+1){LinkedList<Int>()}
    var visited = Array(n+1){false}
    val q = LinkedList<Int>()

    repeat(m){
        val input = br.readLine().split(" ").map{it.toInt()}
        graphs[input[0]].add(input[1])
        graphs[input[1]].add(input[0])
    }
    for(lines in graphs)lines.sort()

    fun DFS(graph : Array<LinkedList<Int>>, v : Int) : Unit {
        visited[v] = true
        bw.append(v.toString()+" ")
        for(i in graph[v]){
            if (visited[i] == false){
                DFS(graph, i)
            }
        }
    }

    fun BFS(graph : Array<LinkedList<Int>>, v : Int) : Unit{
        visited[v] = true
        q.add(v)
        while(q.isNotEmpty()){
            val w = q.pollFirst()
            bw.append(w.toString()+" ")
            for(i in graph[w]){
                if (visited[i] == false){
                    q.add(i)
                    visited[i] = true
                }
            }
        }
    }

    DFS(graphs, v)
    bw.flush()
    bw.append("\n")
    visited = Array(n+1){false}
    BFS(graphs, v)
    bw.close()
}