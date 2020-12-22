package freecodecamp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/* NOTE ON GENERIC ARRAY OF OBJECTS: 
 * Java can NOT create generic array of LinkedList
ERROR: adjListArray = new LinkedList<>[v];
ERROR: adjListArray = new LinkedList<Integer>[v];

SOLUTION: remove <Integer> and it gets infered at runtime
          adjListArray = new LinkedList[v]

   
   Adjacency List representation
   https://www.programiz.com/dsa/graph-adjacency-list
   
   -addEdge
   -BFS (Queue & boolean[] visited)
   -DFS (recursive * boolean[] visited)
   -disconnected graph DFS() ; GO DFS for all vertices and each time check visited[]  
    ( if a connected graph, one DFS(int vertex) should visit all vertices)
    
    # of edges (E) in "Connected" "Undirected" graphs:
    IF G(V,E) is a connected graph:
           (Tree) V-1  <= G  <= V(V-1)/2 (full graph)
           Tree is a Connected undirected graph with NO CYCLE (hence, with MINUMUM number of edges (V-1) for a connected graph)
    
*/
public class GraphAdjacencyList {
	
	int nOfVertices;
	LinkedList<Integer>[] adjListArray;//array of LinkedLists
	
	GraphAdjacencyList(int v){
		nOfVertices = v;
		adjListArray = new LinkedList[v];//array of LinkedList
		/* can not create generic array of LinkedList
			ERROR: adjListArray = new LinkedList<>[v];
			ERROR: adjListArray = new LinkedList<Integer>[v];
			
			SOLUTION: remove <Integer> and it gets infered at runtime
			          adjListArray = new LinkedList[v]
		*/

		for(int i = 0 ; i < v ; i++){
			adjListArray[i] = new LinkedList<Integer>();
		}
	}
	
	public void addEdge(int src, int des){
		adjListArray[src].add(des);
	}
	
	
	/* BFS is the "most commonly used" approach.
		BFS is a traversing algorithm where you should start traversing from a selected node (source or starting node),
		 and traverse the graph layerwise thus exploring the neighbour nodes (nodes which are directly connected to source node). 
		 You must then move towards the next-level neighbour nodes.
		 
	  Time Complexity : 
	  The time complexity of BFS is O(V + E), where V is the number of nodes and E is the number of edges.

	  Shortest Path(V1, V2) (Minimum distance): 
	  If all the edges in a graph are of the "same weight",
	   then BFS can also be used to find the minimum distance between the nodes in a graph.
		 
	https://www.hackerearth.com/practice/algorithms/graphs/breadth-first-search/tutorial/
	 * */
	void BFS(int vertex){
		System.out.println("BFS (" + vertex + ")");
		boolean[] visited = new boolean[nOfVertices];
		Queue<Integer> q = new LinkedList<>();
		q.add(vertex);//whenever putting a node in a Queue, mark it as visited
		visited[vertex] = true;
		while(!q.isEmpty()){
			Integer v = q.remove();
			System.out.println(v);
			for(Integer neighbour : adjListArray[v]){
				if(!visited[neighbour]){
					q.add(neighbour);
					visited[neighbour] = true;
				}
			}
		}
	}
	
	
	/*  https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
	 * 
	 * Time Complexity: O(V+E) where V is number of vertices in the graph and E is number of edges in the graph.
	 * */
	void DFS(int vertex){
		System.out.println("DFS (" + vertex + ")");
		DFSRecursive(vertex, new boolean[nOfVertices]);
	}	
	void DFS(){
		System.out.println("DFS()");
		int connectComponentCout = 1;
		boolean[] visited = new boolean[nOfVertices];
		//deal with disconnected graphs
		for(int v = 0; v < nOfVertices; v++ ){
			if(!visited[v]){
				if(connectComponentCout >1 ){
					System.out.println("Graph is disconnected. searching for other connected components : DFS (" + v + ")");
				}
				connectComponentCout++;
				DFSRecursive(v, visited);
			}
		}
	}	
	
	
	void DFSRecursive(int vertex, boolean[] visited){
		//boolean[] isVisited = visited;
		if(!visited[vertex]){
			System.out.println(vertex);
			visited[vertex] = true;
		}
		for(Integer neighbour : adjListArray[vertex]){
			if(!visited[neighbour]){
				 DFSRecursive(neighbour, visited);
			}
		}
	}
	
