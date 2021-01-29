package leetcode.Strings;

public class myAtoi {

	
	  public static int myAtoi(String s) {
	        if(s.length() == 0) return 0;
	        long result = 0;
	        int index = 0;
	        while(index < s.length() && s.charAt(index) == ' '){//ignore whitespaces
	            index++;
	        }
	        if(index > s.length()-1) return 0;//reached end of string
	        char ch = s.charAt(index);
	        if(ch=='+' || ch=='-' || isAnumber(ch)){
	            int sign = ch=='-' ?  -1 : 1 ;
	            if(ch=='+' || ch=='-'){
	                index++;
	            }
	            //long overflow is possible too, so return imediatly when int overflow
	            while(index < s.length() && isAnumber(s.charAt(index))){
	                long i = (long) s.charAt(index) - '0';
	                result = result * 10 + i;
	                if(result > Integer.MAX_VALUE){//if int overflow return imediatly
	                    result *= sign;
	                    result = (result >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
	                    return (int) result;
	                }
	                index++;
	            }
	            result *= sign;
	            return (int) result;
	        }
	        return 0;
	    }
	    
	    private static boolean isAnumber(char ch){
	        return ch>='0' && ch<='9';
	    }
	public static void main(String[] args) {
		System.out.println(myAtoi("9223372036854775808"));//Long overflow
		
		System.out.println("Long overflow -> Long.MAX_VALUE =" + Long.MAX_VALUE);
		System.out.print("Long.MAX_VALUE + 1 --overflow--> " + (Long.MAX_VALUE + 1));
			
	}

}
