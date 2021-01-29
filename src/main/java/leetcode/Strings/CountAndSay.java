package leetcode.Strings;

/* https://leetcode.com/explore/interview/card/top-interview-questions-easy/127/strings/886/
 * 
 * 
 * */
public class CountAndSay {
	
	  public static String countAndSayRecursive(int n) {
	        if(n==1) return "1";
	        String st =  countAndSayRecursive(n-1);
	        char digit = st.charAt(0);
	        int count = 1; 
	        String result = "";
	        for(int i = 1; i < st.length(); i++){
	            if(st.charAt(i) == digit){
	                count++;
	            }else{
	                result += count + "" + digit;
	                digit = st.charAt(i);
	                count = 1;
	            }
	        }
	        result += count + "" + digit;
	        return result;
	    }
	  
	   public static String countAndSay(int n) {
	        if(n==1) return "1";
	        String sayString = "1";
	        for(int i = 2 ; i<=n; i++){
	            sayString = say(sayString);
	        }
	        return sayString;
	    }
	    
	    private static String say(String st){
	        char digit = st.charAt(0);
	        int count = 1; 
	        String result = "";
	        for(int i = 1; i < st.length(); i++){
	            if(st.charAt(i) == digit){
	                count++;
	            }else{
	                result += count + "" + digit;
	                digit = st.charAt(i);
	                count = 1;
	            }
	        }
	        result += count + "" + digit;
	        return result;
	    }

	public static void main(String[] args) {
		System.out.println("countAndSayRecursive(4) : " + countAndSayRecursive(4));
		System.out.println("countAndSay(4) : " + countAndSay(4));
		System.out.println(" Recusrsive solution is more intuitive but more memory-intensive (stack memory)");


	}

}
