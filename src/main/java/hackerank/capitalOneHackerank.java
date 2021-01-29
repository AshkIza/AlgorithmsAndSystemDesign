package hackerank;


import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

/*
* Question 1: minSum
* https://stackoverflow.com/questions/57086537/optimization-of-a-java-method
*
*
* Questio 2: findSmallestDivisor
* https://leetcode.com/discuss/interview-question/427484/mathworks
*
*
*
* */
class capitalOneHackerank {

        /*
         * Complete the 'minSum' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY num
         *  2. INTEGER k
         */

        public static int minSum(List<Integer> num, int k) {
            PriorityQueue<Integer> maxheap = new PriorityQueue<>(num.size(),
                    Collections.reverseOrder());
            for(int i = 0; i <num.size(); i++){
                maxheap.add(num.get(i));
            }
            while(k > 0){
                Integer max = operation(maxheap.poll());
                k--;
                maxheap.add(max);
            }
            return maxheap.stream().mapToInt(Integer::intValue).sum();
        }

        public static int operation(int a){
            double d = (double) a;
            return (int) Math.ceil(d/2);
        }



        /*
         * Complete the 'findSmallestDivisor' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. STRING s
         *  2. STRING t
         */

        public static int findSmallestDivisor(String s, String t) {
            int s_l = s.length();
            int t_l = t.length();
            if(s_l % t_l != 0) return -1;
            int n = s_l / t_l;
            for(int i = 0 ; i < n; i++ ){
                int startIndex = i * t_l;
                int stopindex = (i+1) * t_l;
                String s_sub = s.substring(startIndex, stopindex);
                if(!s_sub.equals(t)) return -1;
            }
            for(int i = t_l; i>=1 ; i--) {
                if(t_l % i == 0 && isdivisible(i, t, t_l)) {//find smallest divisor
                    return t_l/i;
                }
            }
            return t_l;//smallest divisor
        }

        static boolean isdivisible(int divisor, String t, int t_l){
            int chunkSize = t_l / divisor;
            String chunk = t.substring(0,chunkSize);
            boolean result = true;
            for(int i = 1; i < divisor; i++){
                int startIndex = i * chunkSize;
                int stopindex = (i+1) * chunkSize;
                String t_sub = t.substring(startIndex, stopindex);
                if(!t_sub.equals(chunk)) {
                    result = false;
                }

            }
            return result;
        }


    public static void main(String[] args) throws IOException {
        List<Integer> questonOne = Arrays.asList(10, 20, 7);//EXPECTED 14
        System.out.println(questonOne + " : minSum(questonOne, 4) : " +   minSum(questonOne, 4));

        System.out.println("\nquestionTwo");
        System.out.println("findSmallestDivisor(\"bcdbcdbcdbcd\", \"bcdbcd\") : " + findSmallestDivisor("bcdbcdbcdbcd", "bcdbcd"));
    }
}
