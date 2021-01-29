package leetcode.Arrays;

import java.util.Arrays;


/*  https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/646/
 * 
 * */

public class RotateArray {
	
	
	 public static void rotate(int[] nums, int k) {
	        if(nums.length == 1) return;
	        int N = nums.length;
	        k = k % N;//saving unneccessary iterations
	        int[] temp = new int[k];
	        for(int i = 0; i < k; i++){
	            temp[i] = nums[N - k + i];
	        }
	        for(int j = N - k - 1; j >= 0; j--){
	            nums[j+k] =  nums[j];
	        }
	        for(int i = 0; i < k; i++){
	            nums[i] = temp[i];
	        }
	}
	 
	 public static void rotateByThreeReverse(int[] nums, int k) {
		 if(nums.length == 1) return;
		 int N = nums.length;
		 reverse(nums, 0, N-1);//O(N/2)
		 reverse(nums, 0, k-1);//O(K/2)
		 reverse(nums, k, N-1);//O((N-K)/2)
	 }
	 private static void reverse(int[] nums, int start, int stop) {
		 while(start <  stop) {
			 int temp = nums[start];
			 nums[start] = nums[stop];
			 nums[stop] = temp;
			 start++;
			 stop--;
		 }
	 }

	public static void main(String[] args) {
		int[] nums = new int[] {1,2,3,4,5,6,7,8,9,10};
		System.out.println("nums : " + Arrays.toString(nums));
		rotate(nums, 3);
		System.out.println("rotate(nums, 3) -> " + Arrays.toString(nums));
		
		int[] nums02 = new int[] {1,2,3,4,5,6,7,8,9,10,11,12};
		System.out.println("\nnums02 : " +Arrays.toString(nums02));
		rotateByThreeReverse(nums02, 4);
		System.out.println("rotateByThreeReverse(nums02, 4) -> " + Arrays.toString(nums02));
	}

}
