package hackerank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* https://www.hackerrank.com/challenges/luck-balance/problem
 * 
 * Contest		L[i]	T[i]
1		5	1
2		1	1
3		4	0

If Lena loses all of the contests, her will be . 
Since she is allowed to lose  important contests, and there are only  important contests. She can lose all three contests to maximize her luck at . If , she has to win at least  of the  important contests. She would choose to win the lowest value important contest worth . Her final luck will be .


 * 
 * */
public class LuckBalance {
	
	 static int luckBalance(int k, int[][] contests) {
	        int nonimp = 0;
	        List<Integer> important = new ArrayList<>();
	        int impsum = 0;
	        for(int[] contest : contests){
	            if(contest[1] == 0){
	               nonimp += contest[0]; 
	            }else{
	                important.add(contest[0]);
	                impsum += contest[0];
	            }
	        }
	        int N = important.size();
	        if(N > k){//optimization problem
	            Collections.sort(important);
	            int winsum = 0;
	            for(int i = 0; i < (N-k); i++){
	                winsum+=important.get(i);
	            }
	            int remaining = impsum - winsum;
	            return nonimp + remaining - winsum;
	        }
	        return nonimp + impsum;
	    }

	public static void main(String[] args) {
		int[][] contestes = new int[][] 
				{{5, 1},
				{2, 1},
				{1, 1},
				{8, 1},
				{10, 0},
				{5, 0}};
				
	System.out.println("contests");
	Arrays.stream(contestes).map(st -> st[0] + ", " + st[1]).forEach(System.out::println);
	System.out.println("luckBalance(3,contests) : " + luckBalance(3, contestes));


	}

}
