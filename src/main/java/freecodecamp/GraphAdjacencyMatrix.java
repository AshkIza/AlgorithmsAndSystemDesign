package freecodecamp;

/* https://www.programiz.com/dsa/graph-adjacency-matrix
 * */
public class GraphAdjacencyMatrix {
	int nOfVertices;
    int[][] adjMatrix;
    
    GraphAdjacencyMatrix(int v){
		nOfVertices = v;
		adjMatrix = new int[v][v];
	}
    
	public void addEdge(int src, int des){
		adjMatrix[src][des] = 1;
	}
	
	public void removeEdge(int src, int des){
		adjMatrix[src][des] = 0;
	}
	
	public boolean isEdge(int src, int des){
		return adjMatrix[src][des] == 1;
	}
	
	void PrintGraph(){
		System.out.println("Adjacency Matrix representation");
		for(int v = 0 ; v < nOfVertices ; v++){
			String vertx = "";
			for(int e = 0; e < nOfVertices ; e++){
					vertx += adjMatrix[v][e] + " ";
			
			}
			System.out.println(vertx);
		}
	}

	public static void main(String[] args) {
		GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(5); 
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
	}

}
