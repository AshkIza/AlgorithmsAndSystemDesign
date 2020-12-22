package DesignPatterns.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import DesignPatterns.Proxy.InterceptorUsingDynamicProxy.ProductOrderDAOInterface.*;


/* Interceptor design using Dynamic Proxies (Spring SOP using it too)
   Dynamic proxy of Java design pattern (application of interceptor) https://www.programmersought.com/article/21714449472/
https://laptrinhx.com/java-implements-a-simple-interceptor-through-a-dynamic-proxy-945519194/
 * 
 * */

public class InterceptorUsingDynamicProxy {
	static Map<ProductType, Integer> SALES;// in-memory Database
	static {
		SALES = new HashMap<>();
		SALES.put(ProductType.CAR, 0);
		SALES.put(ProductType.LAPTOP, 0);
		SALES.put(ProductType.FRIDGE, 0);
		SALES.put(ProductType.GUNS, 0);
	}
	
	public interface Interceptor{
		void before(Object targetObj, Method method, Object[] args);
		void after(Object targetObj, Method method, Object[] args);
		void afterThrowing(Object targetObj, Method method, Object[] args, Exception ex);
	}

	
	public static class JdkReflectDynamicProxy implements InvocationHandler{
		Object targetObject;
		Interceptor proxyHookImplementations;
		
 
		
		JdkReflectDynamicProxy(Object proxiedObject, Interceptor interceptor){
			this.targetObject =  proxiedObject;
			this.proxyHookImplementations = interceptor; 
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if(proxyHookImplementations == null)
				return proxy;
			proxyHookImplementations.before(targetObject, method, args);
			Object obj = null;
			try {
				obj = method.invoke(targetObject, args);
			}catch(Exception ex) {
				proxyHookImplementations.afterThrowing(targetObject, method, args, ex);
			}finally {
				proxyHookImplementations.after(targetObject, method, args);
			}
			return obj;
		}
	}
	
	// -- general purpose interceptors using Interceptor interface
	public static class GeneralLoggingInterceptor implements Interceptor{
		@Override
		public void before(Object targetObj, Method method, Object[] args) {
			String argsString = (String) Arrays.stream(args).reduce("", (a,b) -> a + ", " + b);
			String classname = targetObj.getClass().toString();
			String packageName = targetObj.getClass().getPackageName();
			System.out.println(" Method " + method.getName() + 
					" from " + classname.replace(packageName,"") + " invoked with arguments : " + argsString);  		
		}

		@Override
		public void after(Object targetObj, Method method, Object[] args) {
		}

		@Override
		public void afterThrowing(Object targetObj, Method method, Object[] args, Exception ex) {
		}
	}
	
	
	public static class ProductDAOSecurityFilterPerformanceMonitor implements ProductDAOInterceptor{
		@Override
		public void beforeOrder(ProductOrderDAOContext context, ProductType product, int quantity) {
			if(UserType.PUBLIC == context.userType && ProductType.GUNS == product) {
				System.out.println(" *** potentials SECURITY concern. Public users not allowed to order GUNS! --> order suspending ...");
			}
			if(UserType.PUBLIC == context.userType && quantity > 20) {
				System.out.println(" *** Ordering more than 20 quantities not allowed for Public --> order suspending ...");
			}
		}
		@Override
		public void beforeQuery(ProductOrderDAOContext context) {	
			context.cacheable = true;//enable cacheing
		}
		@Override
		public void afterOrder(ProductOrderDAOContext context) {
			
		}
		@Override
		public void exceptionHandling(Exception ex) {
			
		}
	}
	
	interface ProductDAOInterceptor{
		void beforeOrder(ProductOrderDAOContext context, ProductType product, int quantity);
		void beforeQuery(ProductOrderDAOContext context);
		void afterOrder(ProductOrderDAOContext context);
		void exceptionHandling(Exception ex);
		
	}
	public static class ProductDAOAbstractInterceptor implements Interceptor{
		ProductDAOInterceptor productDaoHook;
		ProductDAOAbstractInterceptor(ProductDAOInterceptor hookImplementation){
			productDaoHook = hookImplementation;
		}
		
		@Override
		public void before(Object targetObj, Method method, Object[] args) {
			if(isProductDaoObject(targetObj)) {
				ProductOrderDAOContext context = ((ProductOrderDAOInterface)targetObj).getContext();
				if(method.getName().equals("order")) {
					ProductType product = (ProductType) args[0];
					int quantity = (int) args[1];
					productDaoHook.beforeOrder(context, product, quantity);
				}else if(method.getName().equals("query")) {
					productDaoHook.beforeQuery(context);
				}
			}	
		}

