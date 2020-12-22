package hackerank;

import java.util.Arrays;

/* https://www.hackerrank.com/challenges/maximizing-xor/problem
 * 
 * Given two integers,  and , find the maximal value of  xor , written , 
 * where  and  satisfy the following condition:



 * */
public class MaximizingXOR {
	
	 static int maximizingXor(int l, int r) {
	        int N =((int) (Math.log(r) /Math.log(2)) )+ 1;// number of bits to consider
	        while(N > 0){
	            for(int i = l; i <=r; i++){
	                int rev = reverseNBits(i,N);
	                if(l <= rev && rev <=r) return i ^ rev;
	            }
	            N--;
	        }
	        return 0;
	    }
	    
	    static int reverseNBits(int a, int N){
	        int allOne = ((int) Math.pow(2, N)) -1 ;
	        return a ^ allOne;
	    }

	public static void main(String[] args) {
		int[][] inputs = new int[][] {{1,10}, {5,6},{1,1000}};
		Arrays.stream(inputs).forEach(i -> {
			int a = i[0];
			int b = i[1];
			System.out.println( String.format("maximizingXor(%s,%s) : ", a, b) + maximizingXor(a,b));
		});
	}

}
