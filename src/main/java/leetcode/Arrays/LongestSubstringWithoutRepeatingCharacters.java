package leetcode.Arrays;

/*  Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * */
public class LongestSubstringWithoutRepeatingCharacters {
	
	public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        String sub = "";
        for(int i = 0 ; i < s.length(); i++){
            if(sub.indexOf(s.charAt(i)) < 0){// if(!sub.contains(s.charAt(i))){
                sub = sub + s.charAt(i);
            }else{
                max = Math.max(max, sub.length());
                int index = sub.indexOf(s.charAt(i));
                sub = sub.substring(index+1);
                sub = sub + s.charAt(i);
            }
        }
        return Math.max(max, sub.length());
    }
	
	public static void main(String[] args) {
		System.out.println("lengthOfLongestSubstring('abcabcbb') :" + lengthOfLongestSubstring("abcabcbb"));
		System.out.println("lengthOfLongestSubstring('bbbbb') :" + lengthOfLongestSubstring("bbbbb"));
		System.out.println("lengthOfLongestSubstring('pwwkew') :" + lengthOfLongestSubstring("pwwkew"));
		System.out.println("lengthOfLongestSubstring('abghilcdecf') :" + lengthOfLongestSubstring("abghilcdecf"));
		System.out.println("lengthOfLongestSubstring('abcefgcmn') :" + lengthOfLongestSubstring("abcefgcmn"));

	}

}
