package hackerank;
/*  https://www.hackerrank.com/challenges/halloween-sale/problem
 * 
 *  Specifically, the first game you buy during the sale will be sold at  dollars,
 *   but every subsequent game you buy will be sold at exactly  dollars less than 
 *   the cost of the previous one you bought. This will continue until the cost becomes 
 *   less than or equal to  dollars, 
 *  after which every game you buy will cost  dollars each.
 * 
 * 
 * */

public class HalloweenSale {
	
	 static int howManyGames(int p, int d, int m, int s) {
	        if(s < p) return 0;
	        int remainingBudget = s;
	        int purchaseCount = 0;
	        int nextPrice = nextPurchacePrice(p, purchaseCount, d, m);
	        while(nextPrice <= remainingBudget){
	            remainingBudget -= nextPrice;
	            purchaseCount++;
	            nextPrice = nextPurchacePrice(p, purchaseCount, d, m);
	        }
	        return purchaseCount;
	    }
	    
	    static int nextPurchacePrice(int initialPrice, 
	        int purchaseCount, int discout, int floorPrice){
	        int price01 = initialPrice - (purchaseCount * discout);
	        return price01>=floorPrice ? price01 : floorPrice;
	    }

	public static void main(String[] args) {
		System.out.println("howManyGames(20, 3, 6, 80) ? " + howManyGames(20, 3, 6, 80) );

	}

}
