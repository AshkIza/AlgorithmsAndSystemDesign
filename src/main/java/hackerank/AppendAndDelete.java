package hackerank;

/* https://www.hackerrank.com/challenges/append-and-delete/problem
 * 
 * You have a string of lowercase English alphabetic letters. 
 * You can perform two types of operations on the string:

Append a lowercase English alphabetic letter to the end of the string.
Delete the last character in the string. 
Performing this operation on an empty string results in an empty string.
Given an integer, , and two strings,  and , 
determine whether or not you can convert  to  by performing exactly  of the above operations on . 
If it's possible, print Yes. Otherwise, print No.
 * 
 * */
public class AppendAndDelete {
	
	 static String appendAndDelete(String s, String t, int k) {
	        if (s.length() == 0 || t.length() == 0) return "No";
	        int s_size= s.length();
	        int t_size= t.length();
	        int startIndex = -1;
	        while(startIndex+1 < Math.min(s_size, t_size) && 
	            s.charAt(startIndex+1) == t.charAt(startIndex+1)){
	                startIndex++;
	        }
	        int toBeDeleted =s_size - (startIndex+1);
	        int tobeAdded = t_size - (startIndex+1);
	        if(k < toBeDeleted + tobeAdded) return "No";
	        if(k == toBeDeleted + tobeAdded) return "Yes";
	        if( (k - (toBeDeleted + tobeAdded)) % 2 == 0) return "Yes";//add/delete no effect
	        if(k  >= (s_size + t_size)) return "Yes";// can we empty s and re-write t ?
	        return "No";
	    }

	public static void main(String[] args) {
		System.out.println(" appendAndDelete(\"y\", \"yu\", 2) ? " + appendAndDelete("y", "yu", 2));
		System.out.println(" appendAndDelete(\"hackerhappy\", \"hackerrank\", 9) ? " + appendAndDelete("hackerhappy", "hackerrank", 9));

	}

}
