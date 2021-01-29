package leetcode.Strings;


/* Longest Common Prefix
Solution
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.

Solution: https://www.geeksforgeeks.org/longest-common-prefix-using-character-by-character-matching/
Longest Common Prefix using Character by Character Matching
	Time Complexity :
	 	Since we are iterating through all the characters of all the strings,
	 	 	so we can say that the time complexity is O(N M) where,
		N = Number of strings
		M = Length of the largest string string 
	Auxiliary Space : 
		To store the longest prefix string we are allocating space which is O(M).
 * */
public class LongestCommonPrefix {
	
	
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        if(strs.length == 1) return strs[0];
        int maxPrefixLength = 0;
        for(String st : strs){
            if(st.length() == 0) return "";
            if(maxPrefixLength == 0){
                maxPrefixLength = st.length();
            }else{
                maxPrefixLength = Math.min(maxPrefixLength, st.length());
            }
        }
        int index = 0;
        String result = "";
        while(index < maxPrefixLength){
            char ch = findCommon(strs, index);
            if(ch == ' ') return result;
            result += String.valueOf(ch);
            index++;
        }
        
        return result;
    }
    
    static char findCommon(String[] strs, int index){
        char c = strs[0].charAt(index);
        for(int i = 1; i < strs.length; i++){
            if(strs[i].charAt(index) != c) return ' ';
        }
        return c;
    }

	public static void main(String[] args) {		
		System.out.println(" longestCommonPrefix(['flower','flow','flight']) : " + longestCommonPrefix( new String[] {"flower","flow","flight"}));
		System.out.println(" longestCommonPrefix(['dog','racecar','car']) : " + longestCommonPrefix( new String[] {"dog","racecar","car"}));
		System.out.println(" longestCommonPrefix(['apple','applby','application']) : " + longestCommonPrefix( new String[] {"apple","applby","application"}));

		
		
		System.out.println("\n array.length vs string.length()  ");
		System.out.println("https://www.geeksforgeeks.org/length-vs-length-java/  ");
		String[] arrayOfStrings = new String[] {"a","ab","abcd", "efghijk", "lmnop"};
		System.out.println(" arrayOfStrings = new String[] {'a','ab','abcd','efghijk', 'lmnop'} : " );
		System.out.println(" arrayOfStrings.length : "  + arrayOfStrings.length);
		System.out.println(" arrayOfStrings[0].length() : "  + arrayOfStrings[0].length());
		System.out.println(" arrayOfStrings[3].length() : "  + arrayOfStrings[3].length());
		
		
		String BlankStringLenght0 = "";
		String BlankStringLenght1 = " ";
		System.out.println( "\n\nBlankStringLenght0.length() : " + BlankStringLenght0.length() + "    BlankStringLenght1.length() : " + BlankStringLenght1.length() );




	}

}