	void PrintGraph(){
		System.out.println("Adjacency List representation");
		for(int v = 0 ; v < nOfVertices ; v++){
			String vertx = "vertex " + v + " : ";
			for(Integer edge : adjListArray[v]){
				vertx += edge + "-> ";
			}
			System.out.println(vertx);
		}
	}
	
	public static void incrementBy10(int[] primitiveIntegerArray, Integer[] ObjectIntegerArray, String stringArgument ){
		for(int i = 0 ; i < 5; i++){
			primitiveIntegerArray[i] += 10;
			ObjectIntegerArray[i] += 10;
		}
		stringArgument+= " HAS CHANGED!";
	}

	public static void main(String[] args) {
		GraphAdjacencyList graph = new GraphAdjacencyList(5); 
		System.out.println("graph");
		graph.addEdge(0, 1); 
		graph.addEdge(0, 4); 
		graph.addEdge(1, 0); 
		graph.addEdge(1, 4); 
		graph.addEdge(1, 3); 
		graph.addEdge(1, 2); 
		graph.addEdge(2, 1); 
		graph.addEdge(2, 3); 
		graph.addEdge(3, 2); 
		graph.addEdge(3, 1);
		graph.addEdge(3, 4); 
		graph.addEdge(4, 3); 
		graph.addEdge(4, 1); 
		graph.addEdge(4, 0);
		graph.PrintGraph();
		System.out.println();
		graph.BFS(0);
		graph.BFS(1);
		graph.BFS(3);
		graph.BFS(2);
		System.out.println();


		//https://www.hackerearth.com/practice/algorithms/graphs/breadth-first-search/tutorial/
		System.out.println("graphHackerank");
		GraphAdjacencyList graphHackerank = new GraphAdjacencyList(8); 
		graphHackerank.addEdge(0, 1);
		graphHackerank.addEdge(0, 2);
		graphHackerank.addEdge(0, 3);
		graphHackerank.addEdge(1, 0);
		graphHackerank.addEdge(1, 4);
		graphHackerank.addEdge(1, 5);
		graphHackerank.addEdge(2, 0);
		graphHackerank.addEdge(2, 6);
		graphHackerank.addEdge(2, 7);
		graphHackerank.addEdge(3, 0);
		graphHackerank.addEdge(3, 7);
		graphHackerank.addEdge(4, 1);
		graphHackerank.addEdge(5, 1);
		graphHackerank.addEdge(6, 2);
		graphHackerank.addEdge(7, 2);
		graphHackerank.addEdge(7, 3);
		graphHackerank.PrintGraph();
		System.out.println();
		graphHackerank.BFS(0);
		graphHackerank.BFS(1);
		graphHackerank.BFS(3);
		graphHackerank.BFS(5);
		graphHackerank.BFS(7);
		
	
		//	https://www.hackerearth.com/practice/algorithms/graphs/breadth-first-search/tutorial/
		System.out.println();
		System.out.println("graphHackerank");
		graphHackerank.PrintGraph();
		graphHackerank.DFS(0);
		graphHackerank.DFS(1);
		graphHackerank.DFS(3);
		graphHackerank.DFS(5);
		graphHackerank.DFS(7);

		System.out.println("Depth First traversal");
		// https://www.programiz.com/dsa/graph-dfs
		System.out.println("graphProgramiz");
		GraphAdjacencyList graphProgramiz = new GraphAdjacencyList(5);
		graphProgramiz.addEdge(0, 1);
		graphProgramiz.addEdge(0, 2);
		graphProgramiz.addEdge(0, 3);
		graphProgramiz.addEdge(1, 0);
		graphProgramiz.addEdge(1, 2);
		graphProgramiz.addEdge(2, 0);
		graphProgramiz.addEdge(2, 1);
		graphProgramiz.addEdge(2, 4);
		graphProgramiz.addEdge(3, 0);
		graphProgramiz.addEdge(4, 2);
		graphHackerank.PrintGraph();
		System.out.println();
		graphProgramiz.DFS(0);
		
		// https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
		System.out.println();
		System.out.println("graphGeeksforgeeks");
		GraphAdjacencyList graphGeeksforgeeks = new GraphAdjacencyList(4);
		graphGeeksforgeeks.addEdge(0, 1); 
		graphGeeksforgeeks.addEdge(0, 2); 
		graphGeeksforgeeks.addEdge(1, 2); 
		graphGeeksforgeeks.addEdge(2, 0); 
	    graphGeeksforgeeks.addEdge(2, 3); 
	    graphGeeksforgeeks.addEdge(3, 2);
	    graphGeeksforgeeks.PrintGraph();
		System.out.println();
		graphGeeksforgeeks.DFS(2);
		graphGeeksforgeeks.DFS(0);
		graphGeeksforgeeks.DFS();
		System.out.println("if Graph connected : DFS() = DFS(0)    BFS() = BFS(0)");

		//https://www.hackerearth.com/practice/algorithms/graphs/depth-first-search/tutorial/
		System.out.println();
		System.out.println("disconnectedGraph");
		GraphAdjacencyList disconnectedGraph = new GraphAdjacencyList(6);
		disconnectedGraph.addEdge(0, 1);
		disconnectedGraph.addEdge(0, 2);
		disconnectedGraph.addEdge(1, 0);
		disconnectedGraph.addEdge(1, 2);
		disconnectedGraph.addEdge(2, 0);
		disconnectedGraph.addEdge(2, 1);
		disconnectedGraph.addEdge(3, 4);
		disconnectedGraph.addEdge(4, 3);
		disconnectedGraph.PrintGraph();
		disconnectedGraph.BFS(0);
		System.out.println();
		disconnectedGraph.DFS(0);
		System.out.println();
		disconnectedGraph.DFS(2);
		System.out.println();
		disconnectedGraph.DFS(4);
		System.out.println();
		disconnectedGraph.DFS(5);
		System.out.println();
		disconnectedGraph.DFS();
		System.out.println();

		
		

		System.out.println("primitive Arrays are like objects (passed-by-reference),");
		System.out.println(" so, it is safe to pass them as recursive function argument (they keep updated in each recursion step)");
		System.out.println(" as opposed to primitives (int, double,String..) which are passed-by-value and are NOT SAFE as recursive function arguments (not get updated during recursions)");
		System.out.println(" Stirng variables are IMMUTABLE (SO Treat them as primitives)- NOT SAFE as recusrsive function arg (NOT get updated during recursion steps)");
		int[] primitiveIntegerArray = new int[5];
		Integer[] ObjectIntegerArray = new Integer[5];
		String stringArgument = "STRING_VALUE";
		for(int i = 0 ; i < 5; i++){
			ObjectIntegerArray[i] = new Integer(0);
		}
		System.out.println();
		System.out.println("primitiveIntegerArray[0] : " + primitiveIntegerArray[0] + ",   ObjectIntegerArray[0] : " + ObjectIntegerArray[0] + ", stringArgument : " + stringArgument);
		incrementBy10(primitiveIntegerArray, ObjectIntegerArray, stringArgument);
		System.out.println("incrementBy10(primitiveIntegerArray, ObjectIntegerArray, stringArgument)");
		System.out.println("primitiveIntegerArray[0] : " + primitiveIntegerArray[0] + ",   ObjectIntegerArray[0] : " + ObjectIntegerArray[0] + ", stringArgument : " + stringArgument);

		
	}

}
