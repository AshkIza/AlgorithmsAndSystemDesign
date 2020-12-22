package Udemy;

/* write  programm that for input of n (positive integer), 
 * prints out the numbers for 1 to n. but, 
 * for multiples of 3 , print "fizz"
 * for multiples of 5, print "buzz"
 * for multiples for 5 & 3, print "fizzBuzz"
 * */
public class fizzBuzz {

	public static void fizzBuzz(int number) {
		System.out.println("fizzBuzz(" + number + ")");
		if(number < 1) {
			return;
		}
		
		for(int index = 1; index <= number; index++) {
			boolean module3 = index % 3 == 0;
			boolean module5 = index % 5 == 0;
			if(module3 && module5) {
				System.out.println("fizzBuzz");
			}else if(module3) {
				System.out.println("fizz"); 
			}else if(module5) {
				System.out.println("buzz");
			}else {
				System.out.println(String.valueOf(index));
			}
		}
		System.out.println();
		
	}
	public static void main(String[] args) {
		int[] numbers = new int[] {-2, 0, 1, 3, 5, 7, 9, 15 };
		for(int number : numbers) {
			fizzBuzz(number);
		}

	}

}
