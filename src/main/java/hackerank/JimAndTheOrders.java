package hackerank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*   https://www.hackerrank.com/challenges/jim-and-the-orders/problem
 * 
 * Jim's Burgers has a line of hungry customers.
 *  Orders vary in the time it takes to prepare them.
 *   Determine the order the customers receive their orders
 *  Start by numbering each of the customers from  to , front of the line to the back. 
 *  You will then be given an order number and a preparation time for each customer.

The time of delivery is calculated as the sum of the order number and the preparation time.
 If two orders are delivered at the same time, assume they are delivered in ascending customer number order.
 * 
 * 
 * */
public class JimAndTheOrders {
	
	   static class Serveredtime {
	        Integer servedtime;
	        Integer customerNum;
	        Serveredtime(Integer servedtime, int customerNum){
	            this.servedtime = servedtime;
	            this.customerNum = customerNum;
	        }
	    }

	    // Complete the jimOrders function below.
	    static int[] jimOrders(int[][] orders) {
	        int N = orders.length;
	        List<Serveredtime> servredList = new ArrayList<>();
	        for(int i = 0; i < N ; i++){
	            servredList.add( new Serveredtime(orders[i][0] + orders[i][1], i+1));
	        }
	        return servredList.stream()
	            .sorted((s1, s2) -> s1.servedtime.compareTo(s2.servedtime))
	            .map(s-> s.customerNum).mapToInt(i -> (int) i).toArray();
	    }

	public static void main(String[] args) {
		int[][] case01 = new int[][] { {1,3},
									{2,3},
									{3,3}};
	  System.out.println("jimOrders(case01) : " + 
			  Arrays.stream(jimOrders(case01)).mapToObj(Integer::new).collect(Collectors.toList()));
	  
	  int[][] case02 = new int[][] { 
		  {8, 1},
		  {4, 2},
		  {5, 6},
		  {3, 1},
		  {4, 3}};
		  System.out.println("jimOrders(case02) : " + 
				  Arrays.stream(jimOrders(case02)).mapToObj(Integer::new).collect(Collectors.toList()));
	  
	}

}
