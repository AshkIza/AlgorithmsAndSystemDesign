package Udemy;

import java.util.HashMap;
import java.util.Map;

/* given a string, return the character that is most commonly used in the string
 * --examples:
 * maxChar("abccccccccccd") === "c"
 * maxChar("apple 12311111") === "1"
 * */

/* similar question that we can use similar technique (character map)
 *  - having a string, what is the most common character ?
 *  - having two strings, check if two strings having same number of characters (anagrams)
 *  - find if there is a character repeated in a string
 */
public class maxChar {
	// default values 
	// (primitives)int/double -> 0/0.0    (objects)Integer/Double -> null 
	public static char maxChar(String str) {
		//filter out corner cases first
		if(str == null || str.length() == 0) {
			return ' ';
		}
		if(str.length() == 1) {
			return str.charAt(0);
		}
		//You can't use primitive values for the Map keys/values
		//Only Java objects can be used as keys and values in a Java Map.
		//In case you pass primitive values (e.g. int, double etc.) to a Map as key or value, 
		//"the primitive values will be auto-boxed before being passed" as parameters.
		//Map<char,int> (not accepted) -> Map<Character, Integer>
		//http://tutorials.jenkov.com/java-collections/map.html
		Map<Character, Integer> characterMap = new HashMap<>();
		for(char ch : str.toCharArray()) {
			Character character =  Character.valueOf(ch);
			Integer charCount = characterMap.get(character);
			charCount = charCount == null ? Integer.valueOf(1) : Integer.valueOf(charCount.intValue() +1); 
			characterMap.put(character, charCount++);
		}
		
		int maxCharCount = 0;
		Character maxchar = null;
		for (Character key : characterMap.keySet()) {
			 int count = (int) characterMap.get(key);
			 if(count > maxCharCount) {
				 maxchar = key;
				 maxCharCount = count;
			 }
		}
		return maxchar.charValue();
	}

	public static void main(String[] args) {
		String[] tests = new String[] {"", "abccccccccccd", "apple 12311111", "   d "};
		for(String test : tests) {
			System.out.println(" test : " + test + " maxChar : " + maxChar(test));
		}
	}
	
	/*
	 * Character/char conversions:
		char -> Character
		char c = 'a';
		Character c = new Character('z'); 
		Character value = Character.valueOf(c);
		
		Character -> char
		Character character = new Character('z’);
		char ch = character.charValue();
	 * */
	
	/*iterate through the characters of a string
	 * https://stackoverflow.com/questions/196830/what-is-the-easiest-best-most-correct-way-to-iterate-through-the-characters-of-a
	 * option 1:
	 * for(int i = 0, n = s.length() ; i < n ; i++) { 
		    char c = s.charAt(i); 
		}
	
		option 2:
		for(char c : s.toCharArray()) {
		    // process c
		}
	 * */
}
