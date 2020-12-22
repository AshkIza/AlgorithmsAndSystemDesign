package leetcode.BitWise;


/*  Thins to remeber:
 *   -n = ~n + 1    (  n+1 = -~n)
 *   n & (n-1)   : unset rightmost set bit
 *   (n ^ 1 == 0) : true   n is even
 *   n ^ m : 0 for all similar bits, 1 for all oposite bits
 *   
 *   x is a bit:
 *   	x ^ 0 = x    x ^ 1 = ~x
 *      x | 0 = x    x | 1 = 1
 *      x & 0 = 0    x & 1 = x 
 * 
 * */
/*
 * 
 * https://www.techiedelight.com/bit-hacks-part-2-playing-kth-bit/
 * 
 * 
 * */
public class BitTricksMediumWebSite {
	
	
	/* TODO:
	 * two's complement:
	 * 	    https://medium.com/@jeanvillete/java-makes-use-of-the-twos-complement-for-representing-signed-numbers-31e421725c04
	 * 		https://sciencing.com/difference-between-scientific-engineering-notation-8567943.html
	 * Count set bits in an integer : https://www.geeksforgeeks.org/count-set-bits-in-an-integer/
	 * ENGLISH CHARACTER MANIPULATION: https://www.techiedelight.com/bit-hacks-part-4-playing-letters-english-alphabet/
	 * reverse bits in an integer: algorit and java method
	 * 		https://www.tutorialspoint.com/java-program-to-reverse-bits-of-a-positive-integer-number
			https://www.tutorialspoint.com/java/lang/integer_reverse.htm     (Integer.reverse(int))
	 * 
	 * Binary string to integer:
	 *   https://stackoverflow.com/questions/31365984/java-integer-parseint-for-32-bit-signed-binary-string-throws-numberformatexcep/31366257
	 *   Integer.parseInt("1011", 2); // 11
	 *   (throws excpetion on negatve integees - two's complements of negative binary strings)
	 * 
	 * */
	
	
	
	/* this should consider negative integers too (java uses two's complement for negative integers)
	 * */
	/*public static int binaryStringToInteger(String binaryString){
		
	}*/
	
	public static String integerToBinaryString(int num){
		int lenght = 0;
		boolean negative = num < 0;
		num = Math.abs(num);
		String binaryString = "";
		while( num >= 0 && lenght <32 ){
			int mode = negative ? (num + 1)%2 : num%2;
			binaryString = mode + binaryString;
			num /=2;
			lenght++;
		}
		if(negative){
			binaryString = incrementBinaryString(binaryString);
		}
		return binaryString;
	}
	
	static String incrementBinaryString(String input){
		char[] charArray = input.toCharArray();
		for(int i = charArray.length-1; i >=0; i--){
			if(charArray[i] == '0'){
				charArray[i]= '1';
				return new String(charArray);
			}else{
				charArray[i]= '0';
			}
		}
		return new String(charArray);
	}
	
	
	/* https://www.techiedelight.com/bit-hacks-part-2-playing-kth-bit/
	 * */
	public static class KthBithManipulation{
		// x is a bit representation
		
		// x & 1 = x  x & 0 = 0
		public static int turnOffKthBit(int n, int k){
			return n &  ~(1 << (k-1));
		}
		
		// x | 0 = x    x | 1 = 1
		public static int turnOnKthBit(int n, int k){
			return n |  (1 << (k-1));
		}
		
		public static boolean checkKthBitIsSet(int n, int k){
			return (n &  (1 << (k-1))) > 0;
		}
		
		// x ^ 0 = x    x ^ 1 = ~x
		public static int toggleKthBit(int n, int k){
			return n ^  (1 << (k-1));
		}
	}
	
	
	/* https://www.techiedelight.com/bit-hacks-part-3-playing-rightmost-set-bit-number/#P3
	 * */
	public static class RightmostSetBit{
		// x is a bit representation
		
		public static int unsetRightmostSetBit(int n){
			return n & (n-1);
		}
		
		public static boolean isPowerofTwo(int n){
			return (n & (n-1)) == 0;
		}
		
