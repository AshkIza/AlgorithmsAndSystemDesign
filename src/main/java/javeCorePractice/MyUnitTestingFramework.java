package javeCorePractice;


/*
 * https://mkyong.com/java/java-custom-annotations-example/
 * */
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Arrays;


public class MyUnitTestingFramework {
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Test{
		boolean enable() default true;
	}
	
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface TesterInfo{
		
		public enum Priority{
			LOW, MEDIUM, HIGH
		}
		
		Priority priority() default Priority.LOW;
		String createdBy() default " ";
		String[] tags() default {" "} ;
	}
	
	
	static void runTests(Class<?> clazz) {
		if (clazz.isAnnotationPresent(TesterInfo.class)){
			Annotation annotaion = clazz.getAnnotation(TesterInfo.class);
			TesterInfo testerInfo = (TesterInfo) annotaion;
			System.out.println(clazz.getName() + "tester info : ");
			System.out.println("	priority : " + testerInfo.priority());
			System.out.println("	created by : " + testerInfo.createdBy());
			System.out.println("	tags : " + Arrays.asList(testerInfo.tags()).toString());
			System.out.println("Running tests ... : ");
			
			Method[] allMethods = clazz.getDeclaredMethods();
			int passed = 0;
			int failed = 0;
			int ignored = 0;

			for(Method method : allMethods) {	
				Test methodAnnotationTest = (Test) method.getAnnotation(Test.class);
				if(methodAnnotationTest.enable()) {
					try {
						Object returnedValue = method.invoke(clazz.newInstance());
						if(returnedValue.getClass().equals(Boolean.class)) {
							Boolean val = (Boolean) returnedValue;
							if(val.equals(Boolean.TRUE)) {
								System.out.println("	" + method.getName() +  " passed : ");
								passed++;
							}else {
								System.out.println("	" + method.getName() +  " failed! : ");
								failed++;
							}
						}

					} catch(Exception e) {
						System.out.println("	" + method.getName() +  " failed! : ");
						failed++;
					}
				}else {
					System.out.println("	" + method.getName() +  " ignored : ");
					ignored++;
				}
			}
			
			System.out.println("	Results - passed : " + passed +  " , failed : " + failed + " , ignored : " + ignored);
		} else {
			System.out.println(clazz.getName() + " is not a Test class!!");
		}
	}
}
