package Udemy;

import java.util.Arrays;
//Write a function that accepts a positive number N.
//The function should console log a pyramid shape
//with N levels using the # character.  Make sure the
//pyramid has spaces on both the left *and* right hand sides
//--- Examples
//pyramid(1)
//    '#'
//pyramid(2)
//    ' # '
//    '###'
//pyramid(3)
//    '  #  '
//    ' ### '
//    '#####'

public class Pyramids {

	// O(n^2) time complexity 
	// extra space complexity O(n)   row
	public static void pyramidaIterative(int number) {
		if (number < 1) return;
		int mid = number -1;
		int m = 2 * number -1;
		for(int i = 0 ; i < number; i++) {
			String row = "";
			for(int j = 0; j< m; j++ ) {
				if( j < mid-i || j > mid + i) {
					row += ' ';
				}else {
					row += '*';
				}
			}
			System.out.print(row + '\n');
		}
	}
	
	//Recursive    time complexity O(n) 
	//extra space complexity O(n) ???  mem[] + call stack
	public static void pyramidRecursive(int number) {
		char[] mem = new char[2 * number -1];
		Arrays.fill(mem, ' ');
		recursiveCall(mem, number-1, 0);
	}
	public static void recursiveCall(char[] mem, int mid, int row) {
		if(row > mid) return;
		mem[mid + row]='*';
		mem[mid - row]='*';
		System.out.println(mem);
		recursiveCall(mem, mid, row +1);
	}
	
	
	// used Tabulation (bottom up approach) - DP
	// time complexity O (N) - better than writing 2 nested loops (O (N^2)
	// but , extra space  O(N)
	public static void pyramidDP(int number) {
		int pSize = 2 * number - 1;//pyramid width
		int midIndex = number -1;
		char[] pyramid = new char[pSize];
		Arrays.fill(pyramid, ' ');//make sure always initialize your array properly!

	    for(int row = 1; row <= number; row++) {//next rows
	    	int addIndex = row - 1;
			pyramid[midIndex  - addIndex] = '#';
			pyramid[midIndex  + addIndex] = '#';
		    System.out.println(pyramid);//print char Array :  println(char x[])
	    }
	}
	
	
	
	public static void main(String[] args) {
		//pyramidDP(10);
		//pyramidaIterative(20);
		pyramidRecursive(8);
	}

}
