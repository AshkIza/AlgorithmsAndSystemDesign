package Codility;

import java.util.HashMap;
import java.util.Map;

public class CodilityOktaInterview {
	static Map<String, String> dic = new HashMap<>();
	
	static void print(String s1, String s2) {
		if(s2.length()<=2 && !s2.isEmpty()) {
			System.out.println(s1 + dic.get(s2));
		}
		//case1
		if(s2.length() >= 1) {
			print(s1 + dic.get(s2.substring(0,1)), s2.substring(1, s2.length()));
		}
		//case2
		if(s2.length() >= 2) {
			print(s1 + dic.get(s2.substring(0,2)), s2.substring(2, s2.length()));
		}
	}
	

	public static void main(String[] args) {
		char ch = 'a';
		for(int i= 1; i <=26; i++) {
			dic.put(String.valueOf(i), String.valueOf(ch));
			ch++;
		}

		String input = "123";
		System.out.println("\n " + input + " : ");
		print("" ,input);
		
		input = "16349393";
		System.out.println("\n " + input + " : ");
		print("" ,input);
		
		
		
	}
	
	

}
