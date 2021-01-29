package leetcode.Math;

/*
 * 	Given an integer, write a function to determine if it is a power of three.

	Example 1:
	
	Input: 27
	Output: true
	Example 2:
	
	Input: 0
	Output: false
	Example 3:
	
	Input: 9
	Output: true
	Example 4:
	
	Input: 45
	Output: false
	Follow up:
	Could you do it without using any loop / recursion?
	
	Solution : https://leetcode.com/problems/power-of-three/solution/
	
	NOTE: FOR MATH algorithms, try to find the special characteristics of the NUMBER in the problem
     try to draw a rule, equations for the numbers (use this piece of info to optimize your algorihtm)
     for example here ( isPowerOfthree) : all power of 3 shoudl look like 3 ^ x
         -> all the multiples within the number should be 3
          (rule drwan) --> keep dividing the number by 3 and check the mod to be zero if(n%3 == 0)
          
          
    ANOTHER OBSERVATION FOR 3^n integers: (SPECIAL CHARACTERISTICS of the NUMBERS in the problem)
     public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
    Since 3 is a prime number, the only divisors of 3^19  are 3^0, 3^1,3^2,..3^19. therefore all we need to do is
      divide 3^19 by n. A remainder of 0 means n is a divisor of 3^19 and therefore a power of three.
    

 * 
 * */
public class PowerOfThree {
	 /* Naive solution
	  * 
	  * time : O(log3(n))
	  * space : O(1)
	  * */
	 public boolean isPowerOfThree(int n) {
	     if(n<=0){//negative numbers are not power of 3
	            return false;
	     }
	     while (n > 1){
	           if(n % 3 != 0){
	               return false;
	               //there is a non-three multiple in n -> not power of three
	           }
	           n = n / 3;
	       }
	        return true;//only multiples of 3
	    }
	 
	 /* optimal solutions : looking at SPECIAL CHARACTERISTICS of the NUMBERS in the problem
	  * 
	  * time : O(1)
	  * space : O(1)
	  * */
	 public static boolean isPowerOfThreeOptimal(int n) {
	        return n > 0 && 1162261467 % n == 0;
	    }
	 

	public static void main(String[] args) {
		PowerOfThree powerOfThree = new PowerOfThree();

		
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
		   System.out.println("NOTE: FOR MATH algorithms, try to find the SPECIAL CHARACTERISTICS of the NUMBERs in the problem");
		   System.out.println("     try to draw a rule, equations for the numbers (use this piece of info to optimize your algorihtm)");
		   System.out.println("     for example here ( isPowerOfthree) : all power of 3 shoudl look like 3 ^ x");
		   System.out.println("         -> all the multiples within the number should be 3");
		   System.out.println("          (rule drwan) --> keep dividing the number by 3 and check the mod to be zero if(n%3 == 0)");

		   System.out.println("\nANOTHER OBSERVATION FOR 3^n integers: (SPECIAL CHARACTERISTICS of the NUMBERS in the problem)"); 
		   System.out.println("Since 3 is a prime number, the only divisors of 3^19  are 3^0, 3^1,3^2,..3^19. therefore all we need to do "
		   		+ "    is \n divide 3^19 by n. A remainder of 0 means n is a divisor of 3^19 and therefore a power of three."); 
		   System.out.println("log3(Integer.MAX_VALUE) : " + Math.log(Integer.MAX_VALUE)/Math.log(3));

	}

}
