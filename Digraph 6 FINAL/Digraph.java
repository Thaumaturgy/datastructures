/**Dewey L. Sia
 *Directed/Undirected Graph for Dijkstra, Bellman-Ford, Kruskal, and Floyd-Warshall Algorithms
 *Oct 3, 2016
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Digraph {
	HashMap<Integer, Node> map; 
	ArrayList<Node> verts;
	ArrayList<MSTEdge> eList;
	boolean digraph;
    public Digraph(int v, boolean isDigraph) {
    	map = new HashMap<>(v);
    	verts = new ArrayList<>(v);
    	eList = new ArrayList<>(v);
    	digraph = isDigraph;
    }
    
    public void addNode(int val){
    	Node temp = new Node(val);
    	map.put(val, temp);
    	verts.add(temp);
    }
    
    public void addEdge(int val1, int val2, int weight){
    		map.get(val1).addEdge(map.get(val2), weight);
    		eList.add(new MSTEdge(map.get(val1),map.get(val2), weight));
    		if(!digraph)
    			map.get(val2).addEdge(map.get(val1), weight);
    }
    
    public static void main(String[] args)throws FileNotFoundException{
    	Scanner in = new Scanner(new FileReader("C:\\Users\\Core i5\\Desktop\\Digraph 5\\input.txt"));
    	System.out.println("Enter number of verts");
    	int vert = in.nextInt();
        Digraph mg = new Digraph(vert, false);
        for(int i = 0; i < vert; i++){
            mg.addNode(in.nextInt());
        }
        System.out.println("Enter number of edges");
        int edges = in.nextInt();
        for(int i = 0; i < edges; i++){
            mg.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
        }
        System.out.println("Graph is currently directed: "+ mg.digraph);
        mg.floydWarshall();
        mg.dijkstra(1, 4);
        mg.dfs(1);
        mg.bfs(4);
        mg.bellmanFord(1,3);
        mg.kruskal();
        mg.floydWarshall();
        in.close();
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
    	System.out.println();
    	resetFlagsAndWeights();
    }
    
    private void resetFlagsAndWeights(){
    	for(int i = 0; i < verts.size(); i++){ //O(n)
    		verts.get(i).cost = Integer.MAX_VALUE;
    		verts.get(i).visited = false;
    	}
    }
    
    public void dijkstra(int s, int e){
    	Node temp = map.get(s);
    	Node end = map.get(e);
    	PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
			public int compare(Node a, Node b) { //Anonymous Class; defines how Comparator works
				if(a.cost < b.cost) return -1;
				else if(a.cost> b.cost) return 1;
				return 0;
			}
    	});
    	pq.add(temp);
    	temp.cost = 0;
    	while(!pq.isEmpty()){
    		temp = pq.poll();
    		temp.visited = true; //can set it to visited because 
    		//we're sure all the other paths leading to it will just be more expensive
    		//We are sure because other paths leading to it LATER will not be cheaper
    		//because all edge weights are positive
    		for(int i = 0; i < temp.edges.size(); i++){
    			if(!temp.edges.get(i).neighbor.visited){
    				//System.out.println("Now operating on node " +temp.data);
    				if(temp.cost + temp.edges.get(i).weight < temp.edges.get(i).neighbor.cost){
    					temp.edges.get(i).neighbor.cost = temp.cost + temp.edges.get(i).weight;
    					pq.add(temp.edges.get(i).neighbor);
    				}
    			}
    		}
    	}
    	System.out.println("Lowest cost is: "+ end.cost);
    	resetFlagsAndWeights();
    }
    
    public void bellmanFord(int start, int end){
    	bellmanFord(map.get(start), map.get(end));
    	
    }
    
    public void bellmanFord(Node start, Node end){
    	start.cost = 0; //set starting node cost to 0
    	for(int i = 0; i < verts.size()-1; i++){ //Bellman proved we have to iterate V-1 times
    		for(int j = 0; j < verts.size(); j++){ //Iterate through AdjList by visiting each node vert(j)
    			Node current = verts.get(j);
    			for(int k = 0; k < verts.get(j).edges.size(); k++){ 
    				//Iterate through all neighbors of vert(j) using this loop
    				Edge edge = verts.get(j).edges.get(k); //Just a wrapper to shorten code, not important
    				if(verts.get(j).cost != Integer.MAX_VALUE) //Bellman Relax
    					edge.neighbor.cost = Math.min(edge.neighbor.cost, edge.weight + current.cost);
    				//Set neighbor cost to minimum between (neighbor cost) 
    				//and (weight or cost of travel PLUS cost of current)

    			}
    		}
    	}
    	//Iterate through AdjList one more time
    	boolean hasNegativeCycle = false;
    	for(int j = 0; j < verts.size(); j++){
    		for(int k = 0; k < verts.get(j).edges.size(); k++){
    			//a current node + weight to travel to a neighbor still decreases
    			// and becomes cheaper than neighbor cost, then negative cycle exists
    			if(verts.get(j).cost + verts.get(j).edges.get(k).weight < verts.get(j).edges.get(k).neighbor.cost)
    				hasNegativeCycle = true;
    		}
    	}
    	System.out.println("Cost from " +start.data +" to "+end.data +" is: "+end.cost);
    	System.out.println("Has negative cycle: " + hasNegativeCycle);
    }
    
    public void kruskal(){
    	Collections.sort(eList);
    	for(MSTEdge e : eList){
    		System.out.print(e.weight+" ");
    	}
    	System.out.println();
    	for(int i = 0; i < eList.size(); i++){ //for every edge, check if it makes cycle
    		if(!isCycle(eList.get(i).start, eList.get(i).end)){
    			eList.get(i).minimum = true;
    		}else{
    			System.out.println(eList.get(i).start.data + " and " + eList.get(i).end.data +" makes cycle");
    		}
    		resetFlagsAndWeights(); //reset after each check
    	}
    	System.out.println("Edges in Minimum Spanning Tree:");
    	for(int i = 0; i < eList.size(); i++){
    		if(eList.get(i).minimum)
    			System.out.println(eList.get(i).start.data +" "+ eList.get(i).end.data);
    	}
    }
    public boolean isCycle(Node start, Node end){//bfs in hiding
    	Node temp = start;
    	boolean flag = false; //flag for isCycle
    	Queue<Node> q = new LinkedList<>();
    	q.add(temp);
    	while(!q.isEmpty()){
    		temp = q.poll();
    		temp.visited = true;
    		if(temp == end){ //if eventually start/temp becomes end (it was able to traverse to end),
    			//then cycle will exist if trying to connect these two nodes again
    			flag = true;
    			break;
    		}
    		for(int i = 0; i < temp.edges.size(); i++){ //queue in neighbors that satisfy condition below
    			//if neighbor is not visited yet, and the edge through to it is in MST, queue neighbor
    			if(!temp.edges.get(i).neighbor.visited && temp.edges.get(i).inMST){
    				q.add(temp.edges.get(i).neighbor);
    			}
    		}
    	}
    	if(!flag){ //if flag == false OR cycle does not exist,
    		//we will add the edge in MSTEdge upon returning
    		for(int i = 0; i < start.edges.size(); i++){ 
    			if(start.edges.get(i).neighbor == end)
    				start.edges.get(i).inMST = true;
    		}
    		for(int i = 0; i < end.edges.size(); i++){
    			if(end.edges.get(i).neighbor == start){
    				end.edges.get(i).inMST = true;
    			}
    		}
    	}
    	return flag;
    }
    
  //------------CONVERTS ADJ LIST TO ADJ MATRIX----------------//
    public int[][] convertListToMatrix(ArrayList<Node> arr){
    	int[][] mat = new int[arr.size()][arr.size()];
    	for(int i = 0; i < arr.size(); i++){
    		for(int j = 0; j < arr.size(); j++){ //check if contains a neighbor with data for index j
    			int indexOf = -1;
    			for(int k = 0; k < arr.get(i).edges.size(); k++){
    				//Is there road from vert(i) to vert(j)?
    				//uses h to loop through all neighbors of vert(i)
    				// if current neighbor is same as the vert(j) we're inspecting,
    				// it means that vert(i) contains vert(j) as neighbor
    				if(arr.get(i).edges.get(k).neighbor == arr.get(j)){
    					//vert(i) contains road to vert(j)
    					//the neighbor vert(k) is vert(j) if the condition is true;
    					//basically verts.get(i).edges.indexOf(edge containing vert(j))
    					indexOf = k; //set the index to k to access it later
    					break;
    				}
    			}
    			if(indexOf != -1)
    				mat[i][j] = arr.get(i).edges.get(indexOf).weight;
    			else
    				mat[i][j] = Integer.MAX_VALUE/10;
    			//if out of bounds, set to appropriately large number
    		}
    	}
    	return mat;
    }
	//------------CONVERTS ADJ LIST TO ADJ MATRIX----------------//
    
    
    public void floydWarshall(){
    	int[][] mat = convertListToMatrix(verts);
    	// Floyd-Warshall Algorithm-----------------
    	for(int k = 0; k < verts.size(); k++){
    		mat[k][k] = 0; //sets "self-loops" to 0 cost
    		for(int i = 0; i < verts.size(); i++){
    			for(int j = 0; j < verts.size(); j++){
    				if(mat[i][j] > mat[i][k]+ mat[k][j])
    					mat[i][j] = mat[i][k]+ mat[k][j];
    			}
    		}
    	}
    	//------------------------------------------
    	//Printing Matrix after Algorithm
    	for(int i = 1; i <= verts.size(); i++){
    		System.out.print("\t"+i);
    	}
    	System.out.println();
    	for(int i = 0; i < verts.size(); i++){
    		System.out.print((i+1) + "\t");
    		for(int j = 0; j < verts.size(); j++){
    			if(mat[i][j] > Integer.MAX_VALUE/20)
    				System.out.print("inf\t");
    			else
    			System.out.print(mat[i][j]+"\t");
    		}
    		System.out.println();
    	}
    	resetFlagsAndWeights();
    }
}

class MSTEdge implements Comparable<MSTEdge>{
	Node start,end;
	int weight;
	boolean minimum; //flags if this edge is of the minimum weight for MST
	MSTEdge(Node a, Node b, int w){
		start = a;
		end = b;
		weight = w;
	}
	public int compareTo(MSTEdge e){
		if(weight > e.weight)
			return 1;
		else if(weight < e.weight)
			return -1;
		return 0;
	}
}
class Node{
	int cost = Integer.MAX_VALUE;
	ArrayList<Edge> edges = new ArrayList<>();
	int data;
	boolean visited,inMST;
	
	Node(int val){
		data = val;
	}
	
	public void addEdge(Node n, int weight){
		if(!hasNeighbor(n))
			edges.add(new Edge(n, weight));
	}
	
	public boolean hasNeighbor(Node n){
		for(int i = 0; i < edges.size(); i++){
			if(n == edges.get(i).neighbor)
				return true;
		}
		return false;
	}
}
class Edge{
	Node neighbor;
	int weight;
	boolean inMST;
	Edge(Node n, int w){
		neighbor = n;
		weight = w;
	}
}