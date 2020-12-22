package freecodecamp;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/*  find if an undirected graph is connected
 *  count number of edges in a graph
 *  check if graph has loops (detect cycle)
 *  find if a graph is tree or not (no cycle & connected)
 *  Is there a path between two nodes in this undirected graph, and if yes print them all? 
 *  		(solution needs to get optimized-sapce-wise)
 *  What's the shortest path between two nodes in this undirected, unweighted graph?
 *  Can this undirected graph be colored with two colors? (is this graph bipartite?)
 * */
public class GraphUndirected extends GraphAdjacencyList{

	GraphUndirected(int v) {
		super(v);
	}
	
	boolean ifConnected(){
		boolean[] visited = new boolean[nOfVertices];
		DFSRecursive(0, visited);
		for(int v = 0 ; v < nOfVertices; v++){
			if(!visited[v]){
				return false;
			}
		}
		return true;
	}
	
	int numberOfEdges(){
		int count = 0;
		for(int v = 0; v < nOfVertices; v++ ){
			count += adjListArray[v].size();
		}
		return count/2;
	}
	

	
	/*  Check if a given graph is tree or not
	 * https://www.geeksforgeeks.org/check-given-graph-tree/
	 * 
	 * An undirected graph is tree if it has following properties.
		1) There is no cycle.
		2) The graph is connected.
		
		How to detect cycle in an undirected graph? 
		(every vertex should get visited ONLY ONCE, if  an adjacent vertex is parent, do NOT visit it AGIAN)
			We can either use BFS or DFS. For every visited vertex ‘v’, 
			if there is an adjacent ‘u’ such that u is already visited and u is not parent of v, 
			then there is a cycle in graph
			
		How to check for connectivity?
			Since the graph is undirected, we can start BFS or DFS from any vertex and check ,
			if all vertices are reachable or not. If all vertices are reachable,
			 then graph is connected, otherwise not.
	 * */
	boolean isTree(){
		boolean[] visited = new boolean[nOfVertices];
		//check for cycle
		if(detectCycleDFS(0, 0, visited)){
			return false;
		}
		//check for connectivity
		for(int v = 0; v < nOfVertices; v++){
			if(!visited[v]){
				return false;//disconnected (can NOT be a tree)
			}
		}
		return true;
	}
	boolean detectCycleDFS(int node, int parentNode, boolean[] visited){
		visited[node] = true;
		for(int adjNode : adjListArray[node]){
			if(adjNode != parentNode){
				if(visited[adjNode]){ 
					/*an adjacent node (which is not parent) has already been visited 
					 *   --> there should be a loop (cycle)
					 *     --> NOT a tree
					 * */
					return true;
				}else{
					//check recursively if adjacent nodes are not subtrees
					//keep searching for cycle, as soon as find one, stop (return true)
					if(detectCycleDFS(adjNode, node, visited)){
						return true;
					}
				}
			}
		}
		return false;//leaf
	}
	