		public static int positionOfRighmostSetBit(int n){
			if(isOddNumber(n)){
				return 1;
			}
			int flag = n ^ (n & (n-1)); //all bits are set to 0 except rightmost Set bit
			return  (int) (Math.log(flag) / Math.log(2)) + 1;
			
		}
		
		public static boolean isOddNumber(int n){
			return (n & 1) > 0;
		}
		
		public static boolean isEvenNumber(int n){
			return (n & 1) == 0;
		}
	}
	
	
	
	
	
	
	
	/*https://www.techiedelight.com/bit-hacks-part-1-basic/
	 * */
	public static class BitHacksBasic{
		// x is a bit representation
		
		// x & 0 = 0
		static String oddOrEven(int n){
			if( (n & 1) == 0 ){
				return " is even";
			}else{ // if( (num & 1) > 0 )
				return " is odd";
			}
		}
		
		static boolean hasOppositeSigns(int n, int m){
			return (n ^ m) < 0;//32-th bit is the sign bit
		}
		
		// -x = ~x + 1   --->   -~x = x + 1
		static int addOne(int n){
			return -~n;
		}
		
		static void swapNumbersInPlace(int x, int y){
			x = x ^ y; //this returns 0 for all bits of similar value, returns 1 for all bits of opisite value
			y =  x ^ y;// reverts contents of y into contents of x, and then assign content of x  to y 
			x = x ^ y;//x is still the reverter; now y as x contents; apply reverter to x contents gives us y contents, then put it in x
			System.out.print("x : " + x + ", y : " + y);
		}
	}
	
	public static int passByValue(int x){
		x = x * 10;
		return x;
	}
	
	public static Integer passByReference(ObjectValue x){
		x.value = x.value * 10;
		return x.value;
	}
	
	public static class ObjectValue{
		public int value;
		
		ObjectValue(int val){
			value = val;
		}
		
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		System.out.println(" 5  ~5+1 : " + (5) + " " + (~5 + 1)); 
		System.out.println("\n  ~0 is " + (~0) + " (NOT 1 since this in decimal integer representation not binary),  \n  ~1 is " + (~1) + " (NOT 0 since this in decimal integer representation not binary)"); 

		
		System.out.println("\nString to char Array : str.toCharArray()  /  char Array to String : new String(charArray): "); 
		String str = "12345";
		char[] strChar = str.toCharArray();
		System.out.println(" strChar.toString() : " + strChar.toString() + " charArray.toString() returns object representation @hexadecimal"); 
		System.out.println(" new String(strChar)) : " + (new String(strChar)) + " always use : new String(char[] charArray)"); 
		/*  https://www.techiedelight.com/convert-char-array-string-java/
		 *  1) new String(char[])
		 *  char[] chars = {'T', 'e', 'c', 'h', 'i', 'e', ' ', 
						'D', 'e', 'l', 'i', 'g', 'h', 't'};
			String string = new String(chars);
			
			1) String.valueOf(char[])
				char[] chars = {'T', 'e', 'c', 'h', 'i', 'e', ' ',
						'D', 'e', 'l', 'i', 'g', 'h', 't'};
				String string = String.copyValueOf(chars);
		
		 * */
		

		System.out.println("\nintegerToBinaryString(-5) is : " + integerToBinaryString(-5) + 
				" ; Integer.toBinaryString(-5) : " + Integer.toBinaryString(-5)); 
		System.out.println("integerToBinaryString(-134) is : " + integerToBinaryString(-134) + 
				" ; Integer.toBinaryString(-134) : " + Integer.toBinaryString(-134)); 
		System.out.println("integerToBinaryString(-23456) is : " + integerToBinaryString(-23456) + 
				" ; Integer.toBinaryString(-23456) : " + Integer.toBinaryString(-23456)); 
		System.out.println("integerToBinaryString(5) is : " + integerToBinaryString(5) + 
				" ; Integer.toBinaryString(5) : " + Integer.toBinaryString(5)); 	
		System.out.println("integerToBinaryString(0) is : " + integerToBinaryString(0) + 
				" ; Integer.toBinaryString(0) : " + Integer.toBinaryString(0)); 
		
		System.out.println("\n                   20 : " + integerToBinaryString(20)); 
		System.out.println(" turnOffKthBit(20, 3) : " +  integerToBinaryString(KthBithManipulation.turnOffKthBit(20, 3))); 

