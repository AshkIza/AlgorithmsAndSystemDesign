package leetcode.DP;

/*  Best Time to Buy and Sell Stock
		Say you have an array for which the ith element is the price of a given stock on day i.
		
		If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
		
		Note that you cannot sell a stock before you buy one.
		
		Example 1:
		
		Input: [7,1,5,3,6,4]
		Output: 5
		Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
		             Not 7-1 = 6, as selling price needs to be larger than buying price.
		Example 2:
		
		Input: [7,6,4,3,1]
		Output: 0
		Explanation: In this case, no transaction is done, i.e. max profit = 0.
		
	Solution : https://www.programcreek.com/2014/02/leetcode-best-time-to-buy-and-sell-stock-java/
 * 
 * */
public class BestTimeBuySellStock {
	
	
	   /*
	    * */
	   public static int maxProfit(int[] prices) {
	        if(prices.length == 0 || prices.length == 1){
	            return 0;
	        }
	        int highestSellPrice = prices[prices.length - 1];
	        int maxProfit = 0;
	        for(int i = prices.length -2; i>=0; i--){
	            highestSellPrice = Math.max(highestSellPrice, prices[i+1]);//tabulized value 
	            maxProfit = Math.max(maxProfit, highestSellPrice - prices[i]);
	        }
	        return maxProfit;
	    }

	public static void main(String[] args) {
		System.out.println("maxProfit(new int[] {7, 1, 5, 3, 6, 4} ) : "  + maxProfit(new int[] {7, 1, 5, 3, 6, 4} ) );
		System.out.println("maxProfit(new int[] {7, 6, 4, 3, 1} ) : "  + maxProfit(new int[] {7, 6, 4, 3, 1} ) );
		System.out.println("maxProfit(new int[] {7} ) : "  + maxProfit(new int[] {7} ) );
		
		System.out.println(" \nmaximization problem --> screams for Dynamic Problamming " );
		System.out.println(" 	Dynamic Problamming --> trying to break in problem to 'overlapping' sub-problems " );
		System.out.println(" 	     'overlapping' sub-problems --> saving the subproblem results in mem-Cache(random and recursive) or tabulized (iteratively)" );
		System.out.println(" 	        since sub-problems are 'overlapping' -->  results from previous steps help us to build results for next steps" );


	}

}
