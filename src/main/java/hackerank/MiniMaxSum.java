package hackerank;

/*  Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.
 * 
 * https://www.hackerrank.com/challenges/mini-max-sum/problem
 * 
 * 
 * */

public class MiniMaxSum {
	
    static void miniMaxSum(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        long sum = arr[0];
        for(int i = 1 ; i < 5;i++){
            min = (arr[i] < min) ? arr[i] : min;
            max = (arr[i] > max) ? arr[i] : max;
            sum += arr[i];
        }
        System.out.println("miniMaxSum : " + (sum - max) + " " + (sum - min));
    }

	public static void main(String[] args) {
		miniMaxSum(new int[] {1,3,5,7,9});
	}

}
