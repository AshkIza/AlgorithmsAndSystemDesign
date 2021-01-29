package leetcode.Strings;

public class ValidPalindrome {
	
	   /*public static boolean isPalindrome(String s) {
	        if(s.length() == 0 || s.length() ==1){
	            return true;
	        }
	        int i = 0;
	        int j = s.length() -1;
	        while(i < j){
	            char a = alphaNum(s.charAt(i));
	            char b = alphaNum(s.charAt(j));
	            if(a != ' ' && b != ' '){
	                if(a != b){
	                    return false;
	                }else{
	                    i++;
	                    j--;
	                }
	            }else{
	            	i = (a == ' ') ? i+1 : i;
	            	j = (b == ' ') ? j-1 : j;
	            }
	        }
	        return true;
	    }
	    
	        private static char alphaNum(char input){
	        if(input >= '0' && input <= '9'){
	            return input;
	        }else if( (input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z')){
	        	return convertCapitalToSmall(input);
	        }else{
	            return ' ';
	        }
	    }
	    */
	
	
	   public static boolean isPalindrome(String s) {
	        if(s.length()==0) return true;
	        int ia = 0;
	        int ib = s.length()-1;
	        s = s.toLowerCase();
	        while(ia < ib){
	            while(ia < s.length() && !alphanumeric(s.charAt(ia))){
	                ia++;
	            }
	            while(ib >= 0 && !alphanumeric(s.charAt(ib))){
	                ib--;
	            }
	            if(ia >= ib) return true;
	            if(s.charAt(ia) != s.charAt(ib)) {
	                 return false;
	            }
	            ia++;
	            ib--;
	        }
	        return true;
	    }
	    
	    private static boolean alphanumeric(char ch){
	        if((ch >= 'a' && ch<='z') || (ch>='0' && ch<='9')) {
	            return true;
	        }
	        return false;
	    }
	    
	    
	    
	    private static char convertCapitalToSmall(char input){
	    	if(input >= 'A' && input <= 'Z'){
	    		int diff =  'A'-'a';
	    		return (char) (input - diff);//cast it back to char otherwise will stay as ascii value
	    	}else if(input >= 'a' && input <= 'z'){
	    		return input;
	    	}else{
	    		return ' ';
	    	}
	    }
	
	  
	    

	public static void main(String[] args) {
		//System.out.println(" isPalindrome('A man, a plan, a canal: Panama') : " + isPalindrome("A man, a plan, a canal: Panama"));
		System.out.println(" isPalindrome('0P') : " + isPalindrome("0P"));
		System.out.println(" isPalindrome('race a car') : " + isPalindrome("race a car"));
		
		// capital to small letter char, ascii int versus char
		System.out.println("\n\nConvert Capital letters to small letters ( convert to ascii int VS back to char)");
		System.out.println("  convertCapitalToSmall('0') : " + convertCapitalToSmall('0'));
		System.out.println("  convertCapitalToSmall('F') : " + convertCapitalToSmall('F'));
		System.out.println("  convertCapitalToSmall('f') : " + convertCapitalToSmall('f'));
		
		for(char ch = 'A'; ch <='Z'; ch++){
			System.out.println(ch + " (capital letter)  + 32 (cast it back to char) --> " + (char) (ch + 32) + " (small letter)");

		}
		
		System.out.println(" \n('A' + 32) : " + ('A' + 32) + "    Vs   (char) ('A' + 32) : " + ((char) ('A' + 32)));
		
		// null and '' are invalid char constants
		System.out.println("  \nnull and '' are invalid character constants " );
		System.out.println("      for null, cast char to Character object" );
		System.out.println("      for '', use ' ' instead" );

		// Increment (++) and decrement (--) operators not very safe
		System.out.println("  \n Increment (++) and decrement (--) operators should not be used in a method call or mixed with other operators in an expression " );
		System.out.println("    It is safer to use these operators in isolation from any other arithmetic operators.  " );
		System.out.println("    https://rules.sonarsource.com/java/RSPEC-881  " );

		System.out.println("  \nint i = 0  " );
		int i = 0;
		i = (i == 0) ? i++ : i;
		System.out.println("  i = (i == 0) ? i++ --> i : " + i + " NEVER USE a++ operator in mixed operations, use it separatly or use ++a");
		
		i = 0;
		i = (i == 0) ? i+1 : i;
		System.out.println("  i = (i == 0) ? i+1 --> i : " + i);
		
		i = 0;
		i = (i == 0) ? ++i : i;
		System.out.println("  i = (i == 0) ? ++i --> i : " + i);
		
		
		System.out.println("  \nint a = 10 , int b = 20 " );
		int a = 10; 
		int b = 20;
		System.out.println("  a++ + b++ " +  (a++ + b++));
		System.out.println("  a , b  " +  (a) + ", " + (b) + " a and b got incremented after + operation ");

		System.out.println("    \nuse them in isolation from any other arithmetic operators.  " );
	    a = 10; 
		b = 20;
		System.out.println("  first a++  " +  (a++));
		System.out.println("  then b++  " +  (b++));
		System.out.println("  then a+b  " +  (a+b));
		
		System.out.println("    \nOR, use ++a operator  " );
		 a = 10; 
		b = 20;
		System.out.println("  ++a + ++b " +  (++a + ++b));

	}

}
