package leetcode.Strings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordSearchDictionary {
	static Map<Character, Integer> masterFingerPrint = new HashMap<Character, Integer>();
	static int masterSize;
	static int numberOfDeepCopies;
	static int numberOfPermutations;
	
	private static void generateFingerPrint(String s) {
		masterFingerPrint.clear();
		masterSize = s.length();
		for(char c : s.toCharArray()) {
			int charCout = 1;
			if(masterFingerPrint.containsKey(c)) {
				charCout = masterFingerPrint.get(c) + 1;
			}
			masterFingerPrint.put(c, charCout);
		}
	}
	
	static Map<Character, Integer> deepCopy(){
		Map<Character, Integer> copy = new HashMap();
		masterFingerPrint.entrySet().stream().forEach(e -> copy.put(e.getKey(), e.getValue()));
		return copy;
	}
		            
	
	private static void wordSearch(Map<Character, Integer> masterFingerPrint, String word) {
		if(word.length() > masterSize) return;
		Map<Character, Integer> copy = deepCopy();
		numberOfDeepCopies++;
		for(char c : word.toCharArray()) {
			if(copy.containsKey(c)) {
				int charCount = copy.get(c) - 1;
				if(charCount < 1) {
					copy.remove(c);
				}
			}else {
				return;
			}
		}
		System.out.println(word);
		numberOfPermutations++;
	}

	public static void main(String[] args) {
		// default settings if no args
		String master = "bull";
		String currentDirectory = System.getProperty("user.dir");
		String filePath = currentDirectory + "/src/leetcode/Strings/dictionary.txt";//"/usr/share/dict/words";
		if(args.length > 0) {
			master = args[0];
		}
		if(args.length > 1) {
			filePath = args[1];
		}

		generateFingerPrint(master);
		try {
			int dictionarySize = 0;
	        Scanner dictionary = new Scanner(new File(filePath));
	        while(dictionary.hasNext()){
	        	dictionarySize++;
	            wordSearch(masterFingerPrint, dictionary.next().trim());	            
	        }
	        System.out.println("----- results -----\n" + "master word : " + master + 
	        		"\ndictionaryPath : " + filePath +
	        		"\ndictionarySize : " + dictionarySize +
	        		"\nnumberOfPermutations :" + numberOfPermutations +
	        		"\nnumberOfDeepCopies : " + numberOfDeepCopies + "\n"
	        				+ "currentDirectory" + currentDirectory);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
