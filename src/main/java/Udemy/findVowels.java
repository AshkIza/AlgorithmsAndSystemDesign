package Udemy;

/*
 * examples:  vowels:  A E I O U 
 *  vowels('Hi there!') ---> 3
 *  vowels('why do you ASK?') --> 4
 * */
public class findVowels {
	
	//O(n) 
	public static int findVowels(char[] string) {
		int count = 0;
		int upperToLowercase = 'A' - 'a';//32 
		char[] vowels = new char[] {'a','e', 'i', 'o', 'u'};//declaring and initializing char array at the same time
		for(char ch : string) {
			for(char vowel : vowels) {
				if(ch >='A' && ch<= 'Z') {
					ch = (char) (ch - upperToLowercase);//converting upperCase to lowerCase
				}
				if( ch == vowel) {
					count++;
					break;//get out of inner loop
				}
			}
		}
		return count;
	}
	
	public static int findVowelsWithJavaUtilFuncs(char[] string) {
		int count = 0;
		String vowels = "aeiou";
		String st = new String(string);
		st = st.toLowerCase();// String.toLowerCase
		for(char ch : st.toCharArray()) {
			if(vowels.indexOf(ch) >= 0) {
				// search for a character in a string
				// String.indexOf(char) : -1 if not found
				count++;
			}
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		//char[] String.toCharArray()
		System.out.println(findVowels("AEI O U ooii".toCharArray()));
		System.out.println(findVowelsWithJavaUtilFuncs("AEI O U ooii".toCharArray()));
	}
}
