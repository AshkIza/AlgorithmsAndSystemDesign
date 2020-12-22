package leetcode.Math;

/*  Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
	Example 1:
	
	Input: "A"
	Output: 1
	Example 2:
	
	Input: "AB"
	Output: 28
	Example 3:
	
	Input: "ZY"
	Output: 701
 * 
 * */
public class ExcelSheetColumnNumber {
	
	
	 /* "XYZ" ---->
	  * 
	  * 26^2 * num(X) + 26^1 * num(Y) + num(Z)
	  * 
	  * */
	 public int titleToNumber(String s) {
	        if(s.length()==0){
	            return 0;
	        }
	        int N = s.length();
	        int sum = 0;
	        for(int index = 0; index < N; index++){
	            char ch = s.charAt(index);
	            int num = num(ch);
	            int pow = N - (index+1);
	            sum += Math.pow(26, pow) * num;
	        }
	        return sum;
	    }
	    
	    private int num(char ch){
	        return ((int) (ch - 'A')) + 1;
	    }

	public static void main(String[] args) {
		ExcelSheetColumnNumber excelToNumber = new ExcelSheetColumnNumber();
		System.out.println("excelToNumber.titleToNumber(A) : " + excelToNumber.titleToNumber("A"));
		System.out.println("excelToNumber.titleToNumber(B) : " + excelToNumber.titleToNumber("B"));
		System.out.println("excelToNumber.titleToNumber(C) : " + excelToNumber.titleToNumber("C"));
		System.out.println("excelToNumber.titleToNumber(Z) : " + excelToNumber.titleToNumber("Z"));
		System.out.println("excelToNumber.titleToNumber(AA) : " + excelToNumber.titleToNumber("AA"));
		System.out.println("excelToNumber.titleToNumber(ZY) : " + excelToNumber.titleToNumber("ZY"));
	}

}
