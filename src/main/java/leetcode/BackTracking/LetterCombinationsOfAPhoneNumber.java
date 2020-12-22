package leetcode.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*  Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

	A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
	
	
	
	Example:
	
	Input: "23"
	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	Note:
	
	Although the above answer is in lexicographical order, your answer could be in any order you want.
	
	https://www.programcreek.com/2014/04/leetcode-letter-combinations-of-a-phone-number-java/
 * 
 * */
public class LetterCombinationsOfAPhoneNumber {
	
	  public List<String> letterCombinations(String digits) {
	        if (digits.length() == 0 || digits.contains("1")){// handling incorrect data formats
	            return new ArrayList<String>(); 
	        }
	        
	        Map<String, String> dic = new HashMap<>();
	        dic.put("2", "abc");
	        dic.put("3", "def");
	        dic.put("4", "ghi");
	        dic.put("5", "jkl");
	        dic.put("6", "mno");
	        dic.put("7", "pqrs");
	        dic.put("8", "tuv");
	        dic.put("9", "wxyz");

	        return letterCombo(digits, "", new ArrayList<String>(), dic);
	    }
	    
	    public List<String> letterCombo(String remainingInput, String output, List<String> combos, 
	                                    Map<String, String> dic ) {
	        if(remainingInput.length() ==0){//end of branch
	            combos.add(output);// add the reutls to the result list and backtrack
	            return combos;
	        }
	        
	        String ch = String.valueOf(remainingInput.charAt(0));
	        String combo = dic.get(ch);
	        String remaining = remainingInput.substring(1);
	        for(char each : combo.toCharArray()){
	            String out = output + String.valueOf(each);
	            combos = letterCombo(remaining, out, combos, dic);//collect all sub-branch results
	        }
	        return combos;
	    }
	    
	    void print(List<List<Integer>> results){
	    	for(List<Integer> row : results){
	    		System.out.println( row.toString());

	    	}
	    }

	public static void main(String[] args) {
		LetterCombinationsOfAPhoneNumber letterCombo = new LetterCombinationsOfAPhoneNumber();
		
		System.out.println( "letterCombo.letterCombinations('23')");
		System.out.println(Arrays.toString(letterCombo.letterCombinations("23").toArray()));
	}

}
