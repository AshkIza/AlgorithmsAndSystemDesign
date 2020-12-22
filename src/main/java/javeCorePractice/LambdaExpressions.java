package javeCorePractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
 * 
 * */
public class LambdaExpressions {
	
	
	public static class Order {
		String country;
		String type;
		int quantity;
		public Order(String country, String type, int quantity) {
			super();
			this.country = country;
			this.type = type;
			this.quantity = quantity;
		}
		
		String getorderDetails() {
			return " order [" + quantity + " " + type + "(s) ( purchased from " + country + ")]";
		}
	}
	
	private static void placeOrder(List<Order> orders, VerifyIntreface... lambdas) {
		System.out.println(orders.size() + " orders are getting processed");
		for(Order order : orders) {
			boolean verified = true;
			for(VerifyIntreface lambda :lambdas) {
				verified &= lambda.verify(order);
			}
			String result = order.getorderDetails();
			result += verified ? " had ben placed" : " rejected! (NOT verified)";
			System.out.println(result);
		}		
	}
	

	public static class MyStream<T>{
		List<T> elemenets;
		MyStream(List<T> elems){
			this.elemenets = elems;
		}
		
		MyStream(T... values){
			elemenets = Arrays.asList(values);
		}
		
		void forEach(MyConsumer<T> myComsumer){
			for(T t : elemenets) {
				myComsumer.apply(t);
			}
		}
		
		<V> MyStream<V> map(MyFunction<T,V> function) {
			List<V> mappedelemenets = new ArrayList<V>();
			for(T t : elemenets) {
				V mapped = function.func(t);
				mappedelemenets.add(mapped);
			}
			return new MyStream<V>(mappedelemenets);
		}
	}
	
	
	@FunctionalInterface
	public interface MyConsumer<T>{
		void apply(T t);
	}
	
	@FunctionalInterface
	public interface MyFunction<T,V>{
		 V func(T input);
	}

	
	@FunctionalInterface
	public interface VerifyIntreface{
		boolean verify(Order t);
	}
	
	public static void main(String[] args) {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order("USA", "Book" , 20));
		orders.add(new Order("USA", "Book" , 3));
		orders.add(new Order("CHINA", "Book" , 5));
		orders.add(new Order("CANADA", "Gun" , 1));
		
        // 1- multiple lambda expression written for VerifyIntreface Functional Interface
		VerifyIntreface verifyCountry = o -> "USA".equals(o.country) || "CANADA".equals(o.country);
		VerifyIntreface verifyQuantity = o -> o.quantity < 5;
		VerifyIntreface verifyType = o -> "USA".equals(o.country) || !"Gun".equals(o.type);

		
		placeOrder(orders, verifyCountry);
		placeOrder(orders, verifyQuantity);
		placeOrder(orders, verifyQuantity, verifyCountry);
		placeOrder(orders, verifyQuantity, verifyCountry, verifyType);
		
		
		// 2- writing MyStream class with forEach and map methods
		MyFunction<Order,String> myFunction = input -> input.getorderDetails();
		MyConsumer<String> myconsumer = str -> System.out.println(str);
		
		MyStream<Order> myStream = new MyStream<>(orders);
		myStream.map(myFunction).forEach(myconsumer);

	}

	

}
