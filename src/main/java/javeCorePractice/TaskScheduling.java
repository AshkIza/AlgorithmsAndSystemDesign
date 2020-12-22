package javeCorePractice;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*  What is Cron Job ? How to Create Cron Job in Core Java ?
 * 			 (java.util.Timertask)
 *  http://www.infybuzz.com/2019/08/what-is-cron-job-how-to-create-cron-job-in-core-java.html
 *  	Cron Job is kind of scheduled task (Cron job means scheduler) 
 *  	If you have some task which should run at some interval OR at some specific time in a day 
 *  		then you can create Cron Job.
		Cron word is obtained from Chronograph word.
		
 * 
 * https://dzone.com/articles/schedulers-in-java-and-spring
 * The scheduler is used to schedule a thread or task that executes at a certain period of time or
 * 	 periodically at a fixed interval. 
 * 
 * There are multiple ways to schedule a task in Java:
	 1) java.util.TimerTask  (java core - single threaded)
	 2) java.util.concurrent.ScheduledExecutorService (java core - multithreaded)
	 3) org.springframework.scheduling.TaskScheduler (spring - multithreaded)
	 4) Quartz Scheduler
	 
 * 
 * 
 * */

public class TaskScheduling {
	static Supplier<Product> productSupplier = ()-> new Product(UUID.randomUUID());
	enum ProductType{
		ELECTRONICS, KITCHENWARE,FOOD;
	}
	static class Product {
		UUID serialNumber;
		ProductType type;
		boolean reportCreated;
		Product(UUID id){
			this.serialNumber = id;
		}
		Product withType(ProductType type){
			this.type = type;
			return this;
		}
		@Override
		public String toString() {
			return "Product [serialNumber=" + serialNumber.toString().substring(0,5) + ", type=" + type + "]";
		}
		public boolean isReportCreated() {
			return reportCreated;
		}
		public void setReportCreated(boolean reportCreated) {
			this.reportCreated = reportCreated;
		}
		
	}
	
	private static List<Product> productsSold; 
	
	public static List<Product> getProducts(){
		if(productsSold == null) {
			//productsSold = Collections.synchronizedList(new ArrayList<>());
			//productsSold = new ArrayList<>();
			productsSold = new CopyOnWriteArrayList<>();


		}
		return productsSold;
	}
	
	
	static Runnable SalesReportGenerator = () -> {
		System.out.println(Thread.currentThread().getName() + " | SalesReportGenerator (" + LocalTime.now() + " )");
		getProducts().stream().filter(product -> !product.isReportCreated())
		.forEach(product -> {
			System.out.println(product.toString());
			product.setReportCreated(true);
		});
	};
	
	static Runnable GarbageCollector = () -> {
		System.out.print(Thread.currentThread().getName() + " | GarbageCollector " );
		int a = (int) getProducts().stream().filter(Product::isReportCreated).count();
		getProducts().removeIf(Product::isReportCreated);
		int b = getProducts().size();
		System.out.println( a + " products removed (" + b + " products remaining) (" + LocalTime.now() +")");
	};
	
	public static class ProductInventory extends TimerTask {
		ProductInventory(){
		}
		
		@Override
		public void run() {
			int a = Math.abs(new Random().nextInt()) % 10;
			int b = Math.abs(new Random().nextInt()) % 5;
			int c = Math.abs(new Random().nextInt()) % 3;
			IntStream.range(0, a).forEach( i -> {
				getProducts().add(productSupplier.get().withType(ProductType.ELECTRONICS));
			});
			IntStream.range(0, b).forEach( i -> {
				getProducts().add(productSupplier.get().withType(ProductType.KITCHENWARE));
			});
			IntStream.range(0, c).forEach( i -> {
				getProducts().add(productSupplier.get().withType(ProductType.FOOD));
			});
			System.out.println(Thread.currentThread().getName() + "  "+ (int)(a+b+c) 
					+ " products added to inventory (" + LocalTime.now() +")");
		}
	}
	
	

	public static void main(String[] args) {
		Timer productInventoryWriterTimer = new Timer("productInventoryWriterTimer", false);
		productInventoryWriterTimer.scheduleAtFixedRate(new ProductInventory(), 0, 10000);//every second writing to memory
		
		ScheduledExecutorService sceduler = Executors.newScheduledThreadPool(2);
		sceduler.scheduleAtFixedRate(SalesReportGenerator, 10, 15000, TimeUnit.MILLISECONDS);
		sceduler.scheduleAtFixedRate(GarbageCollector, 0, 30000, TimeUnit.MILLISECONDS);
	}

}
