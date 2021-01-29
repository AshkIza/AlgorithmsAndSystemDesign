package leetcode.BitWise;


/*  The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ≤ x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑

The above arrows point to positions where the corresponding bits are different.
 * 
 * */
public class HammingDistance {
	
	  public static int hammingDistance(int x, int y) {
	        int hammingwieght = 0;
	        while(x!=0 || y !=0){
	            int a = x & 1;//1 is mask bit
	            int b = y & 1;
	            if( (a^b) == 1) hammingwieght++;// 0^0 =0 ; 1^1=0
	            x >>= 1;//right-shift
	            y >>= 1;
	        }
	        return hammingwieght;
	    }
	
	public static int hammingDistanceUsingHammingWeight(int x, int y) {
	        return hammingWeight(x ^ y);
    }
	    
	private static int hammingWeight(int n) {
	        int hammingDistance = 0;
	        while( n != 0){
	            hammingDistance += n & 1;
	            n = n >>1;
	        }
	        return hammingDistance ;
	 }
	  
	public static void main(String[] args) {
		System.out.println("hammingDistance((0 0 0 1), (0 1 0 0)) is : " + hammingDistance(1, 4));//((0 0 0 1) : 1, (0 1 0 0) : 4

		System.out.println("hammingDistance((100100111000111010000010101010), (111111111111111111111110101111)) is : " + hammingDistance(618897578, 1073741743));
		//618897578))   100100111000111010000010101010
		//1073741743))  111111111111111111111110101111
	}

}
