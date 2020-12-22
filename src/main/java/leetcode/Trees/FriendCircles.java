package leetcode.Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*  Friend Circles
		Solution
		There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
		
		Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
		
		Example 1:
		Input: 
		[[1,1,0],
		 [1,1,0],
		 [0,0,1]]
		Output: 2
		Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
		The 2nd student himself is in a friend circle. So return 2.
		Example 2:
		Input: 
		[[1,1,0],
		 [1,1,1],
		 [0,1,1]]
		Output: 1
		Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
		so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
		Note:
		N is in range [1,200].
		M[i][i] = 1 for all students.
		If M[i][j] = 1, then M[j][i] = 1.
 * 
 * */
public class FriendCircles {
	
	public static int findCircleNum(int[][] M) {
		LinkedList<Integer> vertices = new LinkedList<>();
		if(M.length <= 1){
			return M.length;
		}
		for(int i = 0 ; i < M.length; i++){
			vertices.add(i);
		}
		int numOfIslands = 0;
		while(vertices.size() != 0){
			vertices = traverse(vertices, M);
			numOfIslands++;
		}
		return numOfIslands;
	}
	
	public static LinkedList<Integer> traverse(LinkedList<Integer> vertices, int[][] M) {
		boolean[] visited = traverseRecursive(vertices.getFirst(), new boolean[M.length], M);
		for(int a = 0 ; a < M.length; a++){
			if(visited[a] && vertices.contains(a)){
				int index = vertices.indexOf(a); 
				vertices.remove(index);
			}
		}
		return vertices;
	}
	
	public static boolean[] traverseRecursive(int vertex, boolean[] visited, int[][] M) {
		if(!visited[vertex]){
			visited[vertex]= true;
		}
		for(int i = 0 ; i < M.length; i++){
			if( i != vertex && M[vertex][i] == 1 && !visited[i]){
				visited = traverseRecursive(i, visited, M);
			}
		}
		return visited;
	}

	public static void main(String[] args) {
		int[][] M01 = new int[3][3];
		M01[0][0] = 1;
		M01[0][1] = 1;
		M01[0][2] = 0;
		
		M01[1][0] = 1;
		M01[1][1] = 1;
		M01[1][2] = 0;
		
		M01[2][0] = 0;
		M01[2][1] = 0;
		M01[2][2] = 1;
		System.out.println("findCircleNum(M01) : " + findCircleNum(M01));
		
		int[][] M02 = new int[3][3];
		M02[0][0] = 1;
		M02[0][1] = 1;
		M02[0][2] = 0;
		
		M02[1][0] = 1;
		M02[1][1] = 1;
		M02[1][2] = 1;
		
		M02[2][0] = 0;
		M02[2][1] = 1;
		M02[2][2] = 1;
		System.out.println("findCircleNum(M02) : " + findCircleNum(M02));
		
		int[][] M03 = new int[6][6];
		M03[0][0] = 1;
		M03[0][1] = 1;
		M03[0][2] = 1;
		M03[0][3] = 0;
		M03[0][4] = 0;
		M03[0][5] = 0;
		
		M03[1][0] = 1;
		M03[1][1] = 1;
		M03[1][2] = 1;
		M03[1][3] = 0;
		M03[1][4] = 0;
		M03[1][5] = 0;
		
		M03[2][0] = 1;
		M03[2][1] = 1;
		M03[2][2] = 1;
		M03[2][3] = 0;
		M03[2][4] = 0;
		M03[2][5] = 0;
		
		M03[3][0] = 0;
		M03[3][1] = 0;
		M03[3][2] = 0;
		M03[3][3] = 1;
		M03[3][4] = 0;
		M03[3][5] = 1;
		
		M03[4][0] = 0;
		M03[4][1] = 0;
		M03[4][2] = 0;
		M03[4][3] = 0;
		M03[4][4] = 1;
		M03[4][5] = 0;
		
		M03[5][0] = 0;
		M03[5][1] = 0;
		M03[5][2] = 0;
		M03[5][3] = 1;
		M03[5][4] = 0;
		M03[5][5] = 1;
		System.out.println("findCircleNum(M03) : " + findCircleNum(M03));
		
	}

}
