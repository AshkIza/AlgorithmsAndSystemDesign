package javeCorePractice;

import java.lang.Thread.State;

/* 1- explain how 2 un-synchronized threads can cause incosistency in a shared resource
 * 2- use Thread.join() Thread.wait() 
 * */

public class SynchronizedResource {
	
	//private static IN_MEMORY_DATABASE = 

	
	

	public static class AgeSexJob implements Runnable{
		SharedResource resource;
		public AgeSexJob(SharedResource resource) {
			this.resource = resource;
		}
		@Override
		public void run() {
				resource.setAgeSexName(20, Sex.Male, null);

			
		
		}
	}
	
	
	public static class NameJob implements Runnable{
		SharedResource resource;
		public NameJob(SharedResource resource) {
			this.resource = resource;
			System.out.print(resource.getClass());

		}
		@Override
		public void run() {
			resource.setAgeSexName(-1, null, "John Smith");

		}
	}
	

	
	
	
	private static class SharedResource{
		int id;
		String status = "NOT_PROCESSED";
		int age;
		Sex sex;
		String name;
		
		
	
	
		public void process(int id, String status) {
			this.id = id;
			this.status = status;
		}

	

		public void setAgeSexName(int age, Sex sex, String name) {
			if(age !=-1){
				this.age = age;
			}
			
			if(sex != null){
				this.sex = sex;
			}
			if(name != null){
				
				this.name = name;
				
			}
		}
		
		public void printFields(){
			System.out.print("  age : " + age + ", sex : " + sex + ", name : " + name);

		}
	}
	
	private static class SharedResourceSynchronized  extends SharedResource{

		@Override
		public void setAgeSexName(int age, Sex sex, String name) {
			synchronized(this){
				if(age !=-1){
					this.age = age;
				}
				if(sex != null){
					this.sex = sex;

				}
				if(name != null){
					this.name = name;
				}
			}
		}
	}
	
	public enum Sex{
		Male, Female;
	}

	public static void main(String[] args) {
		
		System.out.println(" shared resource is not synchronized - data inconsistency");
		SharedResource obj01 = new SharedResource();
		Thread t1 = new Thread(new AgeSexJob(obj01));
		Thread t2 = new Thread(new AgeSexJob(obj01));
		Thread t3 = new Thread(new AgeSexJob(obj01));
		Thread t4 = new Thread(new AgeSexJob(obj01));
		Thread t5 = new Thread(new AgeSexJob(obj01));

		Thread t6 = new Thread(new NameJob(obj01));
		Thread t7 = new Thread(new NameJob(obj01));
		Thread t8 = new Thread(new NameJob(obj01));
		Thread t9 = new Thread(new NameJob(obj01));
		Thread t10 = new Thread(new NameJob(obj01));

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();


		try{
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
			
		}catch(InterruptedException e){
			
		}
		obj01.printFields();
		
		
		System.out.println(" \nshared resource is synchronized - data integrity maintained");
		SharedResourceSynchronized obj02 = new SharedResourceSynchronized();
		Thread t30 = new Thread(new AgeSexJob(obj02));
		Thread t40 = new Thread(new NameJob(obj02));
		t30.start();
		t40.start();
		try{
			t3.join();
			t4.join();
		}catch(InterruptedException e){
			
		}
		obj02.printFields();
		
		
		
		
	}

}
