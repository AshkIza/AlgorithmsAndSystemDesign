package leetcode.Strings;

/* https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/885/
 * 
 * 
 * Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 * */
public class ImplementStrStr {
	
    public static int strStr(String haystack, String needle) {
        if(needle.length() ==0) return 0;
        int index = 0;
        while(index < haystack.length()){
            if(haystack.charAt(index) == needle.charAt(0) 
                 && needlefound(haystack, needle, index)){
                return index;
            }else{
                index++;
            }
        }
        return -1;
    }
    
    private static boolean needlefound(String haystack, String needle, int start){
        if(needle.length() == 1) return true;
        for(int i = 1; i < needle.length(); i++){
            if(start+i >= haystack.length()) return false;
            if(haystack.charAt(start+i)!=needle.charAt(i)) return false;
        }
        return true;
    }

	public static void main(String[] args) {
		System.out.println( "strStr (hello, ll) : " + strStr("hello", "ll"));
		System.out.println( "strStr (aaaaa, bba) : " + strStr("aaaaa", "bba"));
		System.out.println( "strStr ('', '') : " + strStr("", ""));


	}

}
