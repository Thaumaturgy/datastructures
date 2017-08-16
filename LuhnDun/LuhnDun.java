import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LuhnDun {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int edges = in.nextInt();
		in.nextLine();
		Graph g = new Graph();
		for(int i = 0; i < edges; i++){
			g.addEdge(in.next().charAt(0), in.next().charAt(0));
		}
		g.bfs();
		in.close();
	}
}

class Graph{
	HashMap<Character, Node> map = new HashMap<>(26);
	Graph(){
		map.put('A', new Node('A'));
		map.get('A').weight = 0; //subtract 1 after if this not present
	}
	void addEdge(char val1, char val2){
		if(!map.containsKey(val1))
			map.put(val1,new Node(val1));
		if(!map.containsKey(val2))
			map.put(val2,new Node(val2));
		map.get(val1).addNeighbor(map.get(val2));
		map.get(val2).addNeighbor(map.get(val1));
	}
	
	void bfs(){
		Queue<Node> q = new LinkedList<>();
		Node temp = map.get('A');
		q.add(temp);
		while(!q.isEmpty()){
			temp = q.poll();
			System.out.println("---------------------------PROCESSING: " + temp.value + "----------------------");
			for(int i = 0; i < temp.neighbors.size(); i++){
				if(temp.neighbors.get(i).visited == true) continue;
					System.out.println(temp.neighbors.get(i).value + " added!");
					q.add(temp.neighbors.get(i));
					if(temp.weight + 1 < temp.neighbors.get(i).weight)
						temp.neighbors.get(i).weight = temp.weight + 1;
			} //Can we terminate immediately upon reaching first 'Z'? 
			// Is it correct to assume that the first Z visited is from shortest path?
			temp.visited = true;
		}
		
		System.out.println(temp.value);
		if(map.get('Z').weight < Integer.MAX_VALUE)
			System.out.println(map.get('Z').weight);
		else
			System.out.println("IMPOSSIBLE");
	}
}

class Node{
	char value;
	ArrayList<Node> neighbors = new ArrayList<>();
	int weight = Integer.MAX_VALUE;
	boolean visited;
	Node(char val){
		value = val;
	}
	
	void addNeighbor(Node n){
		neighbors.add(n);
	}
}