package leetcode.sortAndSearch;

/* First Bad Version 
 * 
 * https://leetcode.com/explore/interview/card/top-interview-questions-easy/96/sorting-and-searching/774/
 * 
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * 
 * */
public class FirstBadVersion {
	
	private static int firstBadVersion;
	static void set(int a){
		firstBadVersion = a;
	}
	
	static boolean isBadVersion(int version) {
		return version >= firstBadVersion;
	}
	
	///////////////////////
	
	 public static int firstBadVersion(int n) {
		 if (n == 1 || isBadVersion(1)) return 1;//edge cases
	        //for sure n= 1 is good -> a is good, b is bad
	        long a = 1;
	        long b = n;
	        while( b - a >= 1) {
	            if(b-a == 1) return (int) b;
	            long mid = (a+b)/2; // a+b gives overflow if a and b are int
	            if(isBadVersion((int) mid)){
	                b = mid;
	             }else{
	                a = mid;
	            }
	        }
	        return (int) b;
	    }

	public static void main(String[] args) {
		
		System.out.println("NOTE on mid = (a+b)/2 ");
		System.out.println("	if both a and b are int and can go up to Integer.MAX_VALUE");
		System.out.println("	(a+b) / 2  OVERFLOWS  —> define a and b as long\n");
		
		int[] case_01 = new int[]{2126753390, 1702766719};
		set(case_01[1]);
		System.out.println("n = " + case_01[0] + " (first bad version= " + case_01[1] + ")");
		System.out.println("firstBadVersion() :" + firstBadVersion(case_01[0]));
		
		int[] case_02 = new int[]{2, 1};
		set(case_02[1]);
		System.out.println("\nn = " + case_02[0] + " (first bad version= " + case_02[1] + ")");
		System.out.println("firstBadVersion() :" + firstBadVersion(case_02[0]));
	}

}
