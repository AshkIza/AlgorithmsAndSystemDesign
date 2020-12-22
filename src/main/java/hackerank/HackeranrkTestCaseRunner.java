package hackerank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class HackeranrkTestCaseRunner<T, U, R> {
	
    private static final Scanner scanner = new Scanner(System.in);
    
	
	public void BiFunctionRun(T firstArg, U secondArg , BiFunction<T, U, R> bifunction) throws IOException {
		
		BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));


	        int t = scanner.nextInt();
	        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	        for (int tItr = 0; tItr < t; tItr++) {
	            int m = scanner.nextInt();
	            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	            int n = scanner.nextInt();
	            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	            int[] arr = new int[n];

	            String[] arrItems = scanner.nextLine().split(" ");
	            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

	            for (int i = 0; i < n; i++) {
	                int arrItem = Integer.parseInt(arrItems[i]);
	                arr[i] = arrItem;
	            }

				R result = bifunction.apply(firstArg, secondArg);
				if(result instanceof Object[]) {
					Object[] r = (Object[]) result;
					 for (int i = 0; i < r.length; i++) {
			                bufferedWriter.write(String.valueOf(r[i]));
			         }
					
				}else if (result instanceof List) {
					List<Object> r = (List<Object>) result;
					int c = 0;
					 for (Object obj : r) {
			                bufferedWriter.write(String.valueOf(obj));
			         }
					
				}else {
					bufferedWriter.write(String.valueOf(result));
				}

	            bufferedWriter.newLine();
	        }

	        bufferedWriter.close();

	        scanner.close();
		
	}

}
