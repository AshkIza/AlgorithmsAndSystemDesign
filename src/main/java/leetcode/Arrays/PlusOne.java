package leetcode.Arrays;

import java.util.Arrays;

/*
 *  https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/559/
 * 
 * */

public class PlusOne {

	public static int[] plusOne(int[] digits) {
        int index = digits.length -1;
        int i = -1;
        while(index >= 0 && digits[index] == 9){
            i = index;
            index--;
        }
        if(i==-1) {//no overflow at all
            digits[digits.length -1]++;
            return digits;
        }
        if(index == -1){//all overflow -> resize array
            int[] newArray = new int[digits.length+1];
            newArray[0] = 1;
            return newArray;
        }
        Arrays.fill(digits, index+1, digits.length, 0);
        digits[index]++;
        return digits;
    }
	
	public static void main(String[] args) {
		int[] case1= new int[] {0};
		int[] case2= new int[] {1,2,3};
		int[] case3= new int[] {1,2,9};
		int[] case4= new int[] {1,9,9};
		int[] case5= new int[] {9,9,9};
		
		System.out.println("case1 : " + Arrays.toString(case1) 
			+ ", plusOne() -> " + Arrays.toString(plusOne(case1)));
		
		System.out.println("case2 : " + Arrays.toString(case2) 
		+ ", plusOne() -> " + Arrays.toString(plusOne(case2)));
		
		System.out.println("case3 : " + Arrays.toString(case3) 
		+ ", plusOne() -> " + Arrays.toString(plusOne(case3)));
		
		System.out.println("case4 : " + Arrays.toString(case4) 
		+ ", plusOne() -> " + Arrays.toString(plusOne(case4)));
		
		System.out.println("case5 : " + Arrays.toString(case5) 
		+ ", plusOne() -> " + Arrays.toString(plusOne(case5)));
	}

}
