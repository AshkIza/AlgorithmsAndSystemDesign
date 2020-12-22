package javeCorePractice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javeCorePractice.MyUnitTestingFramework.Test;
import javeCorePractice.MyUnitTestingFramework.TesterInfo;
import javeCorePractice.MyUnitTestingFramework.TesterInfo.Priority;


import javeCorePractice.MyJsonSerializerFramework.*;


/*
 * JAVA reflection is a very powerful tool to inspect the attributes of a class in runtime.
 *  For example, we can retrieve the list of public fields of a class using getDeclaredMethods().
 *  
 *  Java Annotations
 *  Retention and Target
	Creating an annotation requires two pieces of information:
	 (1) a retention policy and (2) a target. A retention policy specifies how long, 
	 	in terms of the program lifecycle, the annotation should be retained for. 
	 	For example, annotations may be retained during compile-time or runtime, 
	 	depending on the retention policy associated with the annotation.
	 (2) The target of an annotation specifies which Java constructs an annotation can be applied to.
	  	For example, some annotations may be valid for methods only, 
	  	while others may be valid for both classes and fields	
 * */

public class Reflection {
	
	public static class Student{
	    private String name;
	    private String id;
	    private String email;
	    
	    public String getName() {
	        return name;
	    }
	    public void setId(String id) {
	        this.id = id;
	    }
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public void anothermethod(){  }
	    
	    private void privatemethod(){  }
	}
	
	
	@TesterInfo(priority = Priority.HIGH, createdBy= "Ashkan", tags = {"java.lang.reflect", "java.lang.annotation"})
	public static class TestClass {
		
		@Test(enable = true)
		Boolean testPassA(){
			return true;
		}
		
		@Test(enable = false)
		Boolean testPassB(){
			return true;
		}
		
		@Test
		Boolean testFail() {
			return false;
		}
		
		@Test(enable = true)
		Boolean testException() throws Exception {
			 throw new Exception();
		}
	}
	
	
	@JsonObject
	public static class Car {
		@JsonField(name = "manufacturer")
	    private final String make;
		@JsonField()
	    private final String model;
	    private final String year;
		@JsonField(name = "VINnumber")
	    private final long vin;
	    
	    public Car(String make, String model, String year, long vin) {
	        this.vin = vin;
			this.make = make;
	        this.model = model;
	        this.year = year;
	    }
	    public String getMake() {
	        return make;
	    }
	    public String getModel() {
	        return model;
	    }
	    public String getYear() {
	        return year;
	    }
	    
	    public long getVin() {
			return vin;
		}
		@Override
	    public String toString() {
	        return year + " " + make + " " + model;
	    }
	}
	
	

	public static void main(String[] args) {
		System.out.println("java Reflection : inspect attributes of a class at run-time");
		
		// 1) Java reflection -  getClass() : 
		// prints all the methods of another class called Student in alphabetical order.
		// https://www.hackerrank.com/challenges/java-reflection-attributes/problem
		 Class student =  Student.class; // new Student().getClass();
         Method[] methods = student.getDeclaredMethods();
         Arrays.stream(methods).map(method -> method.getName()).sorted().forEach(System.out::println);
         /* java 7   
         ArrayList<String> methodList = new ArrayList<>();
         for(Method m : methods){
             methodList.add(m.getName());
         }
         java.util.Collections.sort(methodList);
         for(String name: methodList){
             System.out.println(name);
         }*/
         
         
		System.out.println("-------------------------------------------------------------------");
		System.out.println("Java Reflection - Annotation , MyUnitTestingFramework.runTests");
         // 2) Java Reflection - Annotation
         // annotation for testing framework
         MyUnitTestingFramework.runTests(Student.class);
         MyUnitTestingFramework.runTests(TestClass.class);
         
         
         
         
        System.out.println("-------------------------------------------------------------------");
 		System.out.println("Java Reflection - Annotation , MyJsonSerializerFramework.serialize");
 		Car car01 = new Car("Ford", "F150", "2018", 7676684343436L);
 		Car car02 = new Car("Mazda", "Rx", "2021", 98038943434380L);
 		System.out.println(MyJsonSerializerFramework.serialize(car01));
 		System.out.println(MyJsonSerializerFramework.serialize(car02));
 		System.out.println(MyJsonSerializerFramework.serialize(new Student()));
         
         
         //---------------------------------------------------------------
         
         /*  https://www.geeksforgeeks.org/arrays-stream-method-in-java/
          * public static <T> Stream<T> stream(T[] array)
          * String[] arr = { "Geeks", "for", "Geeks" }; 
          * Stream<String> stream = Arrays.stream(arr);  
          * */
         
         /*  https://mkyong.com/java8/java-8-how-to-sort-list-with-stream-sorted/
          * How to sort list with stream.sorted()
          * 
          *    List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
          *    
          *    List<String> sortedList = list.stream()
					.sorted(Comparator.naturalOrder())
					.collect(Collectors.toList());

        		List<String> sortedList = list.stream()
					.sorted((o1,o2)-> o1.compareTo(o2))
					.collect(Collectors.toList());
					
				List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
          * 
          * */
         
         /* https://www.geeksforgeeks.org/stream-map-java-examples/
          * Stream map() : Stream map(Function mapper) returns a stream consisting of 
          * 	the results of applying the given function to the elements of this stream.
          * 
          *  list.stream().map(number -> number * 3).forEach(System.out::println); 
          * */
         
         
	}

}
