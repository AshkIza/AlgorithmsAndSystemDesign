package leetcode.Math;

/*  Implement pow(x, n), which calculates x raised to the power n (xn).

	Example 1:
	
	Input: 2.00000, 10
	Output: 1024.00000
	Example 2:
	
	Input: 2.10000, 3
	Output: 9.26100
	Example 3:
	
	Input: 2.00000, -2
	Output: 0.25000
	Explanation: 2-2 = 1/22 = 1/4 = 0.25
	Note:
	
	-100.0 < x < 100.0
	n is a 32-bit signed integer, within the range [−231, 231 − 1]
	
	
NOTE: IN INTEGER algorithms ALWAYS consider following edge cases
1) n = 0 
2) n is negative 
3) n = Integer.MIN_VALUE 
4) n = Integer.MAX_VALUE 
5) integer overflows! in multiplications and additions
       (a+b)/2 causing integer overflow -> a/2 + b/2
       check multiplication results not exceeding Integer.MIN_VALUE/Integer.MAX_VALUE
       
NOTE: 
 Integer.MAX_VALUE + 1 = Integer.MIN_VALUE  
 Math.abs(Integer.MIN_VALUE) = Integer.MIN_VALUE
 
 * 
 * 
 * solution : (use recursion to store x ^ n-i 
 * https://www.programcreek.com/2012/12/leetcode-powx-n/
 * public double myPow(double x, int n){
    if(n==0)
        return 1;
 
    if(n<0){
        return 1/helper(x, -n);
    }
 
    double v = helper(x, n/2);
 
    if(n%2==0){
       return v*v;
    }else{
       return v*v*x;
    }    
}
 * 
 * */
public class Pow_x_n {
	
	 /* iterative approach 
	  * time : O(log2(n))
	  * extra space : O (log2(n))
	  * */
	  public double myPow(double x, int n) {
			 boolean isNegative = n < 0;
			 boolean isIntegerMin = n == Integer.MIN_VALUE;
			 if(isNegative){
			   if(isIntegerMin){
				  n = Integer.MAX_VALUE;
			   }else{
				  n = Math.abs(n);
			   }
			}
	        if(n == 0 || x == 1.0){// spacial cases
	            return 1.0;
	        }
	        int N = (int) (Math.log(n)/Math.log(2)) + 1;//number of binary digits 
	        double[] pow = new double[N];
	        pow[0] = x;
	        for(int i = 1; i < N; i++){
	            pow[i] = pow[i-1] * pow[i-1];// saving the x^ (n-i) like dynamic programming
	        }
	        double result = 1.0;
	        int count = 0;
	        while(n != 0 && count < N){
	            if( (n & 1) == 1){
	                result *= pow[count];
	            }
	            count++;
	            n = n >> 1;
	        }
	        if(isNegative){//HANDLING NEGATIVES
	        	result = 1 / result;
	        	if(isIntegerMin){//Handling Integer.MIN_VALUE
	        		result *= 1/x;
				}
	        }
	        return result;
	    }

	public static void main(String[] args) {
		
        System.out.println("Math.log(n) is log-e(n) - natural logarithm --> log2(n) = Math.log(n)/Math.log(2)" ); 
        System.out.println("Math.log(n) returns double --> cast it to int if needed!" ); 
        System.out.println("number of binary digits of integer n = (int) (Math.log(n)/Math.log(2)) + 1" ); 
        
        System.out.println(); 
        System.out.println("NOTE: Integer.MAX_VALUE + 1 = Integer.MIN_VALUE  / Math.abs(Integer.MIN_VALUE) = Integer.MIN_VALUE"); 
        System.out.println("Integer.MIN_VALUE : " + Integer.MIN_VALUE);
        System.out.println("Integer.MAX_VALUE : " + Integer.MAX_VALUE); 
        System.out.println("Integer.MAX_VALUE + 1: " + (Integer.MAX_VALUE + 1) + " (Integer.MIN_VALUE)"); 
        System.out.println("Math.abs(Integer.MIN_VALUE) : " + Math.abs(Integer.MIN_VALUE) + " (Integer.MIN_VALUE)"); 
        System.out.println(" NOTE : abs(Integer.MIN_VALUE) > Integer.MAX_VALUE --> causing integer overflow! " ); 
        System.out.println("        never do Math.abs(Integer.MIN_VALUE) --> returns Integer.MIN_VALUE" ); 

        System.out.println(); 
        System.out.println("NOTE: IN INTEGER algorithms ALWAYS consider following edge cases"); 
        System.out.println("1) n = 0 "); 
        System.out.println("2) n is negative "); 
        System.out.println("3) n = Integer.MIN_VALUE "); 
        System.out.println("4) n = Integer.MAX_VALUE "); 
        System.out.println("5) integer overflows! in multiplications and additions"); 
        System.out.println("       (a+b)/2 causing integer overflow -> a/2 + b/2"); 
        System.out.println("       check multiplication results not exceeding Integer.MIN_VALUE/Integer.MAX_VALUE"); 

        System.out.println(); 
		Pow_x_n pow = new Pow_x_n();
        System.out.println("pow.myPow(2.0000, 18) : " + pow.myPow(2.0000, 18)); 
        System.out.println("pow.myPow(2.0000, -18) : " + pow.myPow(2.0000, -18)); 
        System.out.println("pow.myPow(1.0000, -2147483648) : " + pow.myPow(1.0000,-2147483648)); 
        System.out.println("pow.myPow(0.9, -2147483648) : " + pow.myPow(0.9,-2147483648)); 





	}

}
