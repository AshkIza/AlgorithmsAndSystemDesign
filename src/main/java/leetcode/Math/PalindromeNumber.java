package leetcode.Math;

/*  Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

	Example 1:
	
	Input: 121
	Output: true
	Example 2:
	
	Input: -121
	Output: false
	Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
	Example 3:
	
	Input: 10
	Output: false
	Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
	Follow up:
	
	Coud you solve it without converting the integer to a string?
	
	solutions: time : O(log10(n))  space : O(1)
	https://www.programcreek.com/2013/02/leetcode-palindrome-number-java/
	
 NOTE : how to find and remove right-most and left-most digits of an integer
    n = (int) Math.log10(x)
    a = (int) Math.pow(10, n)
 	left = x / a    -> find left-most
 	right = x % 10  ->find right-most
 	x = x % a;      -> remove left-most
 	x = x /10;      ->remove right-most
 * 
 * 
 * */
public class PalindromeNumber {
	
	   public boolean isPalindrome(int x) {
	        if(x < 0){
	            return false;//negatives are not palindrome
	        }
	        int N = (int) Math.log10(x);
	        int a = (int) Math.pow(10, N);
	        int b = 1;
	        while( a > b){
	            int left = (x/a) % 10;
	            int right = (x % (b*10)) / b;
	            if(left != right){
	                return false;
	            }
	            a /=10;
	            b *= 10;
	        }
	        return true;
	    }
	   
	   /*  NOTE : how to find and remove right-most and left-most digits of an integer
	    * 
	    * */
	   public boolean isPalindromeSimplified(int x) {
	        if(x < 0){
	            return false;//negatives are not palindrome
	        }
	        int n = (int) Math.log10(x);// Math.log() returns double (should cast it to int)
	        int a = (int) Math.pow(10, n);//Math.pow() returns double (cast it to int)
	        
	        while( a > 0){
	            int left = x / a;//find left-most
	            int right = x % 10;//find right-most
	            if(left != right){
	                return false;
	            }
	            x = x % a;//remove left-most
	            x = x /10;//remove right-most
	            a = a/100;//after removing 2 digits, rank of number drops 2 degree (10^2)
	        }
	        return true;
	    }

	public static void main(String[] args) {
		PalindromeNumber palindromeNumber = new PalindromeNumber();
		
		System.out.println(" palindromeNumber.isPalindrome(1234321) " + palindromeNumber.isPalindrome(1234321)); 
		System.out.println(" palindromeNumber.isPalindrome(123321) " + palindromeNumber.isPalindrome(123321)); 
		System.out.println(" palindromeNumber.isPalindrome(1321) " + palindromeNumber.isPalindrome(1321));
		System.out.println(" palindromeNumber.isPalindrome(-121) " + palindromeNumber.isPalindrome(-121)); 
		System.out.println(" palindromeNumber.isPalindrome(1) " + palindromeNumber.isPalindrome(1)); 
		System.out.println(" palindromeNumber.isPalindrome(10) " + palindromeNumber.isPalindrome(10)); 

		System.out.println("\n palindromeNumber.isPalindromeSimplified(1234321) " + palindromeNumber.isPalindromeSimplified(1234321)); 
		System.out.println(" palindromeNumber.isPalindromeSimplified(123321) " + palindromeNumber.isPalindromeSimplified(123321)); 
		System.out.println(" palindromeNumber.isPalindromeSimplified(1321) " + palindromeNumber.isPalindromeSimplified(1321)); 
		System.out.println(" palindromeNumber.isPalindromeSimplified(-121) " + palindromeNumber.isPalindromeSimplified(-121)); 
		System.out.println(" palindromeNumber.isPalindromeSimplified(1) " + palindromeNumber.isPalindromeSimplified(1)); 
		System.out.println(" palindromeNumber.isPalindromeSimplified(10) " + palindromeNumber.isPalindromeSimplified(10)); 

		
		System.out.println("\n NOTE : how to find and remove right-most and left-most digits of an integer"); 
		System.out.println("    n = (int) Math.log10(x)"); 
		System.out.println("    a = (int) Math.pow(10, n)"); 
		System.out.println(" 	left = x / a    -> find left-most"); 
		System.out.println(" 	right = x % 10  ->find right-most"); 
		System.out.println(" 	x = x % a;      -> remove left-most"); 
		System.out.println(" 	x = x /10;      ->remove right-most"); 
	}

}