	/*  Detect cycle in an undirected graph
	 *    Like directed graphs, we can use DFS to detect cycle in an undirected graph in O(V+E) time.
	 *    We do a DFS traversal of the given graph. For every visited vertex ‘v’, 
	 *    if there is an adjacent ‘u’ such that u is already visited and u is not parent of v, 
	 *    then there is a cycle in graph.
	 *    The assumption of this approach is that there are no parallel edges between any two vertices
	 *    
	 *    https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
	 * */
	boolean isCyclic(){
		boolean[] visited = new boolean[nOfVertices];
		for(int v = 0; v < nOfVertices; v++){
			if(!visited[v]){//in case of disconnected graph
				if(detectCycleDFS(v, v, visited)){
					return true;
				}
			}
		}
		return false;
	}
	
	
	/*  find if there is a path between 2 nodes (source, destination) in an undirected graph (and print them all,if any)
	 * 
	 * path : ordered list of ancestors from source to destination
	 * 
	 * ***NOT OPTIMUM SOLUTION (for space) : extra usages of ancestors list for each path!
	 * */
	boolean findPath(int source, int des){
		List<Integer> ancestors = new LinkedList<>();
		return hasPathRecursive(source, des, ancestors);
	}
	boolean hasPathRecursive(int node, int des, List<Integer> ancestors){
		if(node == des){
			ancestors.add(node);
			System.out.println(ancestors);
			return true;
		}
		boolean isPath = false;
		for(Integer adj : adjListArray[node]){
			if (!ancestors.contains(adj)){
				LinkedList<Integer> adjAncestor = new LinkedList<>(ancestors);//not optimum space-wise
				adjAncestor.add(node);
				if(hasPathRecursive(adj, des, adjAncestor)){
					isPath = true;
				}
			}
		}
		return isPath;
	}
	
	
	public static void main(String[] args) {
		GraphUndirected disconnectedGraph = new GraphUndirected(6);
		disconnectedGraph.addEdge(0, 1);
		disconnectedGraph.addEdge(0, 2);
		disconnectedGraph.addEdge(1, 0);
		disconnectedGraph.addEdge(2, 0);
		disconnectedGraph.addEdge(2, 3);
		disconnectedGraph.addEdge(3, 2);
		disconnectedGraph.addEdge(4, 5);
		disconnectedGraph.addEdge(5, 4);
		disconnectedGraph.PrintGraph();
		System.out.println("disconnectedGraph.ifConnected() : " + disconnectedGraph.ifConnected());
		System.out.println("disconnectedGraph.numberOfEdges() : " + disconnectedGraph.numberOfEdges());
		System.out.println();


		//Check if a given graph is tree or not   https://www.geeksforgeeks.org/check-given-graph-tree/
		GraphUndirected graphIStree = new GraphUndirected(5);
		graphIStree.addEdge(0, 1);
		graphIStree.addEdge(0, 2);
		graphIStree.addEdge(0, 3);
		graphIStree.addEdge(1, 0);
		graphIStree.addEdge(2, 0);
		graphIStree.addEdge(3, 0);
		graphIStree.addEdge(3, 4);
		graphIStree.addEdge(4, 3);
		graphIStree.PrintGraph();
		System.out.println("graphIStree.isTree() : " + graphIStree.isTree());
		System.out.println();

		
		GraphUndirected graphHasCycle = new GraphUndirected(5);
		graphHasCycle.addEdge(0, 1);
		graphHasCycle.addEdge(0, 2);
		graphHasCycle.addEdge(0, 3);
		graphHasCycle.addEdge(1, 0);
		graphHasCycle.addEdge(1, 2);
		graphHasCycle.addEdge(2, 0);
		graphHasCycle.addEdge(2, 1);
		graphHasCycle.addEdge(3, 0);
		graphHasCycle.addEdge(3, 4);
		graphHasCycle.addEdge(4, 3);
		graphHasCycle.PrintGraph();
		System.out.println("graphHasCycle.isTree() : " + graphHasCycle.isTree());
		System.out.println();
		
		GraphUndirected graphNOCycleButDisconnected = new GraphUndirected(6);
		graphNOCycleButDisconnected.addEdge(0, 1);
		graphNOCycleButDisconnected.addEdge(0, 2);
		graphNOCycleButDisconnected.addEdge(0, 3);
		graphNOCycleButDisconnected.addEdge(1, 0);
		graphNOCycleButDisconnected.addEdge(2, 0);
		graphNOCycleButDisconnected.addEdge(3, 0);
		graphNOCycleButDisconnected.addEdge(3, 4);
		graphNOCycleButDisconnected.addEdge(4, 3);
		graphNOCycleButDisconnected.PrintGraph();
		System.out.println("graphNOCycleButDisconnected.isTree() : " + graphNOCycleButDisconnected.isTree());
		System.out.println();
		
		GraphUndirected graphCyclicAndDisconnected = new GraphUndirected(9);
		graphCyclicAndDisconnected.addEdge(0, 1);
		graphCyclicAndDisconnected.addEdge(1, 0);
		graphCyclicAndDisconnected.addEdge(1, 2);
		graphCyclicAndDisconnected.addEdge(2, 1);
		graphCyclicAndDisconnected.addEdge(2, 3);
		graphCyclicAndDisconnected.addEdge(3, 2);
		graphCyclicAndDisconnected.addEdge(3, 4);
		graphCyclicAndDisconnected.addEdge(4, 3);
		//second part of this disconnected graph has cycle
		graphCyclicAndDisconnected.addEdge(5, 6);
		graphCyclicAndDisconnected.addEdge(6, 5);
		graphCyclicAndDisconnected.addEdge(6, 7);
		graphCyclicAndDisconnected.addEdge(7, 8);
		graphCyclicAndDisconnected.addEdge(8, 7);
		graphCyclicAndDisconnected.addEdge(7, 5);
		graphCyclicAndDisconnected.PrintGraph();
		System.out.println("graphCyclicAndDisconnected.isCyclic() : " + graphCyclicAndDisconnected.isCyclic());
		System.out.println();


		LinkedList firstList = new LinkedList<>();
		firstList.add(1);
		firstList.add(2);
		firstList.add(3);
		System.out.println("firstList : " + firstList);
		System.out.println("LinkedList secondList = new LinkedList<>(firstList)");
		System.out.println("secondList.add(4)");
		LinkedList secondList = new LinkedList<>(firstList);
		secondList.add(4);
		System.out.println("firstList : " + firstList);
		System.out.println("secondList : " + secondList);
		System.out.println();

		System.out.println("find if path between nodes in an undirected graph (and print them)");
		GraphUndirected graphWithCycles = new GraphUndirected(12);
		graphWithCycles.addEdge(0, 1);
		graphWithCycles.addEdge(1, 0);
		graphWithCycles.addEdge(1, 2);
		graphWithCycles.addEdge(2, 1);
		graphWithCycles.addEdge(2, 3);
		graphWithCycles.addEdge(2, 4);
		graphWithCycles.addEdge(2, 5);
		graphWithCycles.addEdge(3, 2);
		graphWithCycles.addEdge(3, 4);
		graphWithCycles.addEdge(3, 10);
		graphWithCycles.addEdge(4, 2);
		graphWithCycles.addEdge(4, 3);
		graphWithCycles.addEdge(4, 5);
		graphWithCycles.addEdge(4, 6);
		graphWithCycles.addEdge(5, 2);
		graphWithCycles.addEdge(5, 4);
		graphWithCycles.addEdge(5, 7);
		graphWithCycles.addEdge(6, 4);
		graphWithCycles.addEdge(6, 8);
		graphWithCycles.addEdge(7, 5);
		graphWithCycles.addEdge(7, 8);
		graphWithCycles.addEdge(8, 6);
		graphWithCycles.addEdge(8, 7);
		graphWithCycles.addEdge(8, 9);
		graphWithCycles.addEdge(9, 8);
		graphWithCycles.addEdge(10, 3);
		graphWithCycles.PrintGraph();
		System.out.println("graphWithCycles.findPath(0,6) : " + graphWithCycles.findPath(0, 6));
		System.out.println();
		System.out.println("graphWithCycles.findPath(0,2) : " + graphWithCycles.findPath(0, 2));
		System.out.println();
		System.out.println("graphWithCycles.findPath(0,11) : " + graphWithCycles.findPath(0, 11));
		System.out.println();
		System.out.println("graphWithCycles.findPath(4,9) : " + graphWithCycles.findPath(4, 9));
		System.out.println();

















		




		









	}

}
