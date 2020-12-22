package Udemy;


public class ReverseString {
	
	//easier and better method, with less chance of doing mistakes, 
	//also this method can be applied to strings/integers/.. without doing any type conversion
	//time O(N)  extra space o(N)
	public static String  reverseMethod02(String str) {
		//always think of corner cases
		if(str == null) {
			return null;
		}
		if(str.length()== 0 || str.length()== 1) {
			return str;
		}
		String reversedString = "";
		for(char ch : str.toCharArray()) {
			reversedString = ch + reversedString;
			//it is like, instead of writing from left, keep writing from right
		}
		
		return reversedString;
	}
	
    //a little better method, but chances to make mistakes in indices,..
	//this is better if were dealing with an array (need to convert string to array)
	//(then operation was in-place - no extra space)
	//time O(N/2)   space extra :O(N) since String is immutable in java
	public static String  reverseMethod01(String str) {
		//always think of corner cases
		if(str == null) {
			return null;
		}
		if(str.length()== 0 || str.length()== 1) {
			return str;
		}
		char[] charString = str.toCharArray();//use char[] since String is immutable
		int N = charString.length;
		int mid = (N%2 == 0) ? N/2 : (N-1)/2;
		for (int i=0; i < mid ; i++) {
			char temp = charString[i];
			charString[i] = charString[N-i-1];
			charString[N-i-1] = temp;
		}
		
		return new String(charString);
	}
	

	public static void main(String[] args) {
		
		
		String[] allstrings = new String[] {"abcdef", "abcdefg", "", " This is a long string, with lots of characters spaces commas@!  "};
		for(String s :allstrings) {
			System.out.println("string: " + s + "   reversed: " + reverseMethod02(s) 
		    + " doublereversed: " +  reverseMethod02(reverseMethod02(s)));
			
		}

	}

}
