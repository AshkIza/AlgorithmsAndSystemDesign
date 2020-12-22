package javeCorePractice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.*;

import javeCorePractice.EqualsAndHashCode.CustomerAccount;
import javeCorePractice.EqualsAndHashCode.ProductOrder;

public class LambdaComparatorsCollectors {

	public static void main(String[] args) {
		
		List<EqualsAndHashCode.ProductOrder> orderlist = new ArrayList<EqualsAndHashCode.ProductOrder>(){{
			add(new ProductOrder("mazda3", 23500.0));
			add(new ProductOrder("lamborgini", 323500.0));
			add(new ProductOrder("shiraz wine", 500.0));
			add(new ProductOrder("gouda cheese", 27.0));
			add(new ProductOrder("piano casio", 3500.0));
			add(new ProductOrder("microphone sony", 900.0));
			add(new ProductOrder("soap", 99.01));
			add(new ProductOrder("soup",  13.23));
		}};
		
		Comparator<EqualsAndHashCode.ProductOrder> amountComparator =  (po1, po2) -> Double.compare(po1.totalAmmount, po2.totalAmmount);
	
		Collections.sort(orderlist, amountComparator);
		System.out.println("Collections.sort(orderlist, amountComparator) ");
		System.out.println(orderlist);
		orderlist.sort(amountComparator.reversed());
		System.out.println("orderlist.sort(amountComparator.reversed())");
		System.out.println(orderlist);
		System.out.println("orderlist.sort(LambdaComparatorsCollectors::compareProductNames)");
		orderlist.sort(LambdaComparatorsCollectors::compareProductNames);
		System.out.println(orderlist);
		System.out.println("orderlist.sort(Comparator\n" + 
				"		.comparing(EqualsAndHashCode.ProductOrder::getProductNanme)\n" + 
				"		.thenComparing(EqualsAndHashCode.ProductOrder::getTotalAmmount))");
		
		orderlist.sort(Comparator
				.comparing(EqualsAndHashCode.ProductOrder::getProductNanme)
				.thenComparing(EqualsAndHashCode.ProductOrder::getTotalAmmount));
		System.out.println(orderlist);

		Set<EqualsAndHashCode.CustomerAccount> customers = new HashSet<EqualsAndHashCode.CustomerAccount>() {{
			add(new CustomerAccount("lady gaga", LocalDate.parse("2001-04-06")));
			add(new CustomerAccount("vladimit Putin", LocalDate.parse("2019-11-29")));
			add(new CustomerAccount("brian adams", LocalDate.parse("1990-12-29")));
			add(new CustomerAccount("angela merkel", LocalDate.parse("2017-01-09")));
		}};
		
		System.out.println("customers.stream()\n" + 
				"		.sorted(Collections.reverseOrder\n" + 
				"			(Comparator.comparing(EqualsAndHashCode.CustomerAccount::getName)))\n" + 
				"		.collect(Collectors.toCollection(LinkedHashSet::new));");

		Set<EqualsAndHashCode.CustomerAccount> sortedcustomers = customers.stream().
			sorted(Collections.reverseOrder
					(Comparator.comparing(EqualsAndHashCode.CustomerAccount::getName)))
			.collect(Collectors.toCollection(LinkedHashSet::new));
		System.out.println(sortedcustomers);
		
		
		
		/*orderlist.stream()
			.map(EqualsAndHashCode.ProductOrder::getTotalAmmount)
			.collect(Collectors.summarzingInt());*/
		
	
		Map<String, LinkedList<EqualsAndHashCode.ProductOrder>> priceToFamilyClassMap = orderlist.stream()
			.collect(Collectors.groupingBy(  o -> {
					double purchasePrice = o.totalAmmount;
					return (purchasePrice < 100.00) ? "poor" : 
							(purchasePrice < 1000.00) ? "middle-class" : "rich";
				},
				Collectors.toCollection(LinkedList::new)));
		System.out.println(priceToFamilyClassMap);


	}
	
	static int compareProductNames(EqualsAndHashCode.ProductOrder o1, 
			EqualsAndHashCode.ProductOrder o2) {
		return o1.productNanme.compareTo(o2.productNanme);
	} 

}
