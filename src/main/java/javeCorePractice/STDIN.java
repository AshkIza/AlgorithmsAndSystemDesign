package javeCorePractice;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/*   https://www.techiedelight.com/read-string-standard-input-java/
 * READ String from STDID - standard output (System.in)
 * System.in is InputStream  (public final static InputStream in = null;)
 * Typically this stream corresponds to keyboard input or another input source specified by the host environment or user.

 * Two ways converting InputStream (byte stream) to char stream
 * 1- using Scanner (java.util.Scanner)
 *  Scanner scanner = new Scanner(System.in)
 *  String line = scanner.nextLine();
 *  
 * 2- using InputStreamReader & BufferedReader
 * 	  An InputStreamReader is a bridge from byte streams to character streams:
 * 		 It reads bytes and decodes them into characters using a specified charset
 *    The  BufferedReader class, java.io.BufferedReader, provides buffering for your Java Reader instances. 
 *    	Buffering can speed up IO quite a bit. Rather than read one character at a time from the underlying Reader, 
 *    	the Java BufferedReader reads a larger block (array) at a time. 
 *    	This is typically much faster, especially for disk access and larger data amounts.
 *  BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
 *  String line = br.readLine()
 * 
 * */
public class STDIN {

	
	public static void main(String[] args) {
		//1- using Scanner (java.util.Scanner)
		Scanner scanner = new Scanner(System.in);	
	    String nextLine = scanner.nextLine();
	    
	    System.out.println(tokenizeStringSplit(nextLine));
	    System.out.println(tokenizeStringTokenizer(nextLine));
		
		//2- using InputStreamReader & BufferedReader
	     List<String> tokens = new ArrayList<>();
		 BufferedReader br = null; 
	     try {
				br = new BufferedReader(new InputStreamReader(System.in));
				String line = br.readLine();
				System.out.println(tokenizeStringTokenizer(line));
			}
			catch (IOException e) {
				System.out.println(e);
			}
	}
	
    /*Two ways to tokenize a String
     * https://stackoverflow.com/questions/691184/scanner-vs-stringtokenizer-vs-string-split#:~:text=split()%20compiles%20a%20Regular,to%20operate%20on%20a%20String.&text=One%20important%20difference%20is%20that,but%20StringTokenizer%20never%20does%20it.
     * 1- using string.split()
     *    https://www.geeksforgeeks.org/split-string-java-examples/
     *    
     * 2- using StringTokenizer(string, delim)
     * 
     * StringTokenizer was always there. It is the fastest of all.
     * split came to existence on JDK 1.4. Slower than tokenizer but easier to use,
     * Scanner came to be on JDK 1.5. It is the most flexible
     */
    static List<String> tokenizeStringSplit(String nextLine) {
   	 String[] tokensUsingStringSplit =  nextLine.split(" ");
   	 return Arrays.asList(tokensUsingStringSplit);
    }
    
     static List<String> tokenizeStringTokenizer(String nextLine) {
    	List<String> tokensUsingStringTokenizer = new ArrayList<>();
 	    StringTokenizer tokenizer= new StringTokenizer(nextLine, "-");
 	    while (tokenizer.hasMoreElements()) {
 	    	 tokensUsingStringTokenizer.add(tokenizer.nextToken());
 	    }
 	    return tokensUsingStringTokenizer;
    }
    
}
