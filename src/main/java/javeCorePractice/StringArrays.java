package javeCorePractice;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StringArrays {

	public static void main(String[] args) {
		/* Java String substring() 
		 * https://www.javatpoint.com/java-string-substring
		 * 
		 *  public String substring(int startIndex)  
				and  
			public String substring(int startIndex, int endIndex)  
			
				startIndex : starting index is inclusive
				endIndex :   ending index is exclusive
		 * */
		 String s1="Javatpoint";    
	     System.out.println("s1.substring(0) : " + s1.substring(0));  
	     System.out.println("s1.substring(1) : " + s1.substring(1));  
	     System.out.println("s1.substring(1,2) : " + s1.substring(1,2));
	     System.out.println("s1.substring(1,s1.length()) : " + s1.substring(1,s1.length()));  
	     
	     /*  Java String indexOf()
	      * https://www.javatpoint.com/java-string-indexof
	      * 
	      * The java string indexOf() method returns index of given character value or substring.
	      *  If it is not found, it returns -1
	      *  
	      *  int indexOf(int ch)	:        returns index position for the given char value
             int indexOf(String substring)	 returns index position for the given substring
	      * */
	      String s2="this is index of example"; 
	      System.out.println("\nstr.indexOf()   passing substring / passing char");
		  System.out.println("s2.indexOf('is') :" + s2.indexOf("is") + ", s2.indexOf('index') : " + s2.indexOf("index"));//2 8  
		  System.out.println("s2.indexOf('e') :" + s2.indexOf('e') + ", s2.indexOf('p') : " + s2.indexOf('p'));//2 8  
	      System.out.println("\n");


		  /*   byte[] array <--> String
		   * https://howtodoinjava.com/array/convert-byte-array-string-vice-versa/
		   * 
		   * 1- String.getBytes String(bytes[])
		   * 2- Java8 Base64.getEncoder()
		   *          Base64.getEncoder()
		   *    (string to/from byte[] is called encoding/decoding)
		   * 
		   * 
		   * */
		 //Convert byte[] to String
	      System.out.println("new String(byte bytes[])  /  String.getBytes()");
		  byte[] byteArray = "JavaS7".getBytes();
          String byteArrayString = new String(byteArray);
	      System.out.println(byteArrayString);
	      
	      /*  How to fill a String with a character using Java?
	       *  http://helpdesk.objects.com.au/java/how-to-fill-a-string-with-a-character-using-java
	       * char[] fill = new char[9];
	       * 
	       * */
	      int N = 7;
	      char[] charArrayN = new char[N];
	      Arrays.fill(charArrayN, 'x');
	      String stringFromCharArray = new String(charArrayN);
	      System.out.println("char[] charArrayN = new char[N]");
	      System.out.println("Arrays.fill(charArrayN, 'x')");
	      System.out.println("new String(charArrayN)");
	      System.out.println(stringFromCharArray);
	      
	      /*  Arrays.sort()
	       * sort() method is a java.util.Arrays class method.
	            public static void sort(int[] arr, int from_Index, int to_Index)
		            arr - the array to be sorted
					from_Index - the index of the first element, inclusive, to be sorted
					to_Index - the index of the last element, 'exclusive', to be sorted
				 This method doesn't return any value.
	       * 
	       * */
	      System.out.println("\nArrays.sort() :");
	      int[] intArray = new int[]{6,0,-1,20, 300, 2};
	      System.out.println("unsorted array : " + Arrays.toString(intArray));
	      Arrays.sort(intArray);
	      System.out.println("Arrays.sort(intArray): " + Arrays.toString(intArray));
	      
	      char[] charArray = new char[] {'o', 'a', 'l', 'j', 'z', 'b'};
	      System.out.println("unsorted char array : " + Arrays.toString(charArray));
	      Arrays.sort(charArray, 0, 3);
	      System.out.println("Arrays.sort(charArray, 0, 3): " + Arrays.toString(charArray));  
	           
	      System.out.println("\nLong[] LongObjectArray = new Long[]{6576576576666L, 10000L, 44L, 98089808998999898L}: ");
	      Long[] LongObjectArray = new Long[]{6576576576666L, 10000L, 44L, 98089808998999898L};
	      System.out.println("LongObjectArray : " + Arrays.toString(LongObjectArray));
	      Arrays.sort(LongObjectArray);
	      System.out.println("Arrays.sort(LongObjectArray) \n (CAN NOT use Collections.reverseOrder(): \n    " + Arrays.toString(LongObjectArray));

	      
	      
	      /*  initializing long and float in java
	       * https://stackoverflow.com/questions/6834037/initialize-a-long-in-java
	       * 
	       * long i = 12345678910; --> shows  "The literal 12345678910 of type int is out of range" error.
	       * 1) How do I initialize the long with the value 12345678910?
	       * 			You should add L: 
	       * 			long i = 12345678910L;.
			2) Are all numeric literals by default of type int?
					Yes
	       * 
	       * 
	       *   float variables initialization java:
	       *   https://stackoverflow.com/questions/18425488/float-variables-initialization-java
	       *   
	       *   float Gamma = 20.0; --> gives error (change it to dooble / cast it to float)
	       *    	Floating-point literals are considered doubles unless you specify that they're just floats. 
	       *    	(Similarly, integer literals are ints unless specified otherwise.) 
	       *    		Append the letter f to the number to make it a float:

					float density = 20.0f;
					float varName = 10.0f; or float varName = (float)10.0;
	       * 
	       * */
	      System.out.println("\ninteger / long     float / double ");
	      System.out.println("  3232  /979799L    2.3f /  2345.898987 ");
	      System.out.println("Floating-point literals are considered doubles unless you specify that they're just floats. \n "
	      		+ " (Similarly, integer literals are ints unless specified otherwise.)  ");


	      long smalNumber = 45;
	      long largenumber = 98798472984343L;
	      float integerfloat = 3;
	      float nonIntegerfloat = 3.1f;
	      System.out.println(" long smalNumber = 45 / long largenumber = 98798472984343L:\n   " 
	    		  			+ smalNumber + "  /  " + largenumber);

	      double integerNumber = 77;
	      double nonIntegerNumbe = 77.9;
	      double BigdoubleNumber = 7497348972398748927498727.9;
	      System.out.println(" double integerNumber = 77 / double nonIntegerNumbe = 77.9 / double BigdoubleNumber = 7497348972398748927498727.9 :\n   " 
		  			+ integerNumber + "  /  " + nonIntegerNumbe + " / "+ BigdoubleNumber);


	      /* https://www.adam-bien.com/roller/abien/entry/java_8_streaming_a_string
	       * */
	      System.out.println("\nString --> IntStream :  IntStream chars()");
	      System.out.println("\"String.chars()\".chars().forEach(c -> System.out.println((char) c))");
	      "String.chars()".chars().forEach(c -> System.out.println((char) c));


	}

}
