package Udemy;

import java.util.Arrays;

/*
*   printSteps(4):
*   
*   '*      '
*   '* *    '
*   '* * *  '
*   '* * * *'
 * */
public class printingSteps {
	/* NOTES on recursive solutions:
	 *  1- think of base case first 
	 *  	(base case: where there is no more recursive call and we return/stop)
	 *  2- call function again (recursive call). 
	 *  	make sure to change arguments in some fashion (unless infinite loop)
	 *  3- try to put together the iterative solution first (easier to get your mind to the right place),
	 *     then, try to re-factor the solution over to recursive
	 *     (usually recursive function has more arguments, and you need to define default values for them)
	 * */
	
	
	//**** HAS ERROR****WHY???
	//think of recursive first (a 2-dimensional array and we want to fill in the lower half by '#')
	//recursive call prints either # or ' ' (in same line or next line)!
	// here we have two parameters in our recursive call  str & n (hard to manage, potential for mistakes)
	//default values str= ""  n = 1
	//time complexity O(N^2)   extra space complexity O(1)
	public static void printStepRecursive(int n, int number, String str){
		if (n > number) { //our base case 
			return;
		}
		
		//end of row, should start new row
		if(str.length() == number) {
			System.out.println(str);
			str = "";
			printStepRecursive (n + 1, number, str);//this recursive call updates n
		}
		
		//we are still in the existing row
		if(str.length() < n) {
			str = str + '#';
		}else {
			str = str + ' ';
		}
		 printStepRecursive(n, number, str);//this recursive call updates str
	}
	
	
	// recursive call does not need to decrement n, can also increment n (like in this case!)
	// this solution is not perfect 
	//1- make use of java helper function Arrays.fill
	//2- not purely recursive (Arrays.fill is a iterative)
	//3- use an array       (extra space complexity O(N))
	public static void printStepRecursive02(int n, int number, char[] array){
		if(n == 1) {//first step
			Arrays.fill(array, ' ');
		}
		array[n-1] = '#';//# -> ' '
		System.out.println(String.copyValueOf(array));
		
		if(n == number) {//last step
			return;
		}else {
			printStepRecursive02(n + 1, number, array);//next step
		}
	}
	
	//time complexity O(N)  extra space complexity O(N)
	public static void printSteps(int number) {
		char[] dest = new char[number];
		Arrays.fill(dest, ' ');

		System.out.println("printSteps(" + number + ")");
	    for(int index = 1; index <= number; index++ ){
	    	dest[index - 1] = '#';
	    	//in every step, we add another '#' 
	    	//to the array from the previous step
			System.out.println(String.copyValueOf(dest));
		}
	    System.out.println();
	}

	public static void main(String[] args) {
		/*int[] tests = new int[] {4, 20};
		for(int test : tests) {
			printSteps(test);
		}*/
		
		//printStepRecursive02(1, 5, new char[5]);
		printStepRecursive(1,2,"");
		
	}
	
	/*Initializing Arrays in Java
	 * https://www.baeldung.com/java-initialize-array
	 * Arrays.fill()
	 *  long array[] = new long[5];
		Arrays.fill(array, 30);
		Arrays.fill(array, 0, 3, -50);
        fill(char[] a, int fromIndex, int toIndex, char val)
           fromIndex(inclusive)     toIndex(exclusive)
	 */
	
	/* Convert char Array to String
	 * char[] chars = {'T', 'e', 'c', 'h', 'i', 'e', ' ', 
						'D', 'e', 'l', 'i', 'g', 'h', 't'};
		String string = new String(chars);
		String string = String.copyValueOf(chars);
	 * */
}
