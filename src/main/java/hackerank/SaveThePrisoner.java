package hackerank;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/* https://www.hackerrank.com/challenges/save-the-prisoner/problem
 * 
 * A jail has a number of prisoners and a number of treats to pass out to them.
 *  Their jailer decides the fairest way to divide the treats is to seat the prisoners
 *   around a circular table in sequentially numbered chairs. 
 *   A chair number will be drawn from a hat. 
 *   Beginning with the prisoner in that chair,
 *    one candy will be handed to each prisoner sequentially around the table until 
 *    all have been distributed.

The jailer is playing a little joke, though. 
The last piece of candy looks like all the others, but it tastes awful.
 Determine the chair number occupied by the prisoner who will receive that candy.
 * */
public class SaveThePrisoner {
	static Random rd = new Random();

	static Supplier<IntStream> randomIntGenerator = () -> {
		int n = Math.abs(rd.nextInt()) % 1000;
		int m = Math.abs(rd.nextInt()) % 100000;
		int s = Math.abs(rd.nextInt()) % n;
		return IntStream.of(n, m, s);
	};
	
    static int saveThePrisoner(int n, int m, int s) {
        int candiesLedft = m % n;
        int poison = s + candiesLedft - 1;
        return (poison <=n && poison >= 1 ) ? poison : Math.abs(poison - n); 
    }

	public static void main(String[] args) {
		/*  Generate Random Integer Numbers 
		 * https://www.tutorialspoint.com/generate-random-integer-numbers-in-java
		 * 
		 * Using a stream to iterate n times instead of using a for loop to create n items
		 * https://stackoverflow.com/questions/28080703/using-a-stream-to-iterate-n-times-instead-of-using-a-for-loop-to-create-n-items/28081866
		 * 
		 * */
		System.out.println("Random random =  new Random()");
		Random random =  new Random();
		System.out.println("	random.nextInt() : " + random.nextInt() +
				" , \n	random.nextLong() : " + random.nextLong() + 
				"\n	random.nextDouble() : " + random.nextDouble() +
				"\n	random.nextBoolean() : " + random.nextBoolean());
		System.out.println("use stream to iterate n times");
		System.out.println("IntStream.range(0,n)\n");

		IntStream.range(0,4).forEach( i -> {
			int[] nms = randomIntGenerator.get().toArray();
			System.out.println(nms[0] + " prisinors, " + nms[1] + " candies, strating with prisoner " 
					+ nms[2] + " ->  " +  saveThePrisoner(nms[0],nms[1],nms[2]) + "-th prisonor got poisoned!");
		} );

	}

}
