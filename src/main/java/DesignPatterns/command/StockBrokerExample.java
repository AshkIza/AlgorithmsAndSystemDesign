package DesignPatterns.command;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * https://www.tutorialspoint.com/design_pattern/command_pattern.htm
 * 
 * ** Encapsulate a request as an object
 * 
 * we have a request and an object (receiver) which we apply request on
 * Command encapsulate the request and the receiver object (as a unit of work)
 * So, it can be stored, queued, and planed to be executed for later. 
 * Client doesn't know how/when the requests will be executed. 
 * the invoker (executor), invokes the commands and manages the commands execution 
 *  (but does NOT know about how requests get executed - only knows the interface)
 *  
 *  You need a command pattern to have a life span independent of the original request,
 	or if you want to queue, specify and execute requests at different times.
 	 (Jobs as separate units of works)
 	
 	command knows how to execute the request also has access to the receiver object, 
 		so can be looked as separate unit of work being executed anytime, also can
 		queue different commands to be proceesed later - batch processing
 * */
public class StockBrokerExample {
	
	// command interface
	public interface StockOrder {
		public boolean execute();
	}
	
	// receiver objects Stock and Portfolio
	public static class Stock {
		double price;
		String name;
		
		public Stock(String name, double price) {
			this.price = price;
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
	}
	
	public static class Portfolio {
		String investorName;
		Map<String, Integer> investments;// <stockName, quantity>
		double  cashAvailable;
		
		Portfolio(String investorName, double cashAvailable){
			this.investorName = investorName;
			this.cashAvailable = cashAvailable;
			investments = new HashMap<>();
		}
		
		public void setCash(double newCash) {
			this.cashAvailable = newCash;
		}
		
		public void addOrUpdateStock(Stock stock, int quantity) {
			if(investments.containsKey(stock.getName()) && quantity == 0) {
				investments.remove(stock.getName());
			}else {
				investments.put(stock.getName(), quantity);
			}
		}
		
		public void showPortfolio() {
			System.out.println(investorName + "'s portfolio : ");
			System.out.println("	cashAvailable : " + cashAvailable);
			System.out.println("	investments : ");
			investments.keySet().stream().forEach(stockName -> 
				System.out.println("	" + stockName + " : "  + investments.get(stockName) + " units"));
		}
		
	}
	
	// concrete command objects processing requests on the receive objects (stocks)
	public static class BuyCommand implements StockOrder{
		public Stock stock;
		public int unitNumbersRequested; 
		public Portfolio portfolio;
		
		public BuyCommand(Stock stock, int unitNumbersRequested, Portfolio portfolio) {
			this.stock = stock;
			this.unitNumbersRequested = unitNumbersRequested;
			this.portfolio = portfolio;
		}

		@Override
		public boolean execute() {
			if(portfolio.cashAvailable < (unitNumbersRequested * stock.getPrice())) {
				return false;// not enough funds
			}
			int currentUnits = portfolio.investments.containsKey(stock.getName()) ? 
					portfolio.investments.get(stock.getName()) : 0;
			int unitsUpdated = currentUnits + unitNumbersRequested;
			portfolio.setCash(portfolio.cashAvailable - unitNumbersRequested * stock.getPrice());
			portfolio.addOrUpdateStock(stock, unitsUpdated);
			System.out.println("Buying " + unitNumbersRequested + 
					" unit(s) of " + stock.getName() + " for " + portfolio.investorName);
			return true;
		}
		
		@Override
		public String toString() {
			return portfolio.investorName + "requests to buy " + unitNumbersRequested + " units of " + stock.getName();
		}
	}
	
	public static class SellCommand implements StockOrder{
		public Stock stock;//request 
		public int unitNumbersRequested; //request
		public Portfolio portfolio;//receiver object
		
