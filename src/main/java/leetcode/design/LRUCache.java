package leetcode.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://leetcode.com/problems/lru-cache/
 * 
 * Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. 
	Otherwise, add the key-value pair to the cache.
	 If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * 
 * 
 * 
 * 
 * 
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class LRUCache {
    public static class CacheNode{
        int key;
        int value;
        CacheNode pre;
        CacheNode next;
        CacheNode(int k, int v){
            this.key = k;
            this.value = v;
        }
    }
    
    Map<Integer, CacheNode> cache = new HashMap<>();
    CacheNode head;
    CacheNode tail;
    int capacity;
    int cacheSize;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!cache.containsKey(key)){
            return -1;
        }
        // cache entry exist, update the LRU and return entry value
        CacheNode node = cache.get(key); 
        if(cacheSize > 1){
            updateLRU(node);//re-arrange node as the head
        }
        return node.value;
    }
    
    public void put(int key, int value) {
        //existing entry in the cache
        if(cache.containsKey(key)){
            CacheNode node = cache.get(key);
            node.value = value;
            cache.put(key, node);
            if(cacheSize > 1){
                 updateLRU(node);//re-arrange node as the head
            }
            return;
        }
        // new entry to the cache
        CacheNode node = new CacheNode(key, value);
        if(cacheSize >= capacity){//evict first
             evictLRU();
        }
        if(head == null){//empty cache
            head = node;
            tail  = head;
            cacheSize = 1;
        }else{
            //now we are sure, we have space for new key/value
            node.next = head;
            head.pre = node;
            head = node;
            cacheSize++;
        }
        cache.put(node.key, node);
    }
    void evictLRU(){
        if(cacheSize == 0){//empty cache
            return;
        }
        cache.remove(tail.key);
        if(capacity == 1){
            head = null;
            tail = null;
            cacheSize = 0;
        }else{
           CacheNode n = tail.pre;
           tail = n;
           cacheSize--; 
        }
    }
    private void updateLRU(CacheNode node){
        // handle two edge-cases first
        if(node == head) return;
        if(node == tail){
            CacheNode newTail = tail.pre;
            head.pre = node;
            node.next = head;
            head = node;
            newTail.next = null;
            tail = newTail;
            return;
        }
        // node in the middle
        // wire prev and next nodes (skip node)
        CacheNode prevNode = node.pre;
        CacheNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.pre = prevNode;
        // rearrange node as head
        head.pre = node;
        node.next = head;
        head = node;
    }
    
    private static void runner(String[] methods, Integer[][] values, LRUCache lruCache) {
    	
    	List<Integer>  output = new ArrayList<>();
    	List<String> valuesList = new ArrayList<>();
    	for(int i = 0; i < methods.length; i++) {
    		if("put".equals(methods[i])) {
    			lruCache.put(values[i][0],values[i][1]);
    			output.add(null);
    			valuesList.add(Arrays.asList(values[i][0],values[i][1]).toString());
    		}else {
    			output.add(lruCache.get(values[i][0]));
    			valuesList.add(String.valueOf(values[i][0]));
    		}
    	}
    	
    	System.out.println("methods : " + Arrays.stream(methods).collect(Collectors.toList()));
    	System.out.println("values  : " + valuesList);
    	System.out.println("          " + output);
    }
    
    public static void main(String[] args) {
    	System.out.println("case 01");
    	String[] methods01 = new String[] {"put","put","get","put","get","put","get","get","get"};
    	Integer[][] values01 = new Integer[][] {
    		{1,1},
    		{2,2},
    		{1},
    		{3,3},
    		{2},
    		{4,4},
    		{1},{3},{4}
    	};
    	runner(methods01, values01, new LRUCache(2));
    	
    	System.out.println("case 02");
    	String[] methods02 = new String[] {"put","put","put","put","get","get"};
    	Integer[][] values02 = new Integer[][] {
    		{2,1},
    		{1,1},
    		{2,3},
    		{4,1},
    		{1},{2}
    	};
    	runner(methods02, values02, new LRUCache(2));

	}
}

