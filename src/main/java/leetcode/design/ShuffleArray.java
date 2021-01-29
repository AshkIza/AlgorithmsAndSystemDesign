package leetcode.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ShuffleArray {
	
	    int[] original;
	    Random random = new Random();

	    public ShuffleArray(int[] nums) {
	        original = nums;
	    }
	    
	    /** Resets the array to its original configuration and return it. */
	    public int[] reset() {
	        return original;
	    }
	    
	    /** Returns a random shuffling of the array. */
	    public int[] shuffleBruteforce() {
	        List<Integer> indices = new ArrayList<>();
	        for(int i = 0 ; i < original.length; i++){
	            indices.add(i);
	        }
	        
	        int[] result = new int[original.length];
	        int count = 0;
	        while(!indices.isEmpty()){
	            int index = Math.abs(random.nextInt()) % indices.size();
	            int originalindex = indices.get(index);
	            result[count] = original[originalindex];
	            indices.remove(index);
	            count++;
	        }
	        return result;
	    }
	    
	    public int[] shuffle() {
	        int[] result = Arrays.copyOf(original, original.length);
	        int N = result.length;
	        for(int i = 0 ; i < N; i++){
	            int swapIndex = Math.abs(random.nextInt()) % N;
	            swap(result, i, swapIndex);
	        }
	        return result;
	    }
	    private void swap(int[] array, int a, int b){
	        int t = array[a];
	        array[a] = array[b];
	        array[b] = t;
	    }

	public static void main(String[] args) {
		ShuffleArray suffleArray = new ShuffleArray(new int[] {1,2,3,4,5,6,7,8});
		System.out.println("suffleArray.reset() : " + Arrays.toString(suffleArray.reset()));
		System.out.println("suffleArray.shuffle()" + Arrays.toString(suffleArray.shuffle()));
		System.out.println("suffleArray.shuffle()" + Arrays.toString(suffleArray.shuffle()));
		System.out.println("suffleArray.shuffle()" + Arrays.toString(suffleArray.shuffle()));
		System.out.println("suffleArray.reset() : " + Arrays.toString(suffleArray.reset()));


	}

}
