package Tries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*  https://java2blog.com/trie-data-structure-in-java/
	 *  insert(String word)
	 *  search(String word)
	 *  remove(String word)
 * 
 * */
public class TrieDataStructure {
	
	public static class TrieNode{
		char data;
		LinkedList<TrieNode> children;
		boolean isComplete;//shows end of word
		
		TrieNode(char val){
			data = val;
		} 
		
		LinkedList<TrieNode> getAllChildren(){
			return children;
		}
		
	    void setComplete(boolean comp){
			isComplete = comp;
		}
		
		boolean isComplete(){
			return isComplete;
		}
		
		void addChild(TrieNode child){
			if(children == null){
				children = new LinkedList<>();
			}
			children.add(child);
		}
		
		TrieNode addChild(char val){
			TrieNode child = getChild(val);
			if(child == null){
				child = new TrieNode(val);
				addChild(child);
			}
			return child;
		}
		
		TrieNode getChild(char val){
			if(children != null){
				for(TrieNode node : children){
					if(node.data == val){
						return node;
					}
				}
			}
			return null;
		}
	}
	
	public static class Trie {
		TrieNode root;
		
		Trie(){
			root = new TrieNode(' ');//char does NOT have null value
		}
		
		void insert(String word){
			if(search(word)){
				return;//avoid duplicate insertion
			}
			TrieNode node = root;
			for(char ch : word.toCharArray()){
				node = node.addChild(ch);
			}
			node.setComplete(true);
		}
		
		boolean search(String word){
			if(word.length() == 0){
				return false;
			}
			return searchRec(word, root);
		}
		
		boolean searchRec(String word, TrieNode node){
			TrieNode child = node.getChild(word.charAt(0));
			if(child == null){
				return false;
			}
			if(word.length() == 1){
				return child.isComplete();
			}
			return searchRec(word.substring(1), child);
		}
	    
	    //in-order  traversal
	    public void printAllWordsInTrie() {
            System.out.println("\nAll words in the Trie (in-order  traversal)");
	    	print(root, "");
	    }
	    
	    public void print(TrieNode node, String prefix) {
	    	prefix = (prefix == " " || prefix =="") ?  String.valueOf(node.data) : prefix + String.valueOf(node.data);
	    	if(node.isComplete){
	            System.out.print(prefix + " ");
	    	}
	    	List<TrieNode> children = node.getAllChildren();
	    	if(children != null){
	    		for(TrieNode child : children){
		    		print(child, prefix);
	    		}
	    	}
	    }
	    
	    public void remove(String word){
	    	if(word.length() == 0 || root == null){
	    		return;
	    	}
	    	TrieNode node = root;
	    	LinkedList<TrieNode> nodes = new LinkedList<>();
	    	while(word.length() != 0 && node.getAllChildren() != null){
	    		TrieNode child = node.getChild(word.charAt(0));
	    		if(child == null){//not found the word character, return
	    			return;
	    		}
	    		nodes.add(child);
	    		
	    		if(word.length() == 1){//last character of the word
	    			if(!child.isComplete){
	    				return;// not found return
	    			}else {//found the word, now remove it
	    				child.setComplete(false);
	    				if(child.getAllChildren() != null && child.getAllChildren().isEmpty()){
	    					removeNode(nodes);// remove only if it is leaf
	    				}
	    			}
	    		}
	    		
	    		word = word.substring(1);
	    		node = child;
	    	}
	    }
	    
	    public void removeNode(LinkedList<TrieNode> nodes){
	    	while(!nodes.isEmpty()){
	    		TrieNode node = nodes.getLast();//stack
	    		if(node.isComplete()){
	    			return;
	    		}
	    		TrieNode parent = nodes.peekLast();
	    		if(parent == null){
	    			parent = root;
	    		}
	    		LinkedList<TrieNode> children = parent.getAllChildren();
	    		children.remove(node);
	    		
	    		if(children.size() != 0 || parent.isComplete){
	    			return;
	    		}
	    		// keep removing node only if node is leaf and not complete
	    	}	
	    	
	    }

	    
	}

	public static void main(String[] args) {
		Trie t = new Trie();       
        t.insert("dear");
        t.insert("deal");
        t.insert("do");
        t.insert("he");
        t.insert("hen");
        t.insert("heat");
        t.insert("dot");
        t.insert("heater");
        t.insert("heaters");
        t.insert("d");

        System.out.println("dork present in trie : "+t.search("dork"));
        System.out.println("hen present in trie : "+t.search("hen"));
        System.out.println("hear present in trie : "+t.search("hear"));
        System.out.println("deal present in trie : "+t.search("deal"));
        System.out.println("he present in trie : "+t.search("he"));

        t.printAllWordsInTrie();

        t.remove("do");
        t.printAllWordsInTrie();

        t.remove("dot");
        t.printAllWordsInTrie();

        t.remove("heatter");
        t.printAllWordsInTrie();
        
        t.remove("heater");
        t.printAllWordsInTrie();
        
        t.remove("dork");
        t.printAllWordsInTrie();
        
        t.remove("d");
        t.printAllWordsInTrie();
        

	}

}