		@Override
		public void after(Object targetObj, Method method, Object[] args) {
			if(isProductDaoObject(targetObj)) {
				if(method.getName().equals("order")) {
					ProductOrderDAOContext context = ((ProductOrderDAOInterface)targetObj).getContext();
					productDaoHook.afterOrder(context);
				}
			}	
		}

		@Override
		public void afterThrowing(Object targetObj, Method method, Object[] args, Exception ex) {
			productDaoHook.exceptionHandling(ex);
		}
		
		private boolean isProductDaoObject(Object targetObj) {
			return targetObj instanceof ProductOrderDAOInterface;
			
		}
	}
	
	
	public interface ProductOrderDAOInterface {
		public enum ProductType{
			CAR, LAPTOP, FRIDGE, GUNS;
		}
		public enum UserType{
			PUBLIC, VIP;
		}
		public static class ProductOrderDAOContext{
			public boolean cacheable;
			UserType userType;
		}
		void order(ProductType product, int quantity, UserType user);
		int query(ProductType product);
		ProductOrderDAOContext getContext();
		
  	    //bind interceptor to the object by creating/returning a proxy object (using dynamic proxy)
		public default ProductOrderDAOInterface registerInterceptor(Object realObject,  Interceptor interceptor){
   		 InvocationHandler dynamicProxy = new JdkReflectDynamicProxy(realObject, interceptor);
   		 return (ProductOrderDAOInterface)Proxy.newProxyInstance(ProductOrderDAOInterface.class.getClassLoader(),
   				 new Class[] {ProductOrderDAOInterface.class}, 
   				 dynamicProxy);
   		 }
	}
	
	public static class ProductOrderDAO implements ProductOrderDAOInterface{
		public boolean cacheable = false;
		//Map<ProductType, Integer> SALES;
		ProductOrderDAOContext context = new ProductOrderDAOContext();
		
		public static ProductOrderDAO buildProductOrderDAO() {
			ProductOrderDAO instance = new ProductOrderDAO();
			return instance;
		}
		
		public ProductOrderDAO withVIPUser() {
			context.userType = UserType.VIP;
			return this;
		}
		
		public ProductOrderDAOInterface addInterceptor(Interceptor interceptor) {
			return registerInterceptor(this, interceptor);
		}
		
		ProductOrderDAO(){
			context.userType = UserType.PUBLIC;
		}
		
		public void order(ProductType product, int quantity, UserType user) {
			SALES.put(product, SALES.get(product) + quantity);
		}
		
		public int query(ProductType product) {
			if(!context.cacheable) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println("Caching enabled");
			}
			return (int) SALES.get(product);
		}

		@Override
		public ProductOrderDAOContext getContext() {
			context.cacheable = cacheable;
			return context;
		}

	}
	
	public static void main(String[] args) {
		System.out.println("productDao/ general interceptor -  Logging for all method invocations");
		ProductOrderDAOInterface productDao = ProductOrderDAO
				.buildProductOrderDAO()
				.addInterceptor(new GeneralLoggingInterceptor());
		productDao.order(ProductType.CAR, 5, null);
		productDao.order(ProductType.LAPTOP, 10, null);
		productDao.order(ProductType.FRIDGE, 50, null);
		productDao.order(ProductType.GUNS, 20, null);
		productDao.order(ProductType.CAR, 30, null);
		System.out.println("productDao.query(ProductType.CAR) -> " + productDao.query(ProductType.CAR));
		
		
		System.out.println("\nproductDao/ productDaoInterceptor  -  adding security and perfromance monitoring layers ");
		ProductOrderDAOInterface productDaoSecond = ProductOrderDAO
				.buildProductOrderDAO()
				.addInterceptor(new ProductDAOAbstractInterceptor(new ProductDAOSecurityFilterPerformanceMonitor()));
		productDaoSecond.order(ProductType.CAR, 6, null);
		productDaoSecond.order(ProductType.LAPTOP, 9, null);
		System.out.println("productDaoSecond.query(ProductType.CAR) -> " + productDaoSecond.query(ProductType.CAR));
		System.out.println("productDaoSecond.query(ProductType.LAPTOP) -> " + productDaoSecond.query(ProductType.LAPTOP));

		productDaoSecond.order(ProductType.FRIDGE, 50, null);
		productDaoSecond.order(ProductType.GUNS, 5, null);

	}

}