		System.out.println("\n                   20 : " + integerToBinaryString(20)); 
		System.out.println(" turnOnKthBit(20, 15) : " +  integerToBinaryString(KthBithManipulation.turnOnKthBit(20, 15))); 


		System.out.println("\n                   20 : " + integerToBinaryString(20)); 
		System.out.println(" checkKthBitIsSet(20, 3) : " +  KthBithManipulation.checkKthBitIsSet(20, 3)); 
		System.out.println(" checkKthBitIsSet(20, 4) : " +  KthBithManipulation.checkKthBitIsSet(20, 4)); 

		System.out.println("\n                  20 : " + integerToBinaryString(20)); 
		System.out.println(" toggleKthBit(20, 1) : " +  integerToBinaryString(KthBithManipulation.toggleKthBit(20, 1))); 
		System.out.println(" toggleKthBit(20, 3) : " +  integerToBinaryString(KthBithManipulation.toggleKthBit(20, 3))); 

		System.out.println("\n"); 
		System.out.println("\n                       20 : " + integerToBinaryString(20)); 
		System.out.println(" unsetRightmostSetBit(20) : " +  integerToBinaryString(RightmostSetBit.unsetRightmostSetBit(20))); 

		
		System.out.println("\n                  20 : " + integerToBinaryString(20)); 
		System.out.println(" isPowerofTwo(20) : " +  RightmostSetBit.isPowerofTwo(20)); 
		System.out.println("                    16 : " + integerToBinaryString(16)); 
		System.out.println(" isPowerofTwo(16) : " +  RightmostSetBit.isPowerofTwo(16)); 
		
		System.out.println("\n                       36 : " + integerToBinaryString(100)); 
		System.out.println(" positionOfRighmostSetBit(36) : " +  RightmostSetBit.positionOfRighmostSetBit(100)); 
		System.out.println("   Math.log() is log on natural base e; log2(n) = log(n)/log(2)  " + integerToBinaryString(100)); 
		System.out.println("   https://www.techiedelight.com/calculate-log-base-2-in-java/ "); 
		System.out.println("    (int) Math.log(8) : " + (int) Math.log(8) + " ;  (int) (Math.log(8)/Math.log(2)) : " + (int) (Math.log(8)/Math.log(2))  ); 

		System.out.println("\n");
		System.out.println("oddOrEven((20) : " +  BitHacksBasic.oddOrEven((20))); 
		System.out.println("oddOrEven((-15) : " +  BitHacksBasic.oddOrEven((-15))); 
		
		System.out.println(" \nhasOppositeSigns(15, -200) : " +  BitHacksBasic.hasOppositeSigns(15, -200)); 
		System.out.println("hasOppositeSigns(-9, -200) : " +  BitHacksBasic.hasOppositeSigns(-9, -200)); 
		
		System.out.println(" \naddOne(14) : " +  BitHacksBasic.addOne(14)); 
		System.out.println("addOne(-200) : " +  BitHacksBasic.addOne(-200)); 
		System.out.println("addOne(0) : " +  BitHacksBasic.addOne(0)); 
		
		
		
		System.out.println("Java primitives vs Java Objects when passing to functions as arguments.  ");
		System.out.println(" Java primitives are passed by value (any change within the function does NOT affect the value outside the function.\n   ALWAYS return the primitives if you want the changes to get outside.  ");
		System.out.println(" Java Objects are passed by reference (any chnage within function DOES affect the value outside the function");
		int x = 39;
		int y = 68;
		System.out.println(" x : " +  x + ", y :" + y); 
		
		System.out.print(" swapNumbersInPlace --> ");
		BitHacksBasic.swapNumbersInPlace(x, y);
		
		System.out.println(" a : ");
		int a = 10;
		ObjectValue b = new ObjectValue(10);
		System.out.println("\n a : " + a + ", b :" + b.value );
		int returnedA = BitTricksMediumWebSite.passByValue(a);
		Integer returnedB = BitTricksMediumWebSite.passByReference(b);
		System.out.println(" a : " + a + ", b :" + b.value);

		System.out.println(" returnedA : " + returnedA + ", returnedB :" + returnedB);


		 

	}

}
