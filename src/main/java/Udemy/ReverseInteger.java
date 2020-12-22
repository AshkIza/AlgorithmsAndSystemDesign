package Udemy;

public class ReverseInteger {
	
	//java "integer" arithmetics (returned value also integer):
	// int digit = number % 10;  number /= 10;
	//  -5 % 10 = -5 
	//  -5 / 10 = 0
	//  -123 /10 = -12
	public static int reverse(int number) {
		int reversed = 0;
		while (number != 0 ) {
			int digit = number % 10;
			number /= 10;
			reversed = digit + 10 * reversed;
		}
		return reversed;
	}

	public static void main(String[] args) {
		int[] tests = new int[] {-1234, -200, -90, -15, -5, 0, 3, 15, 500, 981, 2345};
		for(int test : tests) {
			System.out.println("test: " + test + " ; revesred: " + reverse(test));
		}
	}
	
    /*Different ways for Integer to String Conversions In Java
	https://www.geeksforgeeks.org/different-ways-for-integer-to-string-conversions-in-java/
	int a = -1234; 
	String str1 = Integer.toString(a); 
	String str2 = String.valueOf(a); 
	
	Java – Convert String to int
	https://www.mkyong.com/java/java-convert-string-to-int/
	String number = "10";
	int result = Integer.parseInt(number);
	Integer result = Integer.valueOf(number);*/
}
