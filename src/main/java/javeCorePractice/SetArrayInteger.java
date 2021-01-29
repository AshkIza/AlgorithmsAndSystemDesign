package javeCorePractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetArrayInteger {

	public static void main(String[] args) {
		// https://www.geeksforgeeks.org/list-get-method-in-java-with-examples/
		System.out.println( "list.get(index) vs list.remove(index)");
		List<String> list = new ArrayList<>();
		list.add( "First");
		list.add("Second");
		list.add("Third");
		list.add("Forth");
		list.add("Fifth");
		System.out.println( "  list.get(0) : " + list.get(0));
		System.out.println( "  list.get(3) : " + list.get(3));
		System.out.println( "  list.remove(3) : " + list.remove(3));
		System.out.println( "  list.get(3) : " + list.get(3));
		
		//https://www.geeksforgeeks.org/hashmap-remove-method-in-java/
		System.out.println( "\nmap.get(Object key) vs map.remove(Object key)");
		Map<String, Integer> map = new HashMap<>();
		map.put( "First", 1);
		map.put("Second", 2);
		map.put("Third", 3);
		map.put("Forth", 4);
		map.put("Fifth", 5);
		System.out.println( "  map.get('First') : " + map.get("First"));
		System.out.println( "  map.get('Third') : " + map.get("Third"));
		System.out.println( "  map.remove('Third') : " + map.remove("Third"));
		System.out.println( "  map.get('Third') : " + map.get("Third"));

		

		System.out.println( "String to/from integer : Integer.toString() Vs Integer.parseInt( ' ')");

		// Integer to String
		/*Integer.toString(int i) vs String.valueOf(int i)
		 * https://stackoverflow.com/questions/3335737/integer-tostringint-i-vs-string-valueofint-i
		 * 
			In String type we have several method valueOf
			
			static String   valueOf(boolean b) 
			static String   valueOf(char c) 
			static String   valueOf(char[] data) 
			static String   valueOf(char[] data, int offset, int count) 
			static String   valueOf(double d) 
			static String   valueOf(float f) 
			static String   valueOf(int i) 
			static String   valueOf(long l) 
			static String   valueOf(Object obj) 
			As we can see those method are capable to resolve all kind of numbers
			
			every implementation of specific method like you have presented: So for integers we have
			
			Integer.toString(int i)
			for double
			
			Double.toString(double d)
		 * 
		 * */ 
		System.out.println( " Integer.toString(15) : " + Integer.toString(15));
		System.out.println( " String.valueOf(15) : " + String.valueOf(15));
		
		
		// String to Integer
		System.out.println( " Integer.parseInt('15')) : " + Integer.parseInt("15"));


		System.out.println( " \nBinary String to/from Integer : Integer.toBinaryString() Vs Integer.parseInt(' ', radix)");

		
		System.out.println( " Integer.toBinaryString(15) : " + Integer.toBinaryString(15));
		System.out.println( " Integer.parseInt('1111', 2)) : " + Integer.parseInt("1111", 2));
		
		System.out.println( " Integer.toBinaryString(-130922) : " + Integer.toBinaryString(-130922));
		System.out.println( " Integer.parseInt('1111', 2)) : " + Integer.parseInt("-111111111111100000000010010110", 2));



		// Collections.sort() versus list.sort versys Arrays.sort
		// https://stackoverflow.com/questions/34910841/difference-between-collection-sortlist-and-list-sort
		System.out.println("\nCollections.sort() versus list.sort() versys Arrays.sort() : ");
		List<Integer> listInteger = Arrays.asList(1395, 12, 4,-60, 70, -21);
		System.out.println("	listInteger : " + listInteger);
		System.out.println("	listInteger.sort(null) / Collections.sort(listInteger)");
		listInteger.sort(null);
		Collections.sort(listInteger);
		System.out.println("	listInteger : " + listInteger);
		System.out.println("   Collections.sort delegates to List.sort. "
				+ "\n   The method List.sort(comparator) that you are refering to was introduced in Java 8, whereas the utility method Collections.sort has been there since Java 1.2. ");

		int[] intarray = new int[] {56, 90, -4, 2, 54-3,-90};
		Arrays.sort(intarray);
		System.out.println("int[] intarray : Arrays.sort(intarray)" );

	}

}
