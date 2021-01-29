package leetcode.BitWise;

import java.util.Arrays;

/*  Number of 1 Bits

Write a function that takes an unsigned integer and return the number of '1' bits it has
 (also known as the Hamming weight).

 

Example 1:

Input: 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
Example 2:

Input: 00000000000000000000000010000000
Output: 1
Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.
Example 3:

Input: 11111111111111111111111111111101
Output: 31
Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.
 

Note:

Note that in some languages such as Java, there is no unsigned integer type. In this case, 
  the input will be given as signed integer type and should not affect your implementation,
  as the internal binary representation of the integer is the same whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. 
   Therefore, in Example 3 above the input represents the signed integer -3.
 * 
 * */

public class HammingWeight {
	
    public static int hammingWeight(int n) {     
    	int significantBit = (n > 0) ? (int) (Math.log(n)/Math.log(2)) + 1 : 32;//find the significant bit
        int a = 1;
        int hammingWeight = 0;
        for(int i = 1; i <= significantBit; i++){
            if((a & n) != 0) hammingWeight++;
             a = a << 1;//left-shift
        }
        return hammingWeight;
    }

	public static void main(String[] args) {
		System.out.println("hammingWeight(100100111000111010000010101010) " + hammingWeight(618897578));
		System.out.println("hammingWeight(111111111111111111111110101111) " + hammingWeight(1073741743));
		System.out.println("hammingWeight(11111111111111111111111111111101) " + hammingWeight(-3));//11111111111111111111111111111101

	}

}
