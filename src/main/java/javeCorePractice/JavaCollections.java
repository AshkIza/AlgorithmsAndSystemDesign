package javeCorePractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaCollections {

	public static void main(String[] args) {
        /* remove an element from ArrayList / LinkedList
         *   List interface method (both ArrayList and LinkedList implement this)
         *     1. By using remove() methods : (just implemented in LinkedList)
			   2. remove(int index) : Accept index of object to be removed.
			   3. remove(Obejct obj) : Accept object to be removed.
			   
			   https://www.geeksforgeeks.org/remove-element-arraylist-java/
			   https://www.geeksforgeeks.org/linkedlist-remove-method-in-java/
         * */
		
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		arrayList.add(4);
		System.out.println(" arrayList :" + Arrays.toString(arrayList.toArray()));
		System.out.println(" arrayList.remove() NOT IMPLEMENTED in ArrayList" );
		arrayList.remove(1);
		System.out.println(" arrayList.remove(1) :" );
		System.out.println(" arrayList :" + Arrays.toString(arrayList.toArray()));
		arrayList.remove(new Integer(4));
		System.out.println(" arrayList.remove(new Integer(4)) :" );
		System.out.println(" arrayList :" + Arrays.toString(arrayList.toArray()));
		System.out.println("\n" );

		
		LinkedList<String> linkedList = new LinkedList<>();
		linkedList.add("One");
		linkedList.add("Two");
		linkedList.add("Tree");
		linkedList.add("Four");
		System.out.println(" linkedList :" + Arrays.toString(linkedList.toArray()));
		linkedList.remove();//returns the head of the list or the element present at the head of the list.
		System.out.println(" linkedList.remove()  IMPLEMENTED in LinkedList (returns the head of the list)");
		System.out.println(" linkedList :" + Arrays.toString(linkedList.toArray()));
		linkedList.remove(1);
		System.out.println(" linkedList.remove(1) :" );
		System.out.println(" linkedList :" + Arrays.toString(linkedList.toArray()));
		linkedList.remove(new String("Four"));
		System.out.println(" linkedList.remove(new String('Four') :" );
		System.out.println(" linkedList :" + Arrays.toString(linkedList.toArray()));
		System.out.println("\n" );

		
		/*  ==> LinkedList remove() = removeFirst()
 			==> LinkedList    add() = addLast()
    			Queue Implementation = Deque Implementation
		 * */
		System.out.println("LinkedList remove() = removeFirst() AND removeLast()" );
		System.out.println("LinkedList add() = addLast() addFirst() AND addLast" );
		LinkedList<Integer> llist = new LinkedList<>();
		llist.add(1);
		llist.add(2);
		llist.add(3);
		llist.add(4);
		llist.addLast(5);
		llist.addFirst(6); 
		System.out.println("llist.add(4)" );
		System.out.println("llist.addLast(5)" );
		System.out.println("llist.addFirst(6)" );
		System.out.println(" llist : " + Arrays.toString(llist.toArray()));
		System.out.println("llist.remove()" );
		llist.remove();
		System.out.println(" llist : " + Arrays.toString(llist.toArray()));
		System.out.println("llist.removeFirst()" );
		llist.removeFirst();
		System.out.println(" llist : " + Arrays.toString(llist.toArray()));
		System.out.println("llist.removeLast()" );
		llist.removeLast();
		System.out.println(" llist : " + Arrays.toString(llist.toArray()));
		System.out.println(" ==> LinkedList remove() = removeFirst()" );
		System.out.println(" ==> LinkedList    add() = addLast()" );
		System.out.println("    Queue Implementation = Deque Implementation" );
		System.out.println("\n" );


        /*  List indexOf() Method in Java
         *   https://www.geeksforgeeks.org/list-indexof-method-in-java-with-examples/
         *   
         *   public int indexOf(Object o)
         * */
		System.out.println("List.indexOf() (LinkedList AND ArrayList)" );
		List<Integer> al = new LinkedList<>(); 
		al.add(1); 
		al.add(3); 
		al.add(5); 
		al.add(7); 
		System.out.println(" al : " + Arrays.toString(al.toArray()));
		System.out.println(" al.indexOf(3) : " + al.indexOf(3));
		System.out.println("\n");


        System.out.println("Arrays.asList() /  Object[] list.toArray()"); 
		/*  List <--> Array  
		 * Arrays.asList(Object...) : 
		 * This method acts as bridge between array-based and collection-based APIs,
		 *  in combination with Collection.toArray(). The returned list is serializable and implements RandomAccess.
		 *  https://www.geeksforgeeks.org/arrays-aslist-method-in-java-with-examples/
		 *  
		 *  Object[] List.toArray()
		 *  toArray() method of List interface returns an array containing all the elements present in the list in proper order
		 *  https://www.javatpoint.com/java-list-toarray-method

		 * 
		 * */
		String[] stringArray = new String[] { "A", "B", "C", "D" };
		List<String> stringList = Arrays.asList(stringArray);
        System.out.println("Arrays.asList(new String[] { 'A','B','C','D' })"); 
        System.out.println("]nThe stringList is: " + stringList); 
        List<Integer> integerList = Arrays.asList(new Integer[] { 10, 20, 30, 40 }); 
        System.out.println("List<Integer> integerList = Arrays.asList(new Integer[] { 10, 20, 30, 40 }) "); 
        System.out.println("The integerList is: " + integerList); 
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(10);
        intList.add(20);
        intList.add(30);
        intList.add(40);
        Object[] objectArray =  intList.toArray();
        System.out.println("\nList<Integer> intList = new ArrayList<Integer>()"); 
        System.out.println("Object[] objectArray =  intList.toArray()"); 
        System.out.println("objectArray[2] : " + objectArray[2].toString()); 
        
        Integer[] intArray = new Integer[intList.size()];
        intList.toArray(intArray);
        System.out.println(" Integer[] intArray = new Integer[intList.size()]");
        System.out.println(" intList.toArray(intArray) : " + Arrays.asList(intArray)); 

       Set<String> daysOfWeek = new HashSet<>();
       daysOfWeek.add("Saturday");
       daysOfWeek.add("Sunday");
       daysOfWeek.add("Monday");
       daysOfWeek.add("Tuesday");
       daysOfWeek.add("Wednesday");
       daysOfWeek.add("Friday");
       
       
       System.out.println("\ndaysOfWeek : " + daysOfWeek);
       daysOfWeek.remove("Saturday");
       System.out.println("daysOfWeek.remove(\"Saturday\") , daysOfWeek : " + daysOfWeek);
       System.out.println("Removing Elements from Java Collections (while iterating through) : ");
       Iterator<String> it = daysOfWeek.iterator();
       while(it.hasNext()) {
    	   if(it.next().startsWith("T")) {
    		   it.remove();
    	   }
       }
       System.out.println(" .iterator() while(it.hasNext()) it.remove() , daysOfWeek : " + daysOfWeek);

       daysOfWeek.removeIf( day -> day.startsWith("W"));
       System.out.println(" removeIf(), daysOfWeek : " + daysOfWeek);

       Set<String> filteredset = daysOfWeek.stream().filter( d -> !d.startsWith("M")).collect(Collectors.toSet());
       System.out.println(" stream().filter.collect(), filteredset : " + filteredset);

       System.out.println("\ninitialize a List");
       System.out.println(" Arrays.asList(T...a)   Stream.of(T...a)    double-braces {{}}");
       List<String> fromArray = Arrays.asList("using", "Arrays");
       List<String> fromStream = Stream.of("Stream", "of").collect(Collectors.toList());
       List<String> doubleBraces = new ArrayList() {{
    	   add("using");
    	   add("double braces");
       }};
       
       System.out.println("\nInitialize Set in Java");
       SortedSet<Double> setByAddingElements = new TreeSet<>();
       setByAddingElements.add(1984.0);
       setByAddingElements.add(-167.4);
       setByAddingElements.add(123.1);
       System.out.println("setByAddingElements : " + setByAddingElements);
       
       List<Integer> listOfInt = Stream.of(2020, 12,-6, 12, 9, -999 ,1980,9).
    		   collect(Collectors.toCollection(LinkedList::new));
       Set<Integer> setFromList = new LinkedHashSet<>(listOfInt);
       System.out.println("new HashSet<>(Collection c)  / new LinkedHashSet(Collection c)");
       System.out.println(" listOfInt : " + listOfInt);
       System.out.println(" new HashSet<>(listOfInt), setFromList : " + setFromList);
       
       Set<String> setFromCollections = new HashSet<>();
       Collections.addAll(setFromCollections, "set", "from", "collection", "addAll");
       System.out.println("Collections.addAll(Collection<? super T> c, T... elements) ");
       System.out.println(" setFromCollections : " + setFromCollections);
       
       Set<String> immutableSet = Collections.unmodifiableSet(setFromCollections);
       try {
    	   setFromCollections.add("elementAdded");
    	   immutableSet.add("elementAdded");// throws UnsupportedOperationException
       }catch(UnsupportedOperationException e) {
    	   System.out.println("immutableSet = Collections.unmodifiableSet(setFromCollections)");
    	   System.out.println(" immutableSet.add(\"elementAdded\") throws UnsupportedOperationException");
       }
       
       Set<Long> setUsingdoublebraces = new HashSet() {{
    	   add(23L);
    	   add(-67L);
    	   add(5666);
       }};
       System.out.println("using double-braces {{ add() }}, setUsingdoublebraces :  " + setUsingdoublebraces);

       Set<Character> setfromCollectorsToSet =  Stream.of('z','m','a').collect(Collectors.toSet());
       Set<Character> setfromCollectorsToCollection =  Stream.of('z','m','a').collect(Collectors.toCollection(TreeSet::new));
       System.out.println("Stream.of('z','m','a').collect(Collectors.toSet()) : " + setfromCollectorsToSet + " (defaults it to HashSet - unsorted)");
       System.out.println(" Stream.of('z','m','a').collect(Collectors.toCollection(TreeSet::new)) : " + setfromCollectorsToCollection);

	}

}
