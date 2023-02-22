import java.io.*;
import java.util.*;
import java.util.stream.Stream;

//문제명 : 이진 검색 트리
//트리문제
//이진 검색트리의 전위 순회가 주어졌을 때 후위 순회를 구하라

class Node{
    int num;
	public Node left;
    public Node right;

    Node(int num){
        this.num = num;
    }
    
    //자식 노드 추가
    void insert(int n) {
    	if(n < this.num) { //입력받은 값이 현재 노드의 값보다 작을 경우
    		
    		//현재 노드에 left가 null이면 노드생성
    		if(this.left == null) 
    			this.left = new Node(n);
    		else
    			//현재 노드에 left가 있으면 현재 입력값을 left노드의 자식 노드로 추가
    			this.left.insert(n); 
    		
    	}else { //입력받은 값이 현재 노드의 값보다 클 경우
    		if(this.right == null)
    			this.right = new Node(n);
    		else
    			this.right.insert(n);
    	}
    }
}

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Scanner sc =new Scanner(System.in);
    

    public static void main(String[] args)throws Exception{
    	
    	Node root = new Node(Integer.parseInt(br.readLine()));
        
        while(true){
            String input = br.readLine();
            if(input == null || input.equals("")){
                break;
            }
            int n = Integer.parseInt(input);
            root.insert(n);
        }
        
        postOrder(root);

        bw.close();

    } //End of main
    

    static void postOrder(Node node) throws Exception{
        if(node == null)
        	return;
        
        postOrder(node.left);
        postOrder(node.right);
        bw.write(Integer.toString(node.num) + "\n");
    }

} //End of Main
