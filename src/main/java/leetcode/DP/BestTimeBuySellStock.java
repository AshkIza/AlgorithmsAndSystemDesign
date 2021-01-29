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
		    if(prices.length < 2) return 0;
	        int highestSellPrice = prices[prices.length -1];
	        int maxProfit = -1;
	        for(int index = prices.length -2; index >= 0; index--){
	            //buy at index 
	            int profitAtIndex = highestSellPrice - prices[index];
	            maxProfit = Math.max(profitAtIndex, maxProfit);
	            //sell at index
	            highestSellPrice = Math.max(highestSellPrice, prices[index]);
	        }
	        return maxProfit <= 0 ? 0 : maxProfit;
	    }

	public static void main(String[] args) {
		System.out.println("maxProfit(new int[] {7, 1, 5, 3, 6, 4} ) : "  + maxProfit(new int[] {7, 1, 5, 3, 6, 4} ) );
		System.out.println("maxProfit(new int[] {7, 6, 4, 3, 1} ) : "  + maxProfit(new int[] {7, 6, 4, 3, 1} ) );
		System.out.println("maxProfit(new int[] {7} ) : "  + maxProfit(new int[] {7} ) );
		
		System.out.println("NOTE on maximization problems:");
		System.out.println("	maximization problem --> screams for Dynamic programming ");
		System.out.println("	Dynamic programming --> trying to break in them problem into 'overlapping' sub-problem");
		System.out.println("	'overlapping' sub-problems --> saving the subproblem results (recursively or iteratively).");
		System.out.println("	recursively -> memoizedCache (top to bottom approach)");
		System.out.println("	iteratively -> tabulation (bottom up approach)");

		System.out.println("NOTE on maximization problems on Arrays (Dynamic Programming with Arrays):");
		System.out.println("	Iterate through array (recursive solution is not best performing) -> tabular approach");
		System.out.println("	tabular approach (bottom-up)—> approach array from end to being (bottom-up) to build your tabular results");

		
	}

}
