package hackerank;

import java.util.stream.IntStream;

/* https://www.hackerrank.com/challenges/fair-rations/problem
 * 
 * distribute as few loaves as possible according to the following rules:

1-Every time you give a loaf of bread to some person ,
 	you must also give a loaf of bread to the person immediately in front of or behind them 
 	in the line (i.e., persons  or ).
2-After all the bread is distributed, 
	each person must have an even number of loaves.

Given the number of loaves already held by each citizen,
 find and print the minimum number of loaves you must distribute to satisfy the two rules above.
  If this is not possible, print NO.
 * 
 * */
public class FairRations {
	
	static String fairRations(int[] B) {
        int index = 0;
        int count = 0;
        while(index < B.length){
            if(isOdd(B[index])){
                if(index == B.length - 1){
                    return "NO";
                } 
                int c = isOdd(B[index+1]) ? 2 : 1;//do NOT mess up index in the middle of the loop
                B[index] = B[index] + 1;
                B[index+1] = B[index+1] + 1;
                index+=c;//increment the index at the end of the loop
                count+=2;
            }else{
                index++;
            }
        }
        return String.valueOf(count);
    }
    
    static boolean isOdd(int val){
        return val % 2 == 1;
    } 

	public static void main(String[] args) {
		System.out.println("when using array indices in a while loop, only increment it at the end");
		System.out.println(" DO NOT mess up the index in the middle of the loop while still processing it\n");

		System.out.println("2, 3, 4, 5, 6 -> " + fairRations(IntStream.of(2, 3, 4, 5, 6).toArray()));
		System.out.println("1, 2 -> " + fairRations(IntStream.of(1, 2).toArray()));
	}

}
