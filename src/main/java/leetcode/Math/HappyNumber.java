package leetcode.Math;

import java.util.HashSet;
import java.util.Set;

/* Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, 
replace the number by the sum of the squares of its digits,
 and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
  Those numbers for which this process ends in 1 are happy numbers.

	Example: 
	
	Input: 19
	Output: true
	Explanation: 
	12 + 92 = 82
	82 + 22 = 68
	62 + 82 = 100
	12 + 02 + 02 = 1
	
	solutions : https://www.geeksforgeeks.org/happy-number/
	"Happy/unhappy numbers characteristics":  
	https://www.stem.org.uk/news-and-views/opinions/how-find-happy-number
		+happy numbers ends in 1
		+unhappy numbers enter a loop at some point! (key piece of information!)
	    
	    A number is called happy if it leads to 1 after a sequence of steps where
	     in each step number is replaced by sum of squares of its digit that is if we start with Happy Number
	      and keep replacing it with digits square sum, we reach 1
	
	    A number will not be a Happy Number when it makes a loop in its sequence
	     that is it touches a number in sequence which already been touched.
	      So to check whether a number is happy or not, we can keep a set, if same number occurs again 
	      we flag result as not happy. 
 * 
 * */
public class HappyNumber {
	
	public boolean isHappy(int n) {
        Set<Integer> history = new HashSet<>();
        int sumOfSquares = squareSum(n);
        while(sumOfSquares!=1){
            if(history.contains(sumOfSquares)){
                return false;//entered a loop--> unhappy
            }
            history.add(sumOfSquares);
            sumOfSquares = squareSum(sumOfSquares);
        }
        return true;
    }
	
	   private int squareSum(int n){
	        int sum = 0 ;
	        while(n>0){
	            int digit = n%10;
	            sum += digit*digit;
	            n/=10;
	        }
	        return sum;
	    }

	public static void main(String[] args) {
		HappyNumber happyNumber = new HappyNumber();

		System.out.println("happyNumber.isHappy(1) : " + happyNumber.isHappy(1));
		System.out.println("happyNumber.isHappy(19) : " + happyNumber.isHappy(19));
		System.out.println("happyNumber.isHappy(20) : " + happyNumber.isHappy(20));
	}

}
