package BackTracking;

import java.util.Arrays;
import java.util.List;


/*  https://www.geeksforgeeks.org/m-coloring-problem-backtracking-5/
 * 
 * 
 *   Tree Traversal Vs Backtracking (state-space tree):
 *     Tree traversal goes only downward VS 
 *     Backtracking goes downward and (if bounding function return false) goes upward 
 *     --> the passing Object to recursive function gets mixed up once call stack goes up again
 *       --> for backtracking: need to create new recursive function object argument once going down
 *              ( generating new state-space tree branch )
 * */
public class MColoringProblem {
	
	public static void graphColoring(int[][] graph, int M){
		System.out.println("All coloring options for M = " + M);
		if(!colorGraph(graph, M, 0, new int[graph.length])){
    		System.out.println(" NO solution found for M = " + M);
		}
	}
	
	private static boolean colorGraph(int[][] graph, int M, int vertex, int[] colors){
		boolean colorable = false;
		for(int m = 1 ; m <= M; m++){
			if(canColor(graph, m, vertex, colors)){ // applying bounding function
				// after applying bounding function, we go furthur down by generating next layer of state-space tree branch 
				int[] newColoring = Arrays.copyOf(colors, colors.length);
				newColoring[vertex] = m;
				if(vertex == colors.length -1){//found 1 solution
					colorable = true;
					print(newColoring);
				}else{
					// passing new passing argument to the recursive function
                    //( not to get mixed with upper level of  state-space tree)
					colorable = colorGraph(graph, M, vertex+1, newColoring) || colorable;
				}
			}
		}
		return colorable;
	}
	
	// bounding function
	private static boolean canColor(int[][] graph, int colorCode, int vertex, int[] colors){
		for(int j = 0 ; j < vertex; j++){
			if(graph[vertex][j] == 1 && colors[j] == colorCode){
				return false;
			}
		}
		return true;
	}
	
    private static void print(int[] colors) {
    	String coloring = " [";
    	for(int i = 0 ; i < colors.length; i++){
    		if(i == colors.length - 1){
    			coloring += colors[i] + "] ";

    		}else{
    			coloring += colors[i] + ", ";
    		}
    	}
  	     System.out.println(coloring);  
    }

	public static void main(String[] args) {
	/* Create following graph and test whether it is 
        3 colorable 
       (3)---(2) 
        |   / | 
        |  /  | 
        | /   | 
       (0)---(1) 
     */
     int graph[][] = {{0, 1, 1, 1}, 
         {1, 0, 1, 0}, 
         {1, 1, 0, 1}, 
         {1, 0, 1, 0}, 
     }; 
     graphColoring(graph, 2);

     graphColoring(graph, 3);
     
     System.out.println("\n\ndisconected_Graph");
     int disconected_Graph[][] = 
    	 	{{0, 0, 0, 0, 1, 0}, 
             {0, 0, 0, 1, 0, 0}, 
             {0, 0, 0, 0, 0, 1}, 
             {0, 1, 0, 0, 1, 0},
             {1, 0, 0, 1, 0, 0}, 
             {0, 0, 1, 0, 0, 0}, 

         }; 
     graphColoring(disconected_Graph, 2);

	}

}
