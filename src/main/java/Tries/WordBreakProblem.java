package Tries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Tries.TrieDataStructure.Trie;
import Tries.TrieDataStructure.TrieNode;

/*  https://www.techiedelight.com/word-break-problem-using-trie/
 * 
 * */
public class WordBreakProblem {
	
	public static void wordBreak(List<String> dictionary, String word){
		Trie trie = new Trie();
		for(String dicWord : dictionary){
			trie.insert(dicWord);
		}
		List<String> matchedPref = new ArrayList<>();
	    BreakRec(trie, trie.root, word, 0, matchedPref, "" );
	}
	
	public static void BreakRec(Trie trie, TrieNode node, String word,
			int remainingIndex, List<String> matchedPref, String foundpref){
		int index = remainingIndex;
		while(node!= null & index < word.length()){
			TrieNode child = node.getChild(word.charAt(index));
			if(child == null){
				return;
			}
			if(child.isComplete()){//bounding function
				// 2 options, ignore it, keep it (dfs)
				// a- ignore the found prefix and keep going 
				
				//BreakRec(trie, child, word, index + 1, matchedPref, foundpref);
				// b - keep found prefix, and start again from root for the remaining
				matchedPref.add(foundpref);
				BreakRec(trie, trie.root, word, index + 1, matchedPref, "");
			}
			foundpref += String.valueOf(child.data);
			index++;
			node = child;
			
		}
		
		if(index >= word.length()){//bounding function
			print(matchedPref);
		}
	}
	
	public static void print(List<String> matchedPref){
		String st = " one possible way to break the word is :";
		for(String dicWord : matchedPref){
			st += dicWord + ", ";
		}
     	System.out.println(st);
	}

	public static void main(String[] args) {
		List<String> dict = Arrays.asList("this", "th", "is", "famous",
				"word", "break", "b", "r", "e", "a", "k",
				"br", "bre", "brea", "ak", "prob", "lem");
		wordBreak(dict, "wordbreakproblem");
		
	}

}
