package BackTracking;

import java.util.ArrayList;
import java.util.List;

/* 
 * https://iq.opengenus.org/subset-sum-problem-backtracking/
 * https://www.geeksforgeeks.org/subset-sum-backtracking-4/
 * 
 * o(n)= 2^n (each element can be kept or ignored
 * 
 *  Tree Traversal Vs Backtracking (state-space tree):
 *     Tree traversal goes only downward VS 
 *     Backtracking goes downward and (if bounding function return false) goes upward 
 *     --> the passing Object to recursive function gets mixed up once call stack goes up again
 *       --> for backtracking: need to create new recursive function object argument once going down
 *              ( generating new state-space tree branch )
 * 
 * */
public class SubsetSumBacktracking {
	
    public static void findSumSubSet(int[] array, int sum) {
		System.out.println("\nfindSumSubSet(array, " + sum + ")");
    	if(!sumSubset(array, 0, sum , new ArrayList<Integer>())){
    		System.out.println(" NO solution found for sum of : " + sum);
    	}
    }
    
    private static boolean sumSubset(int[] array, int index, int rSum, List<Integer> subset) {
    	if(index >= array.length || rSum < 0){
    		return false;//bounding function
    	}
    	
    	boolean ignore = sumSubset(array, index+1, rSum, subset);//path 1
    	boolean keep = false;//path 2
    	int remain = rSum - array[index];
    	if(remain >= 0 ){//bounding function
    		List<Integer> subset02 = new ArrayList<>(subset);
    		//use a separate holder for this branch , otherwise recursive calls from other branch(ignore) get mixed with this one
			// after applying bounding function, we go furthur down by generating next layer of state-space tree branch 
    		subset02.add(array[index]);
    		if(remain == 0 ){
    			keep = true;// solution found! print it
    			print(subset02);
    		}else if(index + 1 < array.length){
    			keep = sumSubset(array, index+1, remain, subset02);
    			// passing new passing argument to the recursive function
                //( not to get mixed with upper level of  state-space tree)
    		}
    	}
    	return ignore || keep;
    }
    
    private static void print(List<Integer> subset) {
    	String sol = " [";
    	for(int i = 0 ; i < subset.size(); i++){
    		if(i == subset.size() - 1){
        		sol += subset.get(i) + "] ";

    		}else{
        		sol += subset.get(i) + ", ";
    		}
    	}
  	     System.out.println(sol);  
    }

	public static void main(String[] args) {
		System.out.println("\n\narray = {8, 3, 2, 10, 7, 5} ");
		 int[] array = {8, 3, 2, 10, 7, 5};
	        findSumSubSet(array, 8);
	        findSumSubSet(array, 12);
	        findSumSubSet(array, 25);
	        findSumSubSet(array, 27);
	        findSumSubSet(array, 50);
	        findSumSubSet(array, 20);
	        
		System.out.println("\n\narray = {10, 7, 5, 18, 12, 20, 15} ");
		int[] array02 = {10, 7, 5, 18, 12, 20, 15};
        findSumSubSet(array02, 8);
        findSumSubSet(array02, 35);

	}

}
