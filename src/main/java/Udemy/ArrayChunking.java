package Udemy;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;

// int[] numbers = {1, 2, 3, 4, 5, 6, 7};
// int[][] chunks = chunkArray(numbers, 3);
//
// chunks now contains [
//                         [1, 2, 3],
//                         [4, 5, 6],
//                         [7]
//                     ]

//int[][] in 2-dimensional Arrays, 
//inner Arrays can have different sizes! (like above!)
public class ArrayChunking {
	
	
	//easier solution (if they let you use of ArrayCopy java functions)
	//trick for this problem: use java built-in 'copy Array' methods to simplify your code
	/*public static int[][] chunkArrayMethod(int[] array, int chuckSize){
			//TODO		
	}*/


	//not using any java helper functions (not an easy solution!)
	//might make bunch of mistakes while implementing
	public static int[][] chunkArrayMethod02(int[] array, int chuckSize){
		int arraySize = array.length;
		if(chuckSize > arraySize) {
			int[][] chuncks = new int[1][arraySize];
			chuncks[0] = array;
			return chuncks;
		}
		
		int numOfchunks = arraySize / chuckSize;
		if(arraySize % chuckSize != 0) {
			numOfchunks++;//extra sub-array for the remaining
		}
		int[][] chuncks = new int[numOfchunks][];

		int[] subArray = new int[chuckSize];//first sub-array
		for(int index = 0; index < arraySize; index++) {
			subArray[index%chuckSize] = array[index];
			if( (index + 1) % chuckSize == 0) {
				//last element of sub-array
				chuncks[index/chuckSize] = subArray;
				int subArraySize = Math.min(arraySize - index - 1 , chuckSize); 
				subArray = new int[subArraySize];//create next sub-array
			}
		}
		chuncks[numOfchunks - 1] = subArray;//last sub-array
 
		return chuncks;
	}

	public static void main(String[] args) {
		int[] array01 = new int[] {1,2,3,4};
		int[] array02 = new int[] {1,2,3,4,5};
		int[] array03 = new int[] {1,2,3,4,5,6,7,8};
		
		int[][] list = chunkArrayMethod02(array02, 20);
		System.out.println("[");
	   for(int i = 0 ; i < list.length; i++) {
			System.out.print("  [");
			for(int j = 0 ; j < list[i].length; j++) {
				if(j!=0) {
					System.out.print(", ");
				}
				System.out.print(list[i][j]);
			}
			System.out.println("]");
		}
		System.out.println("]");
	}
	
	//How to split a string array into small chunk arrays in java?
    //https://stackoverflow.com/questions/27857011/how-to-split-a-string-array-into-small-chunk-arrays-in-java
	
    //copy Arrays
	//TODO
}
