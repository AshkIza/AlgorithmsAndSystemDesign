package leetcode.Arrays;

import java.util.Arrays;

/*  Given an array nums and a value val, remove all instances of that value in-place and return the new length.	
	Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
	The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example 1:

	Given nums = [3,2,2,3], val = 3,
	Your function should return length = 2, with the first two elements of nums being 2.	
	It doesn't matter what you leave beyond the returned length.
Example 2:	
	Given nums = [0,1,2,2,3,0,4,2], val = 2,	
	Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.	
	Note that the order of those five elements can be arbitrary.	
	It doesn't matter what values are set beyond the returned length.
	Clarification:
	
	Confused why the returned value is an integer but your answer is an array?	
	Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
	Internally you can think of this:
	
	// nums is passed in by reference. (i.e., without making a copy)
	int len = removeElement(nums, val);
	
	// any modification to nums in your function would be known by the caller.
	// using the length returned by your function, it prints the first len elements.
	for (int i = 0; i < len; i++) {
	    print(nums[i]);
}
 * 
 * */
public class RemoveElement {
	
	   public int removeElement(int[] nums, int val) {
	        int index = 0 ;
	        int N = nums.length - 1;
	        while (index <= N){
	            while(index <= N && nums[index]!= val){
	                index++;
	            }
	            while(N>=0 && nums[N]==val){
	                N--;
	            }
	            if(N>=0 && index<=N && 
	               nums[index] == val && nums[N]!=val){
	                swap(nums, index, N);
	                index++;
	                N--;
	            }
	        }
	        return N+1;
	    }
	    
	    void swap(int[] nums, int i, int j){
	        int temp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = temp;
	    }

	public static void main(String[] args) {
		RemoveElement removeElement = new RemoveElement();
		
		int[] nums01 = new int[]{0,1,2,2,3,0,4,2};
		System.out.println( "removeElement.removeElement(new int[]{0,1,2,2,3,0,4,2}, 2) : " + removeElement.removeElement(nums01, 2));
		System.out.println( Arrays.toString(nums01) );
		
		int[] nums02 = new int[]{0, 1, 4, 5, 6, 7};
		System.out.println( "removeElement.removeElement(new int[]{0, 1, 4, 5, 6, 7}, 2) : " + removeElement.removeElement(nums02, 2));
		System.out.println( Arrays.toString(nums02) );
		
		int[] nums03 = new int[]{2, 2, 2, 2, 2};
		System.out.println( "removeElement.removeElement(new int[]{2, 2, 2, 2, 2}, 2) : " + removeElement.removeElement(nums03, 2));
		System.out.println( Arrays.toString(nums03) );
		
		int[] nums04 = new int[]{2};
		System.out.println( "removeElement.removeElement(new int[]{2}, 2) : " + removeElement.removeElement(nums04, 2));
		System.out.println( Arrays.toString(nums04) );
		
		int[] nums05 = new int[]{};
		System.out.println( "removeElement.removeElement(new int[]{}, 2) : " + removeElement.removeElement(nums05, 2));
		System.out.println( Arrays.toString(nums05) );
		
		System.out.println( " \n 1- data samples (use cases) to debug your code : ( 'empty' array [], arrays of 'size 1' [2] ARE ALWAYS amaong use cases)");
		System.out.println( " 2- NO value - check index/N");
		System.out.println( " 3- ALL elements as value");
		System.out.println( " 4- array of size 1 --> while (index <= N)");
		System.out.println( " 5- empty array --> return N+1");



	}

}
