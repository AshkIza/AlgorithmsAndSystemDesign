package hackerank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


/*  Sunny and Johnny like to pool their money and go to the ice cream parlor. 
 * Johnny never buys the same flavor that Sunny does. 
 * The only other rule they have is that they spend all of their money.

Given a list of prices for the flavors of ice cream, 
	select the two that will cost all of the money they have.
 * 
 * 
 * ------> There will always be a unique solution.

 * 
 * */
public class IceCreamParlor {
	
	
    static int[] icecreamParlorAlwaysSolution(int m, int[] arr) {
    	int result[] = new int[2];
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			int x = arr[i];
			int y = m - x;
			Integer j = map.get(y);
			if (j != null) {
				result[0] = j + 1;
				result[1] = i + 1;
				break;
			}
			map.put(x, i);
		}
		// since we know ----> there is always a unique solution, 
		// by end of creating this map, we should already visited our solution while constructing the map 
		return result;
    }

	
    static int[] icecreamParlorNoSolutionGuarantee(int m, int[] arr) {
    	
        Map<Integer, Integer> valueIndex = new HashMap<>();
        for(int index = 0; index < arr.length; index++){
            if(arr[index] < m){
                if(valueIndex.containsKey(arr[index]) && m == arr[index] * 2){//avoid repeated numbers
                    int firstIndex = valueIndex.get(arr[index]) + 1;//1-based
                    return new int[]{firstIndex, index+1};
                }else{
                    valueIndex.put(arr[index], index);
                }
            }
        }
        for(int key : valueIndex.keySet()){
            if(valueIndex.containsKey(m - key) && (m - key != key)){// make sure we are dealing we 2 separate keys
                int first = valueIndex.get(key) + 1;
                int second = valueIndex.get(m - key) + 1;
                return new int[] {Math.min(first, second), Math.max(first, second)};
            }
        }
        return new int[2];//nothing found
    }
    
	public static void main(String[] args) throws IOException {
		
		int[] arr = {830, 22, 160, 654, 466, 327, 607, 831, 265, 638, 476, 111, 556, 441, 448, 
				894, 260, 25, 655, 384, 367, 512, 650, 867, 723, 216, 199, 85, 825, 426, 782, 
				467, 333, 864, 226, 286, 250, 809, 545, 32, 861, 260, 390, 293, 951, 413, 860, 196, 370,
				360, 790, 704, 71, 852, 564, 251, 375, 169, 403, 114, 458, 948, 729, 534, 306, 679, 792, 725, 
				947, 75, 513, 880, 54, 546, 437, 584, 728, 712, 425, 538, 471, 325, 504, 106, 605, 441, 644, 426, 452, 495,
				248, 273, 549, 344, 874, 802, 678, 771, 43, 2, 574, 504, 950, 156, 849, 219, 440, 754, 766, 998, 964, 51,
				92, 396, 237, 697, 858, 791, 785, 950, 378, 363, 485, 324, 76, 682, 780, 885, 120, 858, 2, 956, 23, 827, 377, 771, 182, 885, 513, 891, 520, 386, 152, 890, 744, 777, 988, 723, 3, 784, 268, 519, 826, 760, 56, 842, 83, 370, 130, 218, 687, 493, 292, 815, 758, 902, 28, 312, 293, 635, 575, 645, 200, 321, 37, 992, 1000, 495, 169, 763, 378, 92, 183, 762, 143, 839, 430, 927, 73, 749, 968, 981, 359, 546, 726, 714, 894, 897, 377, 719, 803, 240, 783, 320, 747, 152, 979, 293, 191, 163, 154, 526, 508, 658, 893, 186, 972, 629, 580, 218, 365, 309, 952, 756, 673, 857, 75, 447, 184, 196, 715, 194, 866, 500, 162, 994, 92, 423, 525, 478, 586, 236, 120, 253, 417, 124, 644, 406, 464, 162, 282, 380, 204, 37, 209, 928, 175, 698, 731, 22, 249, 794, 392, 723, 880, 81, 12, 512, 828, 235, 646, 86, 706, 538, 109, 199, 369, 730, 835, 5, 743, 468, 567, 915, 23, 207, 312, 512, 404, 58, 365, 680, 964, 225, 682, 642, 344, 619, 367, 651, 138, 17, 361, 551, 821, 669, 128, 849, 671, 853, 992, 122, 734, 372, 362, 815, 793, 296, 995, 992, 46, 882, 897, 424, 445, 600, 668, 872, 450, 206, 100, 706, 560, 739, 316, 93, 817, 781, 251, 800, 411, 475, 666, 237, 62, 966, 804, 500, 240, 125, 606, 907, 276, 327, 91, 320, 45, 817, 466, 884, 2, 621, 185, 901, 525, 268, 946, 696, 151, 393, 415, 558, 503, 183, 663, 529, 671, 210, 590, 674, 341, 728, 408, 41, 950, 197, 669, 36, 669, 41, 655, 526, 585, 823, 357, 587, 254, 853, 934, 894, 794, 616, 593, 837, 863, 930, 15, 691, 849, 355, 3, 860, 205, 219, 208, 806, 91, 831, 971, 465, 57, 94, 217, 939, 469, 842, 194, 731, 630, 825, 17, 477, 226, 550, 337, 799, 825, 623};
		int[] inta = icecreamParlorNoSolutionGuarantee(72, arr);
		System.out.println(inta[0] + " " + inta[1]);//407 421
		
		int[] arr02 = {1, 4, 5, 3, 2};
		int[] inta02 = icecreamParlorNoSolutionGuarantee(4, arr02);
		System.out.println(inta02[0] + " " + inta02[1]);
		
		
		int[] arr03 = {1, 4, 5, 3, 2};// not a guaranteed solution
		int[] inta03 = icecreamParlorNoSolutionGuarantee(10, arr03);
		int[] inta04 = icecreamParlorAlwaysSolution(10, arr03);

		
		System.out.println(inta03[0] + " " + inta03[1]);
		System.out.println(inta04[0] + " " + inta04[1]);
		HackeranrkTestCaseRunner<Integer, Integer[], Integer[]> runner = new HackeranrkTestCaseRunner<>();
	}

}
