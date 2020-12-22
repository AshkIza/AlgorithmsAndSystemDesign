package leetcode.Strings;
/* 
 * 
	Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
	
	Example 1:
	
	Input: "babad"
	Output: "bab"
	Note: "aba" is also a valid answer.
	Example 2:
	
	Input: "cbbd"
	Output: "bb"
	
	Solution : https://www.programcreek.com/2013/12/leetcode-solution-of-longest-palindromic-substring-java/
 * 
 * */
public class LongestPalindromicSubString {
	
	  public  String longestPalindrome(String s) {
	        if(s.length() == 0 || s.length() == 1){
	            return s;
	        }
	        int pivotPoint = 0;
	        int pivotSize = 1;
	        /*always think of special cases-> first and last elements in string 
	         * (sometime they need special logic also index out of bound check)*/
	        if(isDoublePivot(s, 0) > 0){
	            pivotSize = 2;
	        }
	        /* test and de-bug your logic with enough set of use cases!
	            write down all possible use cases data samples (otherwise your code fails on a use case!)
	             "ac"
	             "aaaa"
	             "bb"
	             "abbc"*/
	        for(int i = 1 ; i < s.length(); i++){
	            int singleSize = isSinglePivot(s, i);
	            int doubleSize = isDoublePivot(s, i);

	            if( singleSize > pivotSize){
	                pivotPoint = i;
	                pivotSize = singleSize;
	            }
	                
	            if( doubleSize > pivotSize){ //"aaaa" use case --> "aaaa"  has both isSinglePivot() && isDoublePivot()
	                pivotPoint = i;
	                pivotSize = doubleSize;
	            }
	        }
	        if(pivotSize == 0){
	            return "";
	        }
	        return palindrome(s, pivotSize, pivotPoint);
	    }
	    
	    public int isSinglePivot(String s, int pivotPoint){
	        int size = 1;//"ac" use case --> "a"
	        int i = pivotPoint - 1;
	        int j = pivotPoint + 1;
	        while( i >= 0 && j < s.length()){
	            if(s.charAt(i) == s.charAt(j)){
	                size = j - i + 1;
	                i--;
	                j++;
	            }else{
	                return size;
	            }
	        }
	        return size;
	    }
	    
	    public int isDoublePivot(String s, int pivotPoint){
	        if(pivotPoint >= s.length() -1 || s.charAt(pivotPoint) != s.charAt(pivotPoint +1)){
	            return 0;
	        }
	        int size = 2;// "bb" use case --> "bb
	        int i = pivotPoint - 1;
	        int j = pivotPoint + 2;
	        while( i >= 0 && j < s.length()){
	            if(s.charAt(i) == s.charAt(j)){
	                size = j - i + 1;
	                i--;
	                j++;
	            }else{
	                return size;
	            }
	        }
	        return size;
	    }
	    
	    String palindrome(String s, int pivotSize, int pivotPoint){
	        String palindrome = "";
	        int i = pivotPoint;
	        int j = pivotPoint;
	        if((pivotSize)%2 != 0){
	            palindrome = String.valueOf(s.charAt(pivotPoint));;//(String) s.charAt(pivotPoint);
	            i= pivotPoint + 1;
	            j = pivotPoint + (pivotSize -1)/2;
	        }else{
	            palindrome = String.valueOf(s.charAt(pivotPoint)) + String.valueOf(s.charAt(pivotPoint));
	            i = pivotPoint + 2;
	            j= pivotPoint + pivotSize/2;
	        }
	        while( i <= j ){
	            palindrome = String.valueOf(s.charAt(i)) + palindrome + String.valueOf(s.charAt(i));
	            i++;//always remeber to increment counter in the loop --> infinite loop
	        }
	        return palindrome;
	    }

	    
	public static void main(String[] args) {
		LongestPalindromicSubString object = new LongestPalindromicSubString();
		System.out.println("longestPalindrome(babad) : " + object.longestPalindrome("babad"));
		System.out.println("longestPalindrome(cbbd) : " + object.longestPalindrome("cbbd"));
		System.out.println("longestPalindrome(ac) : " + object.longestPalindrome("ac"));
		System.out.println("longestPalindrome(aaaa) : " + object.longestPalindrome("aaaa"));
		System.out.println("longestPalindrome(bb) : " + object.longestPalindrome("bb"));
		
		System.out.println("\n HARD lessons learned painfully!!! ");
		System.out.println(" 1) always remeber to increment the counter in the loop --> otherwise infinite loop ");
		System.out.println(" 2) test and debug your logic with enough set of use cases!\n   never limit your testing to the only sample data in the problem");
		System.out.println("   write down all possible use cases data samples (otherwise your code fails on a use case!)");
		System.out.println(" 3) always think of special cases-> first and last elements in the String/Array  \n   (sometimes they need special treatment, also check for index out of bound check ");

		System.out.println("\nchar to String :  (String.valueOf(char))");
		System.out.println(" String.valueOf('abcd'.charAt(2)) : " + String.valueOf("abcd".charAt(2)));
		System.out.println(" (String) 'abcd'.charAt(2) --> error : can not cast from char to String ");
		System.out.println("    error: incompatible types: char cannot be converted to String \n    https://stackoverflow.com/questions/48462019/error-incompatible-types-char-cannot-be-converted-to-string-java?noredirect=1&lq=1 ");

	}

}
