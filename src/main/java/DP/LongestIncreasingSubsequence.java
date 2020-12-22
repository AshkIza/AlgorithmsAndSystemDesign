package DP;

/*
 * https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/
 * 
 * */
public class LongestIncreasingSubsequence {
	
	
	//time complexity O(N^2)   extra space complexity O(N)
	public static String LIS(int[] array) {
		int N= array.length;
		int[] mem = new int[N];//Cache to save LIS for each element
		mem[N-1] = 1; //base case : last element (always has sub-sequence of length 1)
		int maxLis = 0; int index = -1;
		for(int i = N-2; i>= 0; i--) {//find mem[i] (maximum lis for element i)
			int lis = 0;
			for(int j = i+1; j < N; j++) {
				if(array[j] > array[i]) {
					if(mem[j] > lis) {
						lis = mem[j];
					}
				}
			}
			
			mem[i] = 1 + lis;// ith LIS = 1 + (i-1)th LIS (breaking problem to smaller problems)
			if(mem[i] > maxLis) {
				index = i;
				maxLis = mem[i];
			}
		}
		
		//printing out LIS string starting from index
		String str = "" + array[index];
		maxLis--;
		for (int i = index+1; i < N; i++) {
			if(mem[i] == maxLis) {//has found next element in LIS
				str += ", " +  array[i];
				maxLis--;
			}
		}
		
		return str;
	}
	
	

	public static void main(String[] args) {
		int[] A = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };

		System.out.print("LIS is " + LIS(arr));

	}

}
