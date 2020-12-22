package hackerank;

import java.util.ArrayList;
import java.util.List;

/* https://www.hackerrank.com/challenges/happy-ladybugs/problem
 * 
 * A ladybug is happy only when its left or right adjacent cell (i.e., ) is occupied by another ladybug having the same color.
   In a single move, you can move a ladybug from its current position to any empty cell.

 * Given the values of  and  for  games of Happy Ladybugs,
 *  determine if it's possible to make all the ladybugs happy. 
 *  For each game, 
 *  	print YES on a new line if all the ladybugs can be made happy through some number of moves. Otherwise, print NO.
 * 
 * 
 * solution: https://coding-gym.org/challenges/happy-ladybugs/
 * */
public class HappyLadybugs {
	
    static String happyLadybugs(String b) {
        char[] charArray = b.toCharArray();
        char EMPTY = '_';
        if(charArray.length == 0) return "NO";
        if(charArray.length == 1 && charArray[0] == EMPTY) return "YES";
        int[] frequencyCount = new int[27];//0-> 'A' 26->'_'
        boolean alreadyHappy = true;
        for(int i = 0; i< charArray.length; i++){
           char ch = charArray[i];
           if(ch == EMPTY) {
             frequencyCount[26]++;  
           }else{
              int index = (int) (ch - 'A'); 
              if(index >=0 && index < 26){
                  frequencyCount[index]++;
              }
           }
           alreadyHappy &= isHappy(charArray, i);
        }
        if(alreadyHappy) return "YES";
        boolean wasHappy = true;
        for(int i = 0; i < 26; i++){
            if(frequencyCount[i] == 1) {
                wasHappy = false;
            }
        }
        if(!wasHappy) return "NO";
        if(frequencyCount[26] > 0) return "YES";// can be happy again
        return "NO";
    }
    
    static boolean isHappy(char[] charArray, int index){
        if(index-1>=0 && charArray[index-1] == charArray[index]) return true;
        if(index+1<charArray.length && charArray[index+1] == charArray[index])                                       return true;
        return false;
    }
    boolean wasHappy(int[] frequencyCount){//single ladybuy never was happy
        for(int i = 0; i < 26; i++){
            if(frequencyCount[i] == 1) return false;
        }
        return true;
    }

	public static void main(String[] args) {
		List<String> ladeBugs = new ArrayList<String>();
		ladeBugs.add("RBY_YBR");
		ladeBugs.add("X_Y__X");
		ladeBugs.add("__");
		ladeBugs.add("B_RRBR");
		ladeBugs.forEach(
				ladyBug -> System.out.println("ladyBug '" + ladyBug + "' can be Happy? " + happyLadybugs(ladyBug)));
	
		System.out.println("\n MAKE SURE to understand the problem! ");
		System.out.println(" TASK : identify if you can make them happy (YES/NO result)");
		System.out.println(" TASK mistaken by : re-arrange the buys and print YES/NO at the end");

	}

}
