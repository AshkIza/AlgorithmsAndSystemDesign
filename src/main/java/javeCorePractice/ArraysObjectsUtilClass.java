package javeCorePractice;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * Arrays class in Java
	https://www.geeksforgeeks.org/array-class-in-java/
 * 
 * Java.util.Objects class in Java
	https://www.geeksforgeeks.org/java-util-objects-class-java/
 * 
 * */
public class ArraysObjectsUtilClass {

	public static void main(String[] args) {
        System.out.println("Arrays.asList(T...array) " ); 
		String[] stringArray = new String[] {"Monday", "Tuesday", "Wednesday"};
		List<String> stringList = Arrays.asList(stringArray);
        System.out.println("String Array as List: "  + stringList); 

        int intArr[] = { 10, 20, 15, 22, 35 }; 
        List<int[]> IntList = Arrays.asList(intArr);
        System.out.println("Integer Array as List: "  + IntList); 
        
        System.out.println("\nstatic int[] copyOf(int[] original, int newLength) " ); 
        int arrayCoppied[] = Arrays.copyOf(intArr, intArr.length-2);
        System.out.println("intArr[] "  + Arrays.toString(intArr)); 
        System.out.println("int arrayCoppied[] = Arrays.copyOf(intArr, intArr.length-2);) " ); 
        System.out.println("arrayCoppied  "  + Arrays.toString(arrayCoppied)); 
        
        System.out.println("\nfill(originalArray, fillValue) " ); 
        float original[] = new float[]{4f,0.77f,-8.987f, 45f};
        System.out.println("original "  + Arrays.toString(original)); 
        Arrays.fill(original, 9.999f);
        System.out.println("Arrays.fill(original, 9.999f) " ); 
        System.out.println("original " + Arrays.toString(original)); 
        
        System.out.println("\nequals(array1, array2)) " ); 
        System.out.println("Arrays.equals(new int[] {1,3,2}, new int[] {1,3,2}) "  + Arrays.equals(new int[] {1,3,2}, new int[] {1,3,2})); 
        System.out.println("Arrays.equals(new int[] {1,3,2}, new int[] {1,2}) "  + Arrays.equals(new int[] {1,3,2}, new int[] {1,2})); 
        System.out.println("Arrays.equals(new int[] {1,3,2}, new int[] {1,2,3}) "  + Arrays.equals(new int[] {1,3,2}, new int[] {1,2,3})); 

        System.out.println("\nstatic boolean deepEquals(Object[] a1, Object[] a2) : for 2-dimentional arrays" ); 
        System.out.println("Arrays.deepEquals(new String[] {'ab', 'bc'}, new String[] {'ab', 'bc', 'cd'})) "  + Arrays.deepEquals(new String[] {"ab", "bc"}, new String[] {"ab", "bc", "cd"})); 

        System.out.println("\nhashCode(originalArray) / deepHashCode(Object[] a)) " ); 
        Integer[] integerArray =new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)};
        System.out.println("Integer[] integerArray =new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)} " ); 
        System.out.println("Arrays.hashCode(integerArray) " + Arrays.hashCode(integerArray)); 
        System.out.println("Arrays.deepHashCode(integerArray) " + Arrays.deepHashCode(integerArray)); 
        System.out.println("Arrays.hashCode(new int[]{1,2,3}) " + Arrays.hashCode(new int[]{1,2,3})); 
        
        System.out.println("\ntoString(originalArray) / deepToStringe(Object[] a)) " ); 
        System.out.println("char[] charArray  = new char[] {'a', 's', 'h'}; " ); 
        char[] charArray  = new char[] {'a', 's', 'h'};
        System.out.println("Arrays.toString(charArray) "  + Arrays.toString(charArray)); 
        Character[] characterArray = new Character[4];
        Arrays.fill(characterArray, Character.valueOf('s'));
        System.out.println("Arrays.fill(characterArray, Character.valueOf('s'))" ); 
        System.out.println("Arrays.toString(characterArray) : " + Arrays.toString(characterArray) + "\n" +
        		"Arrays.deepToString(characterArray) : " + Arrays.deepToString(characterArray)); 
        
        System.out.println("\ndouble twoDimentionalArray[][] = { { 10.1, 20.4, 15, 22.444, 35.01111 } }" ); 
        double twoDimentionalArray[][] = { { 10.1, 20.4, 15, 22.444, 35.01111 } }; 
        System.out.println("Arrays.toString(twoDimentionalArray) : "  + Arrays.toString(twoDimentionalArray) + "\n" +
        				"Arrays.deepToString(twoDimentionalArray) : " +  Arrays.deepToString(twoDimentionalArray)); 
        System.out.println("use deepHashCode deepToString deepEquals for 2-dimentioanl Arrays" ); 

        System.out.println("\n<T> void sort(T[] a, int fromIndex, int toIndex, Comparator< super T> c)" );
        System.out.println("float[] floatArray = new float[]{-11.222f, 200.1f, 43, 000.1f}" );
        float[] floatArray = new float[]{-11.222f, 200.1f, 43, 000.1f};
        Arrays.sort(floatArray);
        System.out.println("Arrays.sort(floatArray)");
        System.out.println("floatArray_sorted : " + Arrays.toString(floatArray));

        System.out.println("Double doubleArray[] = new Double[]{Double.valueOf(21010), Double.valueOf(9.9), Double.valueOf(-3.0001)}" );
        Double doubleArray[] = new Double[]{Double.valueOf(21010), Double.valueOf(9.9), Double.valueOf(-3.0001)};
        Arrays.sort(doubleArray, 1, 3);
        System.out.println("Arrays.sort(doubleArray, 1, 3)");
        System.out.println("doubleArray_sorted : " + Arrays.toString(doubleArray));
        
        
        System.out.println("\nstatic int binarySearch(data_type arr, data_type key )");
        System.out.println("array should be already sorted");
        float[] floatArr = {10.2f,15.1f,2.2f,3.5f}; 
        Arrays.sort(floatArr); 
        System.out.println("float[] floatArr = {10.2f,15.1f,2.2f,3.5f}");
        System.out.println("Arrays.sort(floatArr)");
        System.out.println("Arrays.binarySearch(floatArr, 2.2f) : " + Arrays.binarySearch(floatArr, 2.2f));
        
        // https://www.baeldung.com/java-arrays-sort-vs-parallelsort
        System.out.println("\nArrays.sort() vs Arrays.parallelSort()");
        System.out.println("The parallelSort() is functionally different. Unlike sort(), which sorts data sequentially using a single thread, "
        		+ "\nit uses a parallel sort-merge sorting algorithm. It breaks the array into sub-arrays that are themselves sorted and then merged.\n" + 
        		"For executing parallel tasks it uses the ForkJoin pool.");
        int[] array = { 10, 4, 6, 2, 1, 9, 7, 8, 3, 5 };
        Arrays.sort(array);
        Arrays.parallelSort(array);  
        System.out.println("	int[] array = { 10, 4, 6, 2, 1, 9, 7, 8, 3, 5 };");
        System.out.println("	Arrays.sort(array);       Arrays.parallelSort(array);  ");
        System.out.println("Based on performance results, we can conclude that"
        		+ "\n	 parallelSort() may be a better choice when we have a large dataset to sort (n > 1000)."
        		+ " \n	However, in the case of smaller size arrays, it's better to go with sort() since it offers better performance");
        
        //https://stackoverflow.com/questions/1694751/java-array-sort-descending
        System.out.println("\nArray Sort descending?");
        System.out.println(" Arrays.sort(a, Collections.reverseOrder());");
        System.out.println(" NOT for primitive arrays (int[]) - > Arrays.sort() cannot be used directly to sort primitive arrays in descending order. "
        		+ "\n		If you try to call the Arrays.sort() method by passing reverse Comparator defined by Collections.reverseOrder() , "
        		+ "\n		it will throw the error");
        System.out.println(" for 'primitive arrays', (sort descending) -> convert to Stream<Integer> using mapToObj then use .sorted(Comparator.reverseOrder())");
        
        //https://www.geeksforgeeks.org/list-array-java/
        System.out.println("\nList --> array ");
        System.out.println("List<String> list = new LinkedList<String>();");
        List<String> list = new LinkedList<String>(); 
        list.add("Geeks"); 
        list.add("for"); 
        list.add("Geeks"); 
        list.add("Practice"); 
        String[] stringArrayFromList = list.toArray(new String[0]);
        String[] stringArrayFromStream = list.stream().toArray(String[] ::new); 
        System.out.println("String[] stringArrayFromList = list.toArray(new String[0])");
        System.out.println("String[] stringArrayFromStream = list.stream().toArray(String[] ::new)");
        
	}

}
