package hackerank;

import java.util.Arrays;
import java.util.List;

/* https://www.hackerrank.com/challenges/sherlock-and-array/problem
 * Watson gives Sherlock an array of integers.
 *  His challenge is to find an element of the array such that 
 *  	the sum of all elements to the left is equal to the sum of all elements to the right. 
 *  For instance, given the array ,  is between two subarrays that sum to . 
 * If your starting array is , that element satisfies the rule as left and right sum to .
 * 
 * */
public class SherlockandArray {
	
    static String balancedSums(List<Integer> arr) {
        if(arr.size() == 1) return "YES";
        if(arr.size() == 2) return "NO";
        int totalsum = arr.stream().mapToInt(Integer::intValue).sum();
        int leftsum = 0;
        for(int i = 0;  i <  arr.size(); i++){
            int rightsum = totalsum - (leftsum + arr.get(i));
            if(leftsum == rightsum) return "YES"; 
            leftsum += arr.get(i);
        }
        return "NO";
    }

	public static void main(String[] args) {
		System.out.println("balancedSums(Arrays.asList(1, 1, 4, 1, 1)) : " + balancedSums(Arrays.asList(1, 1, 4, 1, 1)));
		System.out.println("balancedSums(Arrays.asList(1, 2, 3)) : " + balancedSums(Arrays.asList(1, 2, 3)));
		System.out.println("balancedSums(Arrays.asList(2, 0, 0, 0)) : " + balancedSums(Arrays.asList(2, 0, 0, 0)));
	}

}
