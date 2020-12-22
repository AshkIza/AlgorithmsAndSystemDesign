package Tries;

import java.util.Arrays;
import java.util.List;

import Tries.TrieDataStructure.Trie;
import Tries.TrieDataStructure.TrieNode;

/*  Find Longest Common Prefix (LCP) in given set of strings.
 * 
 *    keys = ["codable”, "code”, "coder”, "coding”]
 *    Longest Common Prefix is “cod”
 *    
 *    https://www.techiedelight.com/longest-common-prefix-given-set-strings-using-trie/
 * 
 * */
public class LongestCommonPrefixGivenStringSet {

	public static String LCP(List<String> dictionary){
		Trie trie = new Trie();
		for(String word : dictionary){
			trie.insert(word);
		}
		return LCP(trie);
	}
	
	public static String LCP(Trie trie){
		TrieNode node = trie.root;
		String lcp = "";
		while (node != null && node.getAllChildren() != null &
				node.getAllChildren().size() == 1){
			node = node.getAllChildren().get(0);
			lcp += String.valueOf(node.data);
		}
		return lcp;
	}
	
	public static void main(String[] args) {
		String[] dictionary = new String[]{"code", "coder", "codeable"};
        System.out.println("LCP ('code', 'coder', 'codeable') : "+ LCP(Arrays.asList(dictionary)));
        
        
     	List<String> dict = Arrays.asList(
     				"code", "coder", "coding", "codable", "codec", "codecs",
     				"coded", "codeless", "codependence", "codependency", 
     				"codependent", "codependents", "codes", "codesign",
     				"codesigned", "codeveloped", "codeveloper", "codex",
     				"codify", "codiscovered", "codrive"
     			);
     	System.out.println("Longest Common Prefix is " + LCP(dict));

	}

}
