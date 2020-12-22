package Udemy;
/* spiral(3)
 *  1  2  3 
 	8  9  4 
 	7  6  5 
 	
 	spiral(4)
 	 1  2  3  4 
	12 13 14  5 
	11 16 15  6 
	10  9  8  7 
 * 
 * */

public class matrixSpiral {
	
	public static int[][] matrixSpiral(int N){
		int[][] matrix = new int[N][N];
		spiralRecursive(0, 0, 0, N-1, matrix, 1);
		return matrix;
	}
	
	public static void spiralRecursive(int i, int j, int min, int max, int[][] matrix, int value){
		int N = matrix[0].length;
		//increment column
		i=min;
		for(j= min; j <= max; j++) {
			matrix[i][j]= value;
			value++;
		}
		//increment row j=max; i=min
		j = max;//enforce j to max (not getting index out of bound)
		for(i= min+1; i<=max; i++) {
			matrix[i][j] = value;
			value++;
		}
		//decrement column i=max j=max
		i = max;//enforce i to max (not getting index out of bound)
		for(j= max-1; j>=min; j--) {
			matrix [i][j] = value;
			value++;
		}
		//decremnet row i=max j=min
		j = min;//enforce i to min (not getting index out of bound)
		for(i = max-1; i > min; i--) {// i--> min+1
			matrix[i][j] = value;
			value++;
		}
		i = min + 1;///enforce i  (not getting index out of bound)
		if(min+1 <= N && max-1>= 0 &&  min+1 <= max-1) {
			spiralRecursive(i, j, min+1, max-1, matrix, value);

		}
	}

	
	
	public static void main(String[] args) {
		int N = 4;
		int[][] m = matrixSpiral(N);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				String string = " ";
				if( m[i][j] <10) {
					string = ' '+  String.valueOf(m[i][j]) + " ";
				}else {
					string =  String.valueOf(m[i][j]) + " ";

				}
				System.out.print(string);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println(" weird behavior when loop variable is defined before loop (not local)") ;
		int i = 0;
		for(i = 1; i <= 4 ; i++) {
			System.out.println("in loop : for(i = 1; i <= 4 ; i++) ; i=" + i);
		}
		System.out.println("after the loop ; i=" + i);
		
		
		int j = 0;
		for(j = 1; j < 5 ; j++) {
			System.out.println("in loop : for(j = 1; j < 5 ; j++) ; j=" + j);
		}
		System.out.println("after the loop ; j=" + j);

		
	}

}
