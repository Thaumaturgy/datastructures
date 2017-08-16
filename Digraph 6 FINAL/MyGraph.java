/**
 * Dewey L. Sia
 *9/21/16
 *MyGraph
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MyGraph {
	private ArrayList<Node> verts;
	private HashMap<Integer, Node> map;
	
	public MyGraph(int v){
        map = new HashMap<Integer, Node>(v);
        verts = new ArrayList<Node>(v);
    }
    
    public void addNode(int val){ //O(1)
    	Node temp = new Node(val);
    	verts.add(temp);
    	map.put(val, temp);
    }
    
    public void addEdge(int val1, int val2){ //O(1)
    	map.get(val1).makeEdge(map.get(val2));
    	map.get(val2).makeEdge(map.get(val1));
    }
    
    public void addEdge(int val1, int val2, int weight){ //O(1)
    	map.get(val1).makeEdge(map.get(val2),weight);
    	map.get(val2).makeEdge(map.get(val1),weight);
    }
    
    public void bfs(int val){ 
    	Queue<Node> q = new LinkedList<Node>();
    	System.out.print("BFS: ");
    	Node temp = map.get(val);
    	q.add(temp);
    	while(!q.isEmpty()){
    		temp = q.poll();
    		if(!temp.visited){
    			temp.visited = true;
        		System.out.print(temp.data + " ");
        		for(int i = 0; i<temp.edges.size(); i++){ //O(|E| of V)
        			q.add(temp.edges.get(i).neighbor);
        		}
    		}
    	}
    	resetFlagsAndWeights();
    }
    
    private void resetFlagsAndWeights(){
    	for(int i = 0; i < verts.size(); i++){ //O(n)
    		verts.get(i).cost = Integer.MAX_VALUE;
    		verts.get(i).visited = false;
    		verts.get(i).refreshWalks();
    	}
    }
    
    public void dfs(int val){
    	Node n = map.get(val);
    	System.out.print("DFS: ");
    	dfs(n);
    	resetFlagsAndWeights();
    }
    
    private void dfs(Node n){
    	System.out.print(n.data + " ");
    	n.visited = true;
    	for(int i = 0; i < n.edges.size(); i++){
    		if(n.edges.get(i).neighbor.visited == false)
    			dfs(n.edges.get(i).neighbor);
    	}
    }
    
     /*public static void main(String[] args)throws FileNotFoundException{
        Scanner in = new Scanner(new FileReader("C:\\Users\\Core i5\\Desktop\\Graph 5\\input.txt"));
        System.out.println("Enter number of vertices");
        int vert = in.nextInt();
        MyGraph mg = new MyGraph(vert);
        for(int i = 0; i < vert; i++){
            mg.addNode(in.nextInt());
        }
        System.out.println("Enter number of edges");
        int edges = in.nextInt();
        for(int i = 0; i < edges; i++){
            mg.addEdge(in.nextInt(), in.nextInt());
        }
         
        mg.printList();
        mg.bfs(10);
        mg.dfs(50);
    }*/
    
    public static void main(String[] args)throws FileNotFoundException{
    	Scanner in = new Scanner(new FileReader("C:\\Users\\Core i5\\Desktop\\Graph 6\\dijkstra.txt"));
    	System.out.println("Enter number of verts");
    	int vert = in.nextInt();
        MyGraph mg = new MyGraph(vert);
        for(int i = 0; i < vert; i++){
            mg.addNode(in.nextInt());
        }
        System.out.println("Enter number of edges");
        int edges = in.nextInt();
        for(int i = 0; i < edges; i++){
        	//System.out.println("Entered loop");
            mg.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        mg.dijkstra(1,5);
        mg.dfs(1);
        mg.bfs(4);
    }
    
    public void printList(){
    	System.out.println("Vertices" + "\t" +"Adjacent Vertices");
    	for(int i = 0; i < verts.size(); i++){
    		Node temp = verts.get(i);
    		System.out.print(temp.data + "\t|");
    		for(int j = 0; j < temp.edges.size(); j++){
    			System.out.print(temp.edges.get(j).neighbor.data + "\t");
    		}
     		System.out.println();
    	}
    }    
    
    public void dijkstra(int start, int end){
    	dijkstra(map.get(start), map.get(end));
    }
    
    public void dijkstra(Node start, Node end){
    	start.cost = 0;
    	Queue<Node> q = new LinkedList<>();
    	Node temp = start;
    	q.add(temp);
    	while(!q.isEmpty()){
    		temp = q.poll();
    		if(temp == end) break;
    		for(int i = 0; i < temp.edges.size(); i++){
    			Edge cheapest = temp.getCheapestEdge(); 
    			if(cheapest == null)continue;
    			q.add(cheapest.neighbor);
    			cheapest.neighbor.cost = Math.min(cheapest.neighbor.cost, cheapest.weight + temp.cost);
    		}
    	}
    	System.out.println((temp == end) + "temp = " +temp.data + "end = "+end.data);
		System.out.println("Path found! Shortest path is "+temp.cost);
    	resetFlagsAndWeights();
    	//reset all cost to infinity.
    }
}