package hackerank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* ++++++
 * https://www.hackerrank.com/challenges/a-chessboard-game-1/problem
 * 
 * */
public class ChessboardGame {

	
	 static class Pair{
	        int x;
	        int y;
	        Pair(int x ,  int y){
	            this.x = x;
	            this.y = y;
	        }
	        boolean canMove(int difx, int dify){
	            return x + difx >= 1 && x + difx<=15 &&
	                y + dify >=1 && y + dify<=15;
	        }
	        Pair nextmove(int difx, int dify){
	            return new Pair(x +difx, y + dify);
	        }
	    }


	    // Complete the chessboardGame function below.
	    static String chessboardGame(int x, int y) {
	        List<Pair> posibleoutcomes = new ArrayList<>();
	        posibleoutcomes.add(new Pair(x, y));
	        boolean p1playsnext = true;
	        while(!posibleoutcomes.isEmpty()){// moves from previous player
	            posibleoutcomes = nextpossiblemoves(posibleoutcomes);
	            p1playsnext = p1playsnext ? false : true;// next person to play
	        }
	        return p1playsnext ? "First" : "Second";
	    }
	    
	    static List<Pair> nextpossiblemoves(List<Pair> moves){
	        List<Pair> nextmoves = new ArrayList<>();
	        for(Pair eachoutcome : moves){
	            if (eachoutcome.canMove(-2, 1)){
	               nextmoves.add(eachoutcome.nextmove(-2, 1)); 
	            } 
	            if (eachoutcome.canMove(-2, -1)){
	               nextmoves.add(eachoutcome.nextmove(-2, -1)); 
	            } 
	            if (eachoutcome.canMove(-1, -2)){
	               nextmoves.add(eachoutcome.nextmove(-1, -2)); 
	            } 
	            if (eachoutcome.canMove(1, -2)){
	               nextmoves.add(eachoutcome.nextmove(1, -2)); 
	            } 
	        }
	        return nextmoves;
	    }

	static Consumer<int[]> runalgorithm = c -> System.out.println(chessboardGame(c[0], c[1]));
	public static void main(String[] args) {
		int[][] testCase0 = new int[][] {{5, 2},
										 {5, 3}, 
										 {8, 8}};
		System.out.println("testCase0 : ");
		Arrays.stream(testCase0).forEach(runalgorithm);
		
		int[][] testCase1 = new int[][] {{7, 3},
										{8, 12}, 
										{9, 7}};
		System.out.println("testCase1 : ");
		Arrays.stream(testCase1).forEach(runalgorithm);

	}

}
