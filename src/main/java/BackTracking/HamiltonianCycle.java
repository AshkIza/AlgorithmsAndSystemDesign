package BackTracking;

/* https://www.geeksforgeeks.org/hamiltonian-cycle-backtracking-6/
 * */
import java.util.ArrayList;
import java.util.List;

public class HamiltonianCycle {
	
	public static void allHamiltonianCycles(int[][] graph){
		List<Integer> path = new ArrayList<>();
		path.add(0);//start at vertex 0
		int allPaths = hamiltonian(graph, 0, path, 0);
		if(allPaths == 0 ){
    		System.out.println(" There is NO hamiltonian path");
		}else{
    		System.out.println(" There are " + allPaths + " hamiltonian paths as listed");
		}
	}
	
	private static int hamiltonian(int[][] graph, int vertex, List<Integer> path, int pathsFound){
		for(int j = 0 ; j < graph.length; j++){
			if( graph[vertex][j] == 1){
				if( j == 0 && path.size() == graph.length){// new hamiltonian cycle found
					pathsFound++;
					print(path);
				}else if(!path.contains(j)){//each vertex should get visited only once (except vertex 0)
					List<Integer> newPathState = new ArrayList<>(path);
					newPathState.add(j);
					pathsFound = hamiltonian(graph, j, newPathState, pathsFound);
				}
			}
		}
		return pathsFound;
	}
	
	private static void print(List<Integer> path){
		int V = path.size();
		String hamiltonianCycle = " [";
    	for(int i = 0 ; i < V; i++){
    		if(i == V - 1){
    			hamiltonianCycle += path.get(i) + "] ";

    		}else{
    			hamiltonianCycle += path.get(i)  + ", ";
    		}
    	}
  	     System.out.println(hamiltonianCycle);  
	}

	
	// no need to print (scan for ) all feasible solutions (hamiltonian cycles), only return first solution if any
	public static void hasAHamiltonianCycle(int[][] graph){
		List<Integer> path = hamiltonian(graph, 0, new ArrayList<Integer>());
		if(path == null){
    		System.out.println(" There is NO hamiltonian path");
		}else{
			print(path);
		}
	}
	
	private static List<Integer> hamiltonian(int[][] graph, int vertex, List<Integer> path){
		path.add(vertex);
		for(int j= 0 ; j < graph.length; j++){
			if(graph[vertex][j] == 1){
				if( path.size() == graph.length){
					if(j == 0){
						return path;
					}
				}
				if(!path.contains(j)){
					List<Integer> returnVal = hamiltonian(graph, j, path);
					if(returnVal!= null){
						return returnVal;
					}
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		
	System.out.println("\nallHamiltonianCycles(graph1)");
		/* Let us create the following graph  
        (0)--(1)--(2)  
        | / \ |  
        | / \ |  
        | / \ |  
        (3)-------(4) */
    int[][] graph1 = {{0, 1, 0, 1, 0},  
                        {1, 0, 1, 1, 1},  
                        {0, 1, 0, 0, 1},  
                        {1, 1, 0, 0, 1},  
                        {0, 1, 1, 1, 0}};  
    //allHamiltonianCycles(graph1);  
      
    
	System.out.println("\nallHamiltonianCycles(graph2)");
   
	/*
	/* Let us create the following graph  
    (0)--(1)--(2)  
    | / \ |  
    | / \ |  
    | / \ |  
    (3) (4) */
    int[][] graph2 = {{0, 1, 0, 1, 0},  
                         {1, 0, 1, 1, 1},  
                         {0, 1, 0, 0, 1},  
                         {1, 1, 0, 0, 0},  
                         {0, 1, 1, 0, 0}};  
    //allHamiltonianCycles(graph2);  
    
	System.out.println("\nallHamiltonianCycles(graph3)");
    int[][] graph3 = {{0, 1, 1, 0, 0},  
            {1, 0, 1, 1, 1},  
            {1, 1, 0, 0, 1},  
            {0, 1, 0, 0, 1},  
            {0, 1, 1, 1, 0}};  
     //allHamiltonianCycles(graph3);  
     
     
 	System.out.println("\nhasAHamiltonianCycle(graph1)");
    //hasAHamiltonianCycle(graph1);  

	System.out.println("\nhasAHamiltonianCycle(graph2)");
    hasAHamiltonianCycle(graph2);  

	System.out.println("\nhasAHamiltonianCycle(graph3)");
    //hasAHamiltonianCycle(graph3);
	}

}