		public SellCommand(Stock stock, int unitNumbersRequested, Portfolio portfolio) {
			this.stock = stock;
			this.unitNumbersRequested = unitNumbersRequested;
			this.portfolio = portfolio;
		}

		@Override
		public boolean execute() {
			if(!portfolio.investments.containsKey(stock.getName())) {
				return false;// this person not having this stock at all!
			}
			
			if(unitNumbersRequested > portfolio.investments.get(stock.getName())) {
				return false;// not enough stock units to sell
			}
			
			int unitsLeft = portfolio.investments.get(stock.getName()) - unitNumbersRequested;
			portfolio.setCash(portfolio.cashAvailable + unitNumbersRequested * stock.getPrice());
			portfolio.addOrUpdateStock(stock, unitsLeft);
			System.out.println("Selling " + unitNumbersRequested + 
					" unit(s) of " + stock.getName() + " for " + portfolio.investorName);
			return true;
		}
		
		@Override
		public String toString() {
			return portfolio.investorName + "requests to sell " + unitNumbersRequested + " units of " + stock.getName();
		}
		
	}


	// invoker object
	public static class StockBroker {
		Queue<StockOrder> orderQueue = new ArrayDeque<>();
		List<StockOrder> failedOrders = new ArrayList<>();
		
		public void placeOrder(StockOrder order) {
			orderQueue.add(order);
			// batch proccessing order queue
			while(orderQueue.size() > 5) {
				StockOrder nextOrder = orderQueue.remove();
				processOrder(nextOrder);
			}
		}
		
		private void processOrder(StockOrder nextOrder) {
			if(!nextOrder.execute()) {
				failedOrders.add(nextOrder);
			}
		}
		
		public void showTradingDayTrades() {
			// finish order processing
			while(!orderQueue.isEmpty()) {
				StockOrder nextOrder = orderQueue.remove();
				processOrder(nextOrder);
			}
			
			// show processed trades
			System.out.println(failedOrders.size() + " orders failed : ");
			failedOrders.forEach(order -> System.out.println("	" +order.toString()));
		}
	}

	//client 
	public static void main(String[] args) {
		Stock appleStock = new Stock("APPL", 320.00);
		Stock amazonStock = new Stock("AMZON", 410.00);
		Stock shellStock = new Stock("SHEL", 15.00);
		
		Portfolio ashkanPortfolio = new Portfolio("Ashkan", 1000.00);
		Portfolio johnPortfolio = new Portfolio("John", 1500.00);
		
		StockBroker broker = new StockBroker();//invoker
		broker.placeOrder(new SellCommand(appleStock, 4, ashkanPortfolio));//should fail
		broker.placeOrder(new BuyCommand(amazonStock, 2, johnPortfolio));
		broker.placeOrder(new BuyCommand(shellStock, 1, johnPortfolio));
		broker.placeOrder(new BuyCommand(shellStock, 1, johnPortfolio));
		broker.placeOrder(new BuyCommand(shellStock, 5, ashkanPortfolio));
		broker.placeOrder(new BuyCommand(shellStock, 5, ashkanPortfolio));
		broker.placeOrder(new BuyCommand(shellStock, 1, johnPortfolio));
		broker.placeOrder(new BuyCommand(appleStock, 10, johnPortfolio));//should faild
		broker.placeOrder(new BuyCommand(appleStock, 1, ashkanPortfolio));
		broker.placeOrder(new SellCommand(shellStock, 2, ashkanPortfolio));
		broker.placeOrder(new SellCommand(shellStock, 1, johnPortfolio));
		
		broker.showTradingDayTrades();
		
		ashkanPortfolio.showPortfolio();
		johnPortfolio.showPortfolio();
		
		System.out.println("\n ** Encapsulate a request as an object \n"
				+ "command knows how to execute the request also has access to the receiver object, \n" + 
				" 		so can be looked as separate unit of work being executed anytime, also can\n" + 
				" 		queue different commands to be proceesed later - batch processing");
	}

}
