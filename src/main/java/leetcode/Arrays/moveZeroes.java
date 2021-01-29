package leetcode.Arrays;

import java.util.Arrays;

/*Move Zeroes
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 * 
 * */
public class moveZeroes {

   /*  using the hints in the questions
    *   be in place -> (no extra copy) by just looping through it
    *   Minimize the total number of operations -> do swap instead of total array shift in each loop iteration
    * */	
   public static void moveZeroes(int[] nums) {
	   if(nums.length <= 1) return;
       int zeroCount = 0;
       for(int index = 0; index < nums.length; index++){
           if(nums[index] == 0){
               zeroCount++;
           }else{
               swap(nums, index, zeroCount);
           }
       }
   }
	    
   public static void swap(int[] nums, int index, int zeroCount){
       if(zeroCount > 0){
           int start = index - zeroCount;
           int temp = nums[start];
           nums[start] = nums[index];
           nums[index] = temp;
       }
   }
	   
	  

	public static void main(String[] args) {
		int[] nums01 = new int[] {0,1,0,3,12};
		moveZeroes(nums01);
		System.out.println(Arrays.toString(nums01));
	}
	
	/*   the simplest way to print a Java array?
	 * https://stackoverflow.com/questions/409784/whats-the-simplest-way-to-print-a-java-array
	 * Arrays.toString([])
	 * 
	 * double Array:
			double[] doubleArray = { 7.0, 9.0, 5.0, 1.0, 3.0 };
			System.out.println(Arrays.toString(doubleArray));
			
			Nested Array:
				String[][] deepArray = new String[][] {{"John", "Mary"}, {"Alice", "Bob"}};
				System.out.println(Arrays.toString(deepArray));
					//output: [[Ljava.lang.String;@106d69c, [Ljava.lang.String;@52e922]
				System.out.println(Arrays.deepToString(deepArray));
	 * 
	 * */

}
