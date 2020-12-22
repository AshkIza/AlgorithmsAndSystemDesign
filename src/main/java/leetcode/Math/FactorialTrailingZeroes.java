package leetcode.Math;

/* Given an integer n, return the number of trailing zeroes in n!.

	Example 1:
	
	Input: 3
	Output: 0
	Explanation: 3! = 6, no trailing zero.
	Example 2:
	
	Input: 5
	Output: 1
	Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.

 * 
 * solution : https://www.programcreek.com/2014/04/leetcode-factorial-trailing-zeroes-java/
 * 
 * */
public class FactorialTrailingZeroes {
	
	   public int trailingZeroes(int n) {
	        if(n < 0 ){
	            return -1;
	        }
	        if(n < 5 ){
	            return 0;
	        }
	        int m = log5(n);//(int) (Math.log(n) / Math.log(5));
	        int count = 0;
	        int multiple = 1;
	        int c = 1;
	        while(c <= m){
	            multiple *= 5;
	            count += n/multiple;
	            c++;
	        }
	        return count;
	    }
	   
	   private int log5(int n){
		   if(n<=0){
			   return -1;
		   }
		   int log = 0;
		   while(n/5 > 0){
			   log++;
			   n = n / 5;
		   }
		   return log;
	   }

	public static void main(String[] args) {
		FactorialTrailingZeroes factorialTrailingZeroes = new FactorialTrailingZeroes();
		System.out.println("factorialTrailingZeroes.trailingZeroes(3) : " + factorialTrailingZeroes.trailingZeroes(3));
		System.out.println("factorialTrailingZeroes.trailingZeroes(5) : " + factorialTrailingZeroes.trailingZeroes(5));
		System.out.println("factorialTrailingZeroes.trailingZeroes(50) : " + factorialTrailingZeroes.trailingZeroes(50));
		System.out.println("factorialTrailingZeroes.trailingZeroes(126) : " + factorialTrailingZeroes.trailingZeroes(126));



	}

}
