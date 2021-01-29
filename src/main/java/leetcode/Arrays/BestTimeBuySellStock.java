package leetcode.Arrays;
/*  https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 * Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

 * 
 * */
public class BestTimeBuySellStock {
	
	
	/*  first approach: very length approach using this concept: buy low, sell high
	 *   (no more analysis done to make the algorithm more concise)
	 * */
	  public static  int maxProfit(int[] prices) {
		  if(prices.length < 2) return 0;//need at least 2 price 
	        int buyprice= -1;
	        int profit = 0;
	        for(int index = 0; index< prices.length -1;  index++){
	            if(buyprice == -1){//buy
	                if(prices[index+1] > prices[index]){//price going up -> buy signal
	                    buyprice = prices[index];
	                }
	            }else{//sell
	                if(prices[index+1] < prices[index]){//price going down -> sell signal
	                    profit += prices[index] - buyprice;
	                    buyprice = -1;//we can but again!
	                }
	            }
	        }
	        
	        if(buyprice!=-1){//reached to the end and have not sold yet
	               profit += prices[prices.length -1] - buyprice;
	        }
	        
	        return profit;
	        
	    }
	  
	  
	  /* more concise approach : by analyzing the algorithm, 
	   * 	the maximum profit is calculated by adding "all positive increments" (ignoring non-positive increments)
	   * 
	   * https://medium.com/@monisha.mary.mathew/best-time-to-buy-and-sell-stock-ii-b8c27bd9e54d
	   * */
	  public static int maxProfit02(int[] prices) {
	        int diff = 0;
	        for(int i = 1; i<prices.length; i++){
	            if(prices[i]>prices[i-1]){
	                diff += prices[i]-prices[i-1];
	            }
	        }
	        return diff;
	    }

	public static void main(String[] args) {
		int[] input01 = new int[] {7,1,5,3,6,4};
		int[] input02 = new int[] {1,2,3,4,5};
		int[] input03 = new int[] {7,6,4,3,1};
		int[] input04 = new int[] {5,5,5,5,5};
		int[] input05 = new int[] {3,4,4,4,4};
		int[] input06 = new int[] {3,4,4,4,3};
		int[] input07 = new int[] {1,2,7,5};


		System.out.println(" first approach");
		System.out.println(" maxProfit([7,1,5,3,6,4] : " + maxProfit(input01));
		System.out.println(" maxProfit([1,2,3,4,5] : " + maxProfit(input02));
		System.out.println(" maxProfit([7,6,4,3,1] : " + maxProfit(input03));
		System.out.println(" maxProfit([5,5,5,5,5] : " + maxProfit(input04));
		System.out.println(" maxProfit([3,4,4,4,4] : " + maxProfit(input05));
		System.out.println(" maxProfit([3,4,4,4,3] : " + maxProfit(input06));
		System.out.println(" maxProfit([3,4,4,4,3] : " + maxProfit(input07));

		System.out.println(" \nsecond approach");
		System.out.println(" maxProfit([7,1,5,3,6,4] : " + maxProfit02(input01));
		System.out.println(" maxProfit([1,2,3,4,5] : " + maxProfit02(input02));
		System.out.println(" maxProfit([7,6,4,3,1] : " + maxProfit02(input03));
		System.out.println(" maxProfit([5,5,5,5,5] : " + maxProfit02(input04));
		System.out.println(" maxProfit([3,4,4,4,4] : " + maxProfit02(input05));
		System.out.println(" maxProfit([3,4,4,4,3] : " + maxProfit02(input06));
		System.out.println(" maxProfit([3,4,4,4,3] : " + maxProfit02(input07));


	}

}
