package leetcode.BitWise;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*https://leetcode.com/explore/interview/card/top-interview-questions-easy/99/others/721/
 * 
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
 * 
 * */
public class ValidParentheses {
	
	  public static boolean isValid(String s) {
	        if(s.length() < 2) return false;
	        Map<Character, Character> brackets = new HashMap<>();
	        brackets.put( '}', '{');//close-open bracket mapping
	        brackets.put( ']', '[');
	        brackets.put( ')', '(');
	        Stack<Character> openBrackets = new Stack<>();//put only opens bracks in
	        for(char ch : s.toCharArray()){
	            if(brackets.containsValue(ch)){//open brackers
	                openBrackets.push(ch);
	            }else{//close brackets
	                if(openBrackets.isEmpty()) return false;
	                Character lastOpenBracket = openBrackets.pop();
	                if(!brackets.get(ch).equals(lastOpenBracket)) return false;
	            }
	        }
	        return openBrackets.isEmpty();
	    }

	public static void main(String[] args) {
		System.out.println("isValid(\"()[]{}\") " + isValid("()[]{}"));
		System.out.println("isValid(\"([)]\") " + isValid("([)]"));
		System.out.println("isValid(\"[[[[\") " + isValid("[[[["));


	}

}
