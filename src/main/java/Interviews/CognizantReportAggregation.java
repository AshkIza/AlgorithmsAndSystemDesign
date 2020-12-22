package Interviews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CognizantReportAggregation {
	
	
	
	
	/**
	 *  
	 * @param sales List containing records for Sale table
	 * @param prices List containing records for Price table
	 * @return List of Report records to be inserted
	 */
	public List<Report> createReport(List<Sale> sales, List<Price> prices) {
		Map<String, Integer> priceMap = new HashMap<>();
		Map<String, Integer> productQuantities = new HashMap<>();
		List<Report> reports = new ArrayList<Report>();
		prices.forEach(p -> priceMap.put(p.product, p.price));
		sales.forEach(s -> {
			
			if(productQuantities.containsKey(s.product)) {
				productQuantities.put(s.product, s.quantity);
			}else {
				int existingQ = productQuantities.get(s.product) ;
				productQuantities.put(s.product, existingQ + s.quantity);
			}
		});
		priceMap.entrySet().stream().forEach(entry ->{
			Report rep = new Report();
			rep.product = entry.getKey();
			rep.quantity = productQuantities.get(rep.product);
			rep.price = priceMap.get(rep.product);
			rep.revenue = rep.quantity * rep.price;
			reports.add(rep);
		});
	
		return reports;
	}
	
	class Sale {
		String product;		
		int quantity;
	}
	
	class Price {
		String product;
		int price;
	}
	
	class Report {
		String product;
		int quantity;
		int price;
		int revenue;
	}
}
