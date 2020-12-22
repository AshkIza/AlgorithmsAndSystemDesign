package javeCorePractice;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/*
 * 
 * https://www.journaldev.com/21095/java-equals-hashcode
 * https://howtodoinjava.com/java/basics/java-hashcode-equals-methods/
 * 
 * http://etutorials.org/cert/java+certification/Chapter+11.+Collections+and+Maps/11.6+Sorted+Sets+and+Sorted+Maps/
 * 
 * https://javarevisited.blogspot.com/2012/11/difference-between-treeset-hashset-vs-linkedhashset-java.html
 * 
 * 
 * */
public class EqualsAndHashCode {
	
	public static class ProductOrder {
		Instant orderDate;
		CustomerAccount orderedBy;
		String productNanme;
		double totalAmmount;
		public ProductOrder(String productNanme,double totalAmmount) {
			this.orderDate = Instant.now();
			this.productNanme = productNanme;
			this.totalAmmount = totalAmmount;
		}
		public ProductOrder withOrderedBy(CustomerAccount orderedBy) {
			this.orderedBy = orderedBy;
			return this;
		}
		public String getProductNanme() {
			return productNanme;
		}
		
		public double getTotalAmmount() {
			return totalAmmount;
		}
		@Override
		public boolean equals(Object o) {
			if(this == o) {
				return true;
			}
			if(o != null && o.getClass().equals(getClass())) {
				ProductOrder other = (ProductOrder) o;
				return this.orderDate.equals(other.orderDate) &&
						this.orderedBy.equals(other.orderedBy) &&
						this.productNanme.equals(other.productNanme) && 
						this.totalAmmount == other.totalAmmount;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			int hash = 0;
			hash = 31 * hash + orderDate.hashCode();
			hash = 31 * hash + orderedBy.hashCode();
			hash = 31 * hash + productNanme.hashCode();
			hash = 31 * hash + Double.hashCode(totalAmmount);
			return hash;
		}
		
		@Override
		public String toString() {
			return "{productNanme:" + productNanme + 
					", totalAmmount:" + totalAmmount + 
					//"orderDate:"  + orderDate +
					"}\n";
					
		}
		
		
	}
	
	public static class CustomerAccount {
		String name;
		List<ProductOrder> orders = new ArrayList<>();
		Instant memberSince;
		public CustomerAccount(String name, LocalDate memberSince) {
			this.name = name;
			this.memberSince = memberSince.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		}
		public void order(String productNanme,double totalAmmount) {
			ProductOrder productOrder = new ProductOrder(productNanme, totalAmmount);
			productOrder.withOrderedBy(this);
			orders.add(productOrder);
		}
		
		public void preorder(List<ProductOrder>  productOrders) {
			for(ProductOrder productOrder : productOrders) {
				productOrder.withOrderedBy(this);
				orders.add(productOrder);
			}
		}
		public String getName() {
			return name;
		}
		
		public Instant getMemberSince() {
			return memberSince;
		}
		@Override
		public boolean equals(Object o) {
			if(this == o) {
				return true;
			}
			if(o != null && o.getClass().equals(getClass())) {
				CustomerAccount other = (CustomerAccount) o;
				return this.name.equals(other.name) &&
						this.memberSince.equals(other.memberSince);
						
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			int hash = 0;
			hash = 31 * hash + name.hashCode();
			//hash = 31 * hash +  Objects.hash(orders);//always use same attribute for hashCode() and equals()
			hash = 31 * hash + memberSince.hashCode();
			
			return hash;
		}
		
		@Override
		public String toString() {
			return "name:" + name +
				",memberSince:" + 
					LocalDateTime.ofInstant(memberSince, ZoneId.systemDefault()).toLocalDate()+
					"\n";
		}
		
	}

	public static void main(String[] args) {
		System.out.println("for ArrayList and LinkedList, NO NEED to implement any of these : hashCode()/equals()/comparators");
		System.out.println("for PriorityQueue, you need to implement comparator/Comparable");

		List<ProductOrder> preordersLinkedList = new LinkedList<>();
		preordersLinkedList.add(new ProductOrder("mazda3", 23500.0));
		preordersLinkedList.add(new ProductOrder("lamborgini", 323500.0));
		
		List<ProductOrder> preordersArrayList = new ArrayList<>();
		preordersArrayList.add(new ProductOrder("shiraz wine", 500.0));
		preordersArrayList.add(new ProductOrder("gouda cheese", 27.0));
		
		Comparator<ProductOrder> priorityQueueComparator = (a,b) -> a.orderDate.compareTo(b.orderDate);
		Queue<ProductOrder> preordersQueue = new PriorityQueue<>(priorityQueueComparator);
		preordersQueue.add(new ProductOrder("piano casio", 3500.0));
		preordersQueue.add(new ProductOrder("microphone sony", 900.0));

		
		CustomerAccount ladyGaga = new CustomerAccount("lady gaga", LocalDate.parse("2001-04-06"));
		ladyGaga.preorder(preordersLinkedList);
		ladyGaga.preorder(preordersArrayList);

		ladyGaga.order("beauty products", 890.0);
		ladyGaga.order("shampoo", 34.87);
		ladyGaga.order("shampoo", 34.87);
		
		CustomerAccount vladimirPutin = new CustomerAccount("vladimit Putin", LocalDate.parse("2019-11-29"));
		vladimirPutin.order("weights", 1893.11);
		vladimirPutin.order("soap", 99.01);
		vladimirPutin.order("soup", 13.23);
			
		System.out.println("for hashSet and hashMap, you need to implement hashCode()");
		Set<CustomerAccount> customersHashSet = new HashSet<>();
		customersHashSet.add(ladyGaga);
		customersHashSet.add(vladimirPutin);
		
		System.out.println("for TreeSet and TreeMap, you need to implement comparator/Comparable");
		Comparator<CustomerAccount> treeSetcomparator = (a,b) -> {
			int nameDiff = a.name.compareTo(b.name);
			return (nameDiff != 0) ? nameDiff : a.memberSince.compareTo(b.memberSince);
		};
			
		Set<CustomerAccount> customersTreeSet = new TreeSet<CustomerAccount>(treeSetcomparator);
		CustomerAccount brianAdams = new CustomerAccount("brian adams", LocalDate.parse("1990-12-29"));
		brianAdams.order("guiatar",1500.0);
		brianAdams.order("drum", 444.44);
		brianAdams.order("shampoo", 34.87);
		
		CustomerAccount angelaMerkel = new CustomerAccount("angela merkel", LocalDate.parse("2017-01-09"));
		angelaMerkel.order("soy beans", 66.45);
		angelaMerkel.order("suits", 1881.01);
		angelaMerkel.order("soup", 13.23);
			
		customersTreeSet.add(brianAdams);
		customersTreeSet.add(angelaMerkel);
		
		System.out.println("\n\n hashCode() equals() general rules:");
		System.out.println("	1- use same object attributes for equals() and hashCode()");
		System.out.println("	2- if override one, should override the other");
		System.out.println("	3- if a.quals(b) then a.hashCode() == b.hashCode()");
	}
}
