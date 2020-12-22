package hackerank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/* https://www.hackerrank.com/challenges/largest-permutation/problem
 * 
 * You are given an unordered array of unique integers incrementing from .
 *  You can swap any two elements a limited number of times. 
 *  Determine the largest lexicographical value array that can be created by executing 
 *  	no more than the limited number of swaps.
 *  
 *  
 *  
 *  
 *  Index Array : 
when we have 'an unordered array of unique integers incrementing from 1' , 
since the values are in the same range of indices (1,n), we can create 'index Array' 	
	by switching values with indices --> this helps up as a value-index lookup (O(n) extra space)
 	array[index] = value ---> indexArray[value] = index   (indexArray is 1-based (NOT 0-based))
	exm: array :[1,5,3,2,4]
		indexArray: (value -> index)
		1->0
		2->3
		3->2
		4->4
		5->1
 * */
public class LargestPermutation {
    static int[] largestPermutationGeneralSolution(int k, int[] arr) {
        if(k >= arr.length){
            return Arrays.stream(arr).parallel().boxed()
            .sorted(Comparator.reverseOrder()).mapToInt(i -> (int)i).toArray();
        }
       
        int index = 0;
        while(k > 0 && index < arr.length){
            int maxIndex = findMaxAt(arr, index);
            if(maxIndex != index){//needs swap
                swap(arr, index, maxIndex);
                k--;
            }
            index++;
        }
        return arr;
    }
    static void swap(int[] arr, int ia, int ib){
        int temp = arr[ia];
        arr[ia] = arr[ib];
        arr[ib] = temp;
    }
    static int findMaxAt(int[] arr, int index){
        int maxIndex = index;
        for(int i = index; i < arr.length; i++){
            maxIndex = arr[i] > arr[maxIndex] ? i : maxIndex;
        }
        return maxIndex;
    }
    
    
    //-------------- 
    
    static int[] largestPermutationIndexArray(int k, int[] arr) {
        k = (k >= arr.length) ? arr.length : k;
        
        int[] indexArr = new int[arr.length + 1];//1-based
        for(int i = 0; i < arr.length; i++){
            indexArr[arr[i]] = i;
        }
        int index = 0;
        int valAtIndex = arr.length;
        while(k > 0 && index < arr.length){
            int ind = indexArr[valAtIndex];
            if(index != ind){//need to swap
            int arrayVal = arr[index];
            // valAtIndex -> arrayVal
            // index -> ind   
                swap(arr, index, ind);
                swap(indexArr, valAtIndex, arrayVal);
                k--;
            }
            index++;
            valAtIndex--;
        }
        return arr;
    }
    
    static void printIntArray(int[] arr) {
    	System.out.println(Arrays.stream(arr).mapToObj(Integer::new).collect(Collectors.toList()));
    }

	public static void main(String[] args) {
		
		System.out.println("Index Array : \n"
				+ "when we have 'an unordered array of unique integers incrementing from 1' , "
				+ "\nsince the values are in the same range of indices (1,n), we can create 'index Array' "
				+ "	\n	by switching values with indices --> this helps up as a value-index lookup (O(n) extra space)"
				+ "\n 	array[index] = value ---> indexArray[value] = index   (indexArray is 1-based (NOT 0-based))"
				+ "\n	exm: array :[1,5,3,2,4]"
				+ "\n		indexArray: (value -> index)"
				+ "\n		1->0"
				+ "\n		2->3"
				+ "\n		3->2"
				+ "\n		4->4"
				+ "\n		5->1");
		
		
		int[] unordered_unique_integers_incrementing_from_1 = new int[] {4, 2, 3, 5, 1};
		int[] random_non_unique_integers_starting_from_nonZero = new int[] {6,2,8,1,5,6};
		
		
		System.out.print("\nunordered_unique_integers_incrementing_from_1 : "); 
		printIntArray(unordered_unique_integers_incrementing_from_1);
		System.out.print("	largestPermutationIndexArray() -  1 swap: ");
		printIntArray(largestPermutationIndexArray(1, unordered_unique_integers_incrementing_from_1));
		
		System.out.print("\nrandom_non_unique_integers_starting_from_nonZero : "); 
		printIntArray(random_non_unique_integers_starting_from_nonZero);
		System.out.print("largestPermutationIndexArray() -  3 swap: ");
		try {
			printIntArray(largestPermutationIndexArray(3, random_non_unique_integers_starting_from_nonZero));

		}catch(ArrayIndexOutOfBoundsException e){
			System.out.print("indexArray concept does NOT work if"
					+ "\n	numbers are notnunique integers incrementing from 1 --> should use a general solution");
		}
		System.out.print("\nlargestPermutationGeneralSolution() -  3 swap: ");
		printIntArray(largestPermutationGeneralSolution(3, random_non_unique_integers_starting_from_nonZero));
	}

}
