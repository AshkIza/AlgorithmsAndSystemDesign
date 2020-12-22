package leetcode.Math;

/*  Implement int sqrt(int x).

	Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
	
	Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
	
	Example 1:
	
	Input: 4
	Output: 2
	Example 2:
	
	Input: 8
	Output: 2
	Explanation: The square root of 8 is 2.82842..., and since 
	             the decimal part is truncated, 2 is returned.
	             
	             
 * 
 * 
 * */
public class Sqrt_x {
	
    public int mySqrt(int x) {
        if(x==0 || x==1){
            return x;
        }
        int n = logTwo(x);
        n = (n%2 == 0) ? (n+2)/2 : (n+1)/2;
        int a = 1;//lower bound
        int b = (int) Math.pow(2, n);// upper bound
        return helper(a, b, x);
    }
    public int helper(int a, int b, int x) {
       if(b - a == 1){
            return a;
        }
      int m = a/2 + b /2;
       int l = m * m ;
     if(l == x) {
         return m;
      } 
        
      if( l < 0 ){//integer overflow
    	 return helper(a, m, x);
      }
        
      if(l < x){
         return helper(m, b, x);
      }else{
         return helper(a, m, x); 
      }
   }
    
    // implementation of log2(n)
    public int logTwo(int x) {
        int c = 0;
        while(x  > 1){
            c++;
            x = x>>1;
        }
        return c;
    }

	public static void main(String[] args) {
		Sqrt_x sqrt = new Sqrt_x();
		
		System.out.println("sqrt.mySqrt(35) = " + sqrt.mySqrt(35) + " ; Math.sqrt=" + (int) Math.sqrt(35)); 
		System.out.println("sqrt.mySqrt(31223) = " + sqrt.mySqrt(31223) + " ; Math.sqrt = " + (int) Math.sqrt(31223)); 
		System.out.println("sqrt.mySqrt(Integer.MAX_VALUE) = " + "("+ Integer.MAX_VALUE + ") " + sqrt.mySqrt(Integer.MAX_VALUE) + " ; Math.sqrt = " + (int) Math.sqrt(Integer.MAX_VALUE)); 

		System.out.println("sqrt.mySqrt(258) = " + sqrt.mySqrt(258) + " ; Math.sqrt = " + (int) Math.sqrt(258)); 

		
		System.out.println("sqrt.mySqrt(2147395599) = " + sqrt.mySqrt(2147395599) + " ; Math.sqrt = " + (int) Math.sqrt(2147395599)); 


	}

}
