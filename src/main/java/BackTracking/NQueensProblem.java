package BackTracking;

import java.util.LinkedList;
import java.util.List;

/*  Printing all solutions in N-Queen Problem
    https://www.geeksforgeeks.org/printing-solutions-n-queen-problem/
    
    N Queen Problem | Backtracking-3
    https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/
 * 
 * 
 * */
public class NQueensProblem {

	public static void solveNQ(int N){
		System.out.println(" solving NQeens problem for N = " + N);

		boolean solved = false;
		for(int column = 0 ; column < N; column++){
			LinkedList<Integer> queens = new LinkedList<>();
			queens.add(column);
			solved = solveNQRecursive(queens, N) || solved;
			/* solved = solved || solveNQRecursive(queens, N)  return only first solution 
			 *   since (true || func()) never calls func()
			 */
		}
		if(!solved){
			System.out.println(" NO NQeens solution for N = " + N);
		}
	}
	
	public static boolean solveNQRecursive(LinkedList<Integer> columns, int N){
		if(columns.size() == N){//already solved
			print(columns);
			return true;
		}
		//applying bounding function (find a spot for next queen not to be under attack)
		int  lastQueen = columns.peekLast();//column index of last queen inserted in the board
		int min = (lastQueen == 0) ? 0 : lastQueen - 1;
		int max = (lastQueen == N-1) ? N-1 : lastQueen + 1;
		for(int i = 0 ; i < N ; i++){
			if( !columns.contains(i) && 
					i != min && i != max){
				columns.add(i);
				return solveNQRecursive(columns, N);
			}
		}
		// we should have been able to insert next queen by now, if not not solution exist, kill this state
		return false;
	}
	
	public static void print(LinkedList<Integer> columns){
		String solution = "\n";
		for(int queenIndex : columns){
			String row = "{ ";
			for(int column = 0 ; column < columns.size(); column++){
				row += (column == queenIndex) ? "1 " : "0 ";
			} 
			row += "}\n";
			solution += row;
		}
		System.out.println(solution);
	}
	
	
	private static boolean function(){
		System.out.println("calling function");
		return true;
	}
	

	
	
	public static void main(String[] args) {
		System.out.println("in a || b , a is always checked, if a== true, b is not even called!");

		System.out.println("\n true || function() never calling the function()");
		if( true || function()){
		}
		
		System.out.println("\n function() || true always calls the function()");
		if( function() || true){
		}
		System.out.println("\nin a || b , a is always checked, if a== true, b is not even called ! \n\n");


		
		solveNQ(2);
		solveNQ(3);
		solveNQ(4);
		solveNQ(5);
		solveNQ(6);
	}

}
