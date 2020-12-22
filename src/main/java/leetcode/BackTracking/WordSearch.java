package leetcode.BackTracking;

import java.util.ArrayList;
import java.util.List;
/*  Given a 2D board and a word, find if the word exists in the grid.

	The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
	
	Example:
	
	board =
	[
	  ['A','B','C','E'],
	  ['S','F','C','S'],
	  ['A','D','E','E']
	]
	
	Given word = "ABCCED", return true.
	Given word = "SEE", return true.
	Given word = "ABCB", return false.
 * 
 * 
 * solution : https://www.programcreek.com/2014/06/leetcode-word-search-java/
 * */
public class WordSearch {
	
	/* optimizing the memory used for maintaining state-space
	 * if solution not found (after applying bounding function), revert the states back to what it was before (backTrack)/go up
	 * this avoids creating state-space memory for each state branch. (saving memory)
	 * */
    public boolean existOptimized(char[][] board, String word) {
        if(board.length == 0 || word.length() == 0){
            return false;
        }
        char ch = word.charAt(0);
        for(int i = 0 ; i < board.length; i++){
            for(int j = 0 ; j < board[0].length; j++){
                if(board[i][j] == ch){
                    board[i][j] = '#';
                    if(backTrack(board, word.substring(1), i, j)){
                        board[i][j] = ch;
                        return true;
                    }else{
                        board[i][j] = ch;
                    }
                }
            }
            
        }
        return false;
    }
	
    private boolean backTrack(char[][] board, String remaining, int i , int j) {
        if(remaining.length() == 0){
            return true;
        }
        char ch = remaining.charAt(0);
        List<int[]> neighbours = findNeighbours(board, i, j, ch);
        if(neighbours.size() == 0){
            return false;
        }
        for(int[] neighbour : neighbours){
            int a = neighbour[0];
            int b = neighbour[1];
            board[a][b] = '#';// going down the branch (updating the states and go down recursively)
            if(backTrack(board, remaining.substring(1), a, b)){
            	board[a][b] = ch;//not modifying the input (thsi step not neccessary)
                return true;
            }else{
                board[a][b] = ch;//back track (revert the sates to its previous step)
            }
        }
        return false;
    }
    
    private List<int[]> findNeighbours(char[][] board, int i , int j, char ch){
        List<int[]> all = new ArrayList<>();
        int M = board.length;
        int N = board[0].length;
        if(i+1<M && board[i+1][j]==ch){
            all.add(new int[] {i+1,j});
        }
        if(i-1>=0 && board[i-1][j]==ch){
            all.add(new int[] {i-1,j});
        }
        if(j+1<N && board[i][j+1]==ch){
            all.add(new int[] {i,j+1});
        }
        if(j-1>=0 && board[i][j-1]==ch){
            all.add(new int[] {i,j-1});
        }
        return all;
    }
    
    
    /* NOT OPTIMIZED (creating memory for each state-space branch)
     * */
	public static class Pair{
		int x;
		int y;
		Pair(int X, int Y){
			x = X;
			y = Y;
		}
		
		@Override
		public boolean equals(Object obj){
			if(this == obj){
				return true;
			}
			if(obj == null || obj.getClass() != this.getClass()){
				return false;
			}
			
			Pair that = (Pair) obj;
			if(this.x == that.x && this.y == that.y){
				return true;
			}
			return false;
		}
	}
	
	public boolean exist(char[][] board, String word) {
        if(word.length() == 0){
            return true;
        }
        if(board.length == 0){
            return false;
        }
        List<Pair> cells = new ArrayList();
        for(int r = 0 ; r < board.length; r++){
            for(int c = 0 ; c < board[0].length; c++){
                if(board[r][c] == word.charAt(0)){
                	Pair cell = new Pair(r,c);
                    cells.add(cell);
                }
            }
        }
        
        if(word.length() == 1){
            return cells.size() != 0;
        }
        if(cells.size() == 0){
            return false;
        }
        for(Pair cell : cells){
            List<Pair> path = new ArrayList<>();
            path.add(cell);
            if(exist(board, word, 1, path)){
                return true;
            }
        }
        return false;
    }
    
    public boolean exist(char[][] board, String word, int index, List<Pair> cells) {
        if( index == word.length() ){
            return true;
        }
        List<Pair> nextCells = findNext(board, word.charAt(index), cells);
        if(nextCells.size() > 0){
            for(Pair nextCell : nextCells){
                List<Pair> newBranch = new ArrayList(cells);
                newBranch.add(nextCell);
                if(exist(board, word, index + 1, newBranch)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public List<Pair> findNext(char[][] board, char ch, List<Pair> cells) {
        List<Pair> neighbours = neighbour(board, cells.get(cells.size()-1));
        List<Pair> feasible = new ArrayList();
        for(Pair neighbour : neighbours){
            if(board[neighbour.x][neighbour.y] == ch &&
              !cells.contains(neighbour))
                feasible.add(neighbour);
        }
        return feasible;
    }
    
    public List<Pair> neighbour(char[][] board, Pair cell) {
        int x = cell.x;
        int y = cell.y;
        int m =board.length;
        int n =board[0].length;
        List<Pair> options = new ArrayList();
        if(x-1 >=0){
            options.add(new Pair(x-1, y));
        }
        if(y-1 >=0){
            options.add(new Pair(x, y-1));
        }
        if(x+1 < m){
            options.add(new Pair(x+1, y));
        }
        if(y+1 < n){
            options.add(new Pair(x, y+1));
        }
        return options;
    }

	public static void main(String[] args) {
		WordSearch ws = new WordSearch();
		char[][] board = new char[1][2];
		board[0][0] = 'a';
		board[0][1] = 'a';
		
		
		char[][] board02 = new char[3][4];
		board02[0][0] = 'A';
		board02[0][1] = 'B';
		board02[0][2] = 'C';
		board02[0][3] = 'E';
		
		board02[1][0] = 'S';
		board02[1][1] = 'F';
		board02[1][2] = 'C';
		board02[1][3] = 'S';
		
		board02[2][0] = 'A';
		board02[2][1] = 'D';
		board02[2][2] = 'E';
		board02[2][3] = 'E';
	

		System.out.println("ws.exist(board, 'aaa') : " + ws.exist(board, "aaa"));
		
		System.out.println("ws.existOptimized(board02, 'ABCCED') : " + ws.existOptimized(board02, "ABCCED"));
		System.out.println("ws.existOptimized(board02, 'SEE') : " + ws.existOptimized(board02, "SEE"));
		System.out.println("ws.existOptimized(board02, 'ABCB') : " + ws.existOptimized(board02, "ABCB"));


	}

}
