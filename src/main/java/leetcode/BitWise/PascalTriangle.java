package leetcode.BitWise;

import java.util.ArrayList;
import java.util.List;

/* Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

solution: https://www.programcreek.com/2014/03/leetcode-pascals-triangle-java/
(loop)
i used receursive
 * 
 * */
public class PascalTriangle {
	
	   public static List<List<Integer>> generate(int numRows) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if(numRows <= 0 ){
	            return result;
	        }
	        List<Integer> firstRow = new ArrayList<>();
	        firstRow.add(1);
	        result.add(firstRow);
	        if(numRows == 1){
	            return result;
	        }else {
	            return generateRec(result, numRows);
	        }
	    }
	    
	    private static List<List<Integer>> generateRec (List<List<Integer>> allRows, int numRows){
	        int n = allRows.size();
	        if( n >= numRows){
	            return allRows;
	        }
	        List<Integer> prev = allRows.get(n-1);
	        List<Integer> next = new ArrayList<>();
	        next.add(1);
	        for(int i = 1; i < prev.size(); i++){
	            int val = prev.get(i-1) + prev.get(i);
	            next.add(val);
	        }
	        next.add(1);
	        allRows.add(next);
	        return generateRec(allRows, numRows);
	    }

	public static void main(String[] args) {
		System.out.println( generate(5));
	}

}
