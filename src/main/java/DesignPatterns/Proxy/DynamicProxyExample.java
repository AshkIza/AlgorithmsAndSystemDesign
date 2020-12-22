package DesignPatterns.Proxy;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/* Dynamic proxies:
	Not tied to a specific interface. Can dynamically tie it to any class at run-time.
	Static proxy: (general proxy design pattern)
 		TwitterSecurityProxy implements TwitterInterface
	Dynamic proxy: (java.lang.reflect.Proxy)
		SecurityProxy implements InvocationHandler
 	Under the cover, it routes all method invocations to a single handler – the invoke() method.
	It may also be used in those cases where concrete class implementations won't be known until run-time.
	
	To create an actual dynamic proxy class,
	 	all you need to do is implement the java.lang.reflect.InvocationHandler interface:
 * */
// InvocationHandler
public class DynamicProxyExample {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface LogeAble{
		
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface Timer{
		
	}
	
	public interface ServiceInterface{
		@LogeAble
		String processA(int val);
		@Timer
		@LogeAble
		String processB(Long val); 
		
	}
	
	public static class Service implements ServiceInterface{
		
		public String processA(int val) {
			return Integer.toBinaryString(val);
		}
		
	
		public String processB(Long val) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return String.valueOf(val);
		}
		
	}
	
	public static class TimingAndLoggingDynamicProxy implements InvocationHandler{
		Object realObject;
		TimingAndLoggingDynamicProxy(Object realObject){
			this.realObject = realObject;
			//we pass in real object into the proxy class,in case need to invoke methods of real object
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Annotation[] annotations = method.getDeclaredAnnotations();
			boolean timer = Arrays.stream(annotations).anyMatch(an -> an  instanceof DesignPatterns.Proxy.DynamicProxyExample.Timer);
			boolean loggable = Arrays.stream(annotations).anyMatch(an -> an  instanceof DesignPatterns.Proxy.DynamicProxyExample.LogeAble);
			long t1 =0L;
			long t2 =0L;
			if(loggable) {
				String classname = realObject.getClass().toString();
				String packageName = realObject.getClass().getPackageName();
				System.out.println(" Method " + method.getName() + 
						" from " + classname.replace(packageName,"") + " invoked with arg : " + args[0]);  
				
			}
			if(timer) {
				t1 = System.currentTimeMillis();
			}
			
			Object obj = method.invoke(realObject, args);
			
			if(timer) {
				t2 = System.currentTimeMillis();
				long dif = t2 - t1;
				System.out.println(" Method " + method.getName() + " took " + dif + "ms to run.");
			}
			return obj;
		}
		
	}
	
	public static void main(String[] args) {
		
		System.out.println("objectNoProxy");
		Service objectNoProxy = new Service();
		System.out.println("	objectNoProxy.processA(12) : " + objectNoProxy.processA(12));
		System.out.println("	objectNoProxy.processB(12L) : " + objectNoProxy.processB(12L));

		
		
		System.out.println("\nobjectWithProxy");
		ServiceInterface objectWithProxy = (ServiceInterface) Proxy.newProxyInstance(
				ServiceInterface.class.getClassLoader(),
				new Class[] {ServiceInterface.class},
				new TimingAndLoggingDynamicProxy(new Service()));
		System.out.println("objectWithProxy.processA(-90) : " + objectWithProxy.processA(-90));
		System.out.println("objectWithProxy.processB(129191L) : " + objectWithProxy.processB(129191L));
		
		
	}

}
