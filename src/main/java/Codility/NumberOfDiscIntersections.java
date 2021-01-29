package Codility;
import java.util.*;

/*
 * https://app.codility.com/programmers/lessons/6-sorting/
 * */
public class NumberOfDiscIntersections {
	
	
    static class Pair{
        int min;
        int max;
        Pair(int a, int b){
            this.min = a;
            this.max = b;
        }
        boolean intersect(Pair other){
            if(this.min == other.min || this.max == other.max
                || this.min == other.max || this.max == other.min) return true;
            if(this.min < other.min)
                return this.max >= other.min;
            else
                return this.min >= other.min;
        }
    }
    public static  int solution(int[] A) {
        int LIMIT = (int) Math.pow(10, 7);
        Map<Double, Pair> discsMap = new TreeMap<>();//sorted map on keys
        for(int i = 0; i < A.length; i++){
            double key = toDouble(i - A[i], i + A[i]);
            discsMap.put(key, new Pair(i - A[i], i + A[i]));
        }
        Double[] keys = discsMap.keySet().toArray(new Double[A.length]);
        int count  = 0;
        for(int i = 0 ; i < keys.length; i++){
        	if(count >= LIMIT) return -1;
            Pair a = discsMap.get(keys[i]);
            int j = i+1;
            while(j < keys.length &&  toInt(keys[j]) <= a.max && count < LIMIT){
                Pair b = discsMap.get(keys[j]);
                if(a.intersect(b)) count++;
                j++;
            }
        }
        return count;
    }

    static double toDouble(int min, int max){
        double a = (double) min;
        if(min==max) return a;
        int digits = ((int) (Math.log(max)/Math.log(10))) + 1;
        double b = max / Math.pow(10, digits);
        b = (a < 0) ? b * -1 : b;
        return a + b;
    }
    static int toInt(Double d){
        return (int) Math.floor(d);
    }
	

	public static void main(String[] args) {
		int[] case01 = new int[] {1, 0, 1, 0, 1};//expected : 6
		System.out.println(solution(case01));
		
		int[] case02 = new int[] {1, 5, 2, 1, 4, 0};//expected : 11
		System.out.println(solution(case02));
		
		
		System.out.println("Converting a pair of integers to a double (use this double as a unique key)");
		System.out.println("toDouble(-1,1) " + toDouble(-1,1));
		System.out.println("toDouble(1,1) " + toDouble(1,1));
		System.out.println("toDouble(1,3) " + toDouble(1,3));

		
		

	}

}
