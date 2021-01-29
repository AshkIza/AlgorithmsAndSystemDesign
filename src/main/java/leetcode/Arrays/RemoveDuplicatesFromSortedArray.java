package leetcode.Arrays;

/*   Remove Duplicates from Sorted Array
 *    
 *    https://leetcode.com/explore/interview/card/top-interview-questions-easy/92/array/727/
 * 
 * */
public class RemoveDuplicatesFromSortedArray {
	
	public static int removeDuplicates(int[] nums) {
        if(nums.length <= 1) return nums.length;
        int index = nums.length-1;
        int num = 1;
        int lastvalue = nums[nums.length-1];
        index--;
        while(index >=0){
            if(nums[index] == lastvalue){//duplicate removal
                for(int i = index; i< index+num; i++){
                    nums[i] = nums[i+1];
                }
                
            }else{
                lastvalue = nums[index];
                num++;
            }
            index--;
        }
        return num;
    }

	public static void main(String[] args) {
		int[] nums =  {0,0,1,1,1,2,2,3,3,4};
		System.out.println("removeDuplicates() : " + removeDuplicates(nums));		

	}

}
