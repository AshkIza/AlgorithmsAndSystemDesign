package leetcode.DP;
/* Given an array of non-negative integers, you are initially positioned at the first index of the array.

	Each element in the array represents your maximum jump length at that position.
	
	Determine if you are able to reach the last index.
	
	Example 1:
	
	Input: [2,3,1,1,4]
	Output: true
	Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
	Example 2:
	
	Input: [3,2,1,0,4]
	Output: false
	Explanation: You will always arrive at index 3 no matter what. Its maximum
	             jump length is 0, which makes it impossible to reach the last index.
	             
	solution : https://leetcode.com/problems/jump-game/solution/
	
	        This is a dynamic programming[1] question. Usually, solving and fully understanding a dynamic programming problem 
	        is a 4 step process:
					1-Start with the recursive backtracking solution
					2-Optimize by using a memoization table (top-down[2] dynamic programming)
					3-Remove the need for recursion (bottom-up dynamic programming)
					4-Apply final tricks to reduce the time / memory complexity
					
			Approach 1: Dynamic Programming Top-down: 
			Top-down Dynamic Programming can be thought of as optimized backtracking. 
			It relies on the observation that once we determine that a certain index is good / bad, this result will never change.
		 	This means that we can store the result and not need to recompute it every time. 
		 		Time complexity : O(n^2)
		 		Space complexity : O(2n) = O(n)O(2n)=O(n). 
		 			First n originates from recursion. 
		 			Second n comes from the usage of the memo table.
		 			
		 	Approach 2: Dynamic Programming Bottom-up
		 	Top-down to bottom-up conversion is done by eliminating recursion(recursion : top-down). 
		 	In practice, this achieves better performance as we no longer have the method stack overhead and might even benefit 
		 	from some caching. More importantly, this step opens up possibilities for future optimization.
			The "recursion is usually eliminated" by trying to "reverse the order of the steps from the top-down approach."

		
 * */
public class JumpGame {
	
    public boolean canJumpBottomUp(int[] nums) {
        int[] mem = new int[nums.length-1];
        //return canJumpRec(0, nums, mem);
        if(nums.length == 1){
            return true;
        }
        for(int i = nums.length-2; i >= 0; i--){
            mem[i] = -1;
            int distance = nums.length - 1 -i;
            if(nums[i] >= distance){
                mem[i] = 1;
            }else{
                for(int step = 1; step <= nums[i]; step++){
                    if(i+step < nums.length-1 && 
                      mem[i+step] == 1){
                        mem[i] = 1;
                        break;
                        //NOT return..return gets out of all loops
                    }
                }
            }
        }
        return mem[0] == 1;
    }
    /*    for (Type type : types) {
		    for (Type t : types2) {
		         if (some condition) {
		             // Do something and break...
		             break; // Breaks out of the inner loop
		         }
		    }
		 }
		 
		for (Type type : types) {
	        for (Type t : types2) {
	            if (some condition) {
	                // Do something and break...
	                return;// return from whole function
	            }
	        }
	    }
     * 
     * */
    
    
    
    public boolean canJumpTopDown(int[] nums) {
        int[] mem = new int[nums.length-1];
        return canJumpRecursive(0, nums, mem);
     
    }
    
    private boolean canJumpRecursive(int index, int[] nums, int[] mem) {
        if(index == nums.length-1){
            return true;
        }
        if(mem[index] != 0){
            return mem[index]==1;
        }
        if(index< nums.length-1 && nums[index]== 0){
            return false;
        }
        boolean value = false;
        int maxJump = nums[index];
        int maxIndex = (index+maxJump >=nums.length-1) ? nums.length-1 : index+maxJump;
        for(int i = index+1; i<=maxIndex; i++){
            if(canJumpRecursive(i, nums, mem)){
                value = true;
            }
        }
        mem[index] = (value) ? 1 : -1;
        return value;
    }

	public static void main(String[] args) {
		JumpGame jumpGame = new JumpGame();
		System.out.println("jumpGame.canJumpTopDown(new int[]{0}) : " + jumpGame.canJumpTopDown(new int[]{0}));
		System.out.println("jumpGame.canJumpTopDown(new int[]{2,3,1,1,4}) : " + jumpGame.canJumpTopDown(new int[]{2,3,1,1,4}));
		System.out.println("jumpGame.canJumpTopDown(new int[]{3,2,1,0,4}) : " + jumpGame.canJumpTopDown(new int[]{3,2,1,0,4}));
		
		System.out.println("\nTop-down to bottom-up conversion is done by eliminating recursion (no stack overhead)\n ");

		System.out.println("jumpGame.canJumpBottomUp(new int[]{0}) : " + jumpGame.canJumpBottomUp(new int[]{0}));
		System.out.println("jumpGame.canJumpBottomUp(new int[]{2,3,1,1,4}) : " + jumpGame.canJumpBottomUp(new int[]{2,3,1,1,4}));
		System.out.println("jumpGame.canJumpBottomUp(new int[]{3,2,1,0,4}) : " + jumpGame.canJumpBottomUp(new int[]{3,2,1,0,4}));

		System.out.println("\nHow do I break out of nested loops in Java?");
		System.out.println("https://stackoverflow.com/questions/886955/how-do-i-break-out-of-nested-loops-in-java");



		
	}

}
