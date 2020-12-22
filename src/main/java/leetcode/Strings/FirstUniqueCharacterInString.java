package leetcode.Strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*  Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.

solution: https://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
 * 
 * */

public class FirstUniqueCharacterInString {
	
	   public static int firstUniqChar(String s) {
	        Map<Character, Integer> dictionary = new HashMap<>();
	        for(char ch : s.toCharArray()){
	            if(dictionary.get(ch) == null){
	                dictionary.put(ch, 1);
	            }else{
	                int count = dictionary.get(ch);
	                dictionary.put(ch, ++count);
	            }
	        }
	        for(int i = 0; i < s.length(); i++){
	            if( dictionary.get(s.charAt(i)) ==1){
	                return i;
	            }
	        }
	        return -1;
	    }

	public static void main(String[] args) {
		String st01 = new String("leetcode");
		String st02 = new String("loveleetcode");
		System.out.println("firstUniqChar(leetcode) is : " + firstUniqChar(st01) + " -  O(N) with O(k) extra sppace for hashing");
		System.out.println("firstUniqChar(loveleetcode) is : " + firstUniqChar(st02) + " -  O(N) with O(k) extra sppace for hashing");
	
		System.out.println();
		System.out.println("string.length()  " + st01.length());
		System.out.println("string.toCharArray().length  " + st01.toCharArray().length);
	}
	
	
	/*  length vs length() in Java
	 * https://www.geeksforgeeks.org/length-vs-length-java/
	 * array.length  : can be used for int[], double[], String[]  to know the length of the arrays.
	 * string.length() :can be used for String, StringBuilder, etc - String class related Objects to know the length of the String
	 *  
	 *  // Here array is the array name of int type 
	 *  int[] array = new int[4]; 
        System.out.println("The size of the array is " + array.length); 
  
        // Here str is a string object 
        String str = "GeeksforGeeks"; 
        System.out.println("The size of the String is " + str.length()); 
	 * 
	 * */

}
