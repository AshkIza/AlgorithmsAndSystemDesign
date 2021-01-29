package leetcode.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
public class LFUCache {

	int capacity;
    int size;
    int leastFreq;
    
    class Node {
        int key;
        int value;
        int freq;
        Node pre;
        Node next;
        Node(int key, int value){
            this.key=key;
            this.value=value;
        }
    }
    
    class LRUCache {
        Node head;
        Node tail;
        int size;
        void add(Node n){
           if(head == null){
               head = n;
               tail = n;
           }else{
               head.pre=n;
               n.next=head;
               head=n;
           }
            size++;
        }
        void remove(Node node){
            if(size == 0) return;
            if(size == 1){
                head = null;
                tail = null;
                size--;
                return;
            }
            Node p = node.pre;
            Node n = node.next;
            if(p==null){
                n.pre=null;
                head = n;
                size--;
                return;
            }
            if(n==null){
                p.next=null;
                tail = p;
                size--;
                return;
            }
            p.next = n;
            n.pre = p;
            size--;
            return;
        }
        Node get(){
            if(size == 0) return null;
            Node n = null;
            if(size == 1) {
                n = head;
                head = null;
                tail = null;
            }else{
                n = tail;
                Node n2 = tail.pre;//new tail
                n2.next = null;
                tail = n2;
            }
            size--;
            return n;
        }
    }
    
    Map<Integer, LRUCache> freqMap = new HashMap<>();
    Map<Integer, Node> cache = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity=capacity;
        this.size=0;
        this.leastFreq=0;
    }
    
    public int get(int key) {
    	if(key == 12 || key == 4) {
    		key =  key +1-1;
    	}
        if(!cache.containsKey(key)){
            return -1;
        }
        Node n = cache.get(key);
        updatefreqMap(n.freq, n);
        return n.value;
    }
    
    public void put(int key, int value) {
    	if(key == 12 || key == 4) {
    		key =  key +1-1;
    	}
        Node n = null;
         if(!cache.containsKey(key)){//value not exists
             if(size >= capacity){//evict first - LFU
                 if(!invalidate()){//remove lfu from both maps
                     return;
                 };
             }
             n = new Node(key, value);
             cache.put(key, n);
             size++;
         }else{//key already exists, update the cache
             n = cache.get(key);
             n.value = value;//update node
         }
         updatefreqMap(n.freq, n);
    }
    
    private boolean invalidate(){
        if(freqMap.containsKey(leastFreq)){
            LRUCache lru = freqMap.get(leastFreq);
            Node n = lru.get();//get LRU and remove it from freqMap
            if(lru.size == 0) {
                freqMap.remove(leastFreq);
                leastFreq = leastFrequency();
             }
            cache.remove(n.key);
            size--;
            return true;
        }else{
            return false;
        }
    }
    
    private  int leastFrequency() {
    	return freqMap.keySet().stream().min(Integer::compare).get();
    }
    
    private void updatefreqMap(int freq, Node n){
        if(freq == 0){//insert
            if(!freqMap.containsKey(1)){
                freqMap.put(1, new LRUCache());
                leastFreq = 1;
            }
            LRUCache lru = freqMap.get(1);
            n.freq++;
            lru.add(n);
        }else{//update
            if(!freqMap.containsKey(freq)){
                freqMap.put(freq, new LRUCache());
            }
            LRUCache lru = freqMap.get(freq);
            lru.remove(n);
            if(lru.size == 0) {
               freqMap.remove(freq);
               if(freq == leastFreq){
                    leastFreq++;
               }
            }
            freq++;
            n.freq ++;
            if(!freqMap.containsKey(freq)){
                freqMap.put(freq, new LRUCache());
            }
            lru = freqMap.get(freq);
            lru.add(n);
        }
    }
    
    private static void runner(String[] methods, Integer[][] values, LFUCache lruCache, String expected) {
    	
    	List<Integer>  output = new ArrayList<>();
    	List<String> valuesList = new ArrayList<>();
    	List<String> results = Arrays.stream(expected.split(",")).collect(Collectors.toList());
    	for(int i = 0; i < methods.length; i++) {
    		boolean isPut = "put".equals(methods[i]);
    		if(isPut) {
    			lruCache.put(values[i][0],values[i][1]);
    			output.add(null);
    			valuesList.add(Arrays.asList(values[i][0],values[i][1]).toString());
    		}else {
    			output.add(lruCache.get(values[i][0]));
    			valuesList.add(String.valueOf(values[i][0]));
    		}
    		
    		if(!results.get(i).equals("null") && !Integer.valueOf(results.get(i)).equals(output.get(i))) {
    			String error = "error at index[" + i + "]" + " : ";
    			if(isPut) {
    				error+="put(" + "" + values[i][0]+ ", " + values[i][1]+ ")";
    			}else {
    				error+="get(" + "" + values[i][0]  + ") = " + output.get(i) + " (should be : " + results.get(i) +")";
    			}
    			
    			System.out.println(error );
    		}
    	}
    	
    	System.out.println("methods : " + Arrays.stream(methods).collect(Collectors.toList()));
    	System.out.println("values  : " + valuesList);
    	System.out.println("results   " + output);
    	System.out.println("expected :" + results);
    	
    }
    
    private static Integer[][] values(String s){
    	String[] v = s.split(",");
    	List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
    	int i = 0;
    	while(i < v.length) {
    		String ch = v[i];
			ArrayList<Integer> arr = new ArrayList<>();
    		if(ch.startsWith("[") && ch.endsWith("]")) {
    			arr.add(Integer.valueOf(ch.replace("[","").replace("]","")));
    			list.add(arr);
    			i++;
    		}else {
    			arr.add(Integer.valueOf(ch.replace("[","")));
    			arr.add(Integer.valueOf(v[i+1].replace("]","")));
    			i+=2;
    			list.add(arr);
    		}
    	}
    	
    	return list.stream().map(u -> u.toArray(Integer[]::new)).toArray(Integer[][]::new);
    }
    
    public static void main(String[] args) {
    	/*System.out.println("case 01");
    	String[] methods01 = new String[] {"put","put","get","put","get","get","put","get","get","get"};
    	Integer[][] values01 = new Integer[][] {
    		{1,1},
    		{2,2},
    		{1},
    		{3,3},
    		{2},
    		{3},
    		{4,4},
    		{1},{3},{4}
    	};
    	
    	runner(methods01, values01, new LFUCache(2));
    	
    	System.out.println("case 02");
    	String[] methods02 = new String[] {"put","put","put","put","get","get"};
    	Integer[][] values02 = new Integer[][] {
    		{2,1},
    		{1,1},
    		{2,3},
    		{4,1},
    		{1},{2}
    	};
    	runner(methods02, values02, new LFUCache(2));*/
    	
    	
    	
      	System.out.println("case 03");
    	String[] methods03 = new String[] {"put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"};
    	String svalues03 = "[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]";
    	String expected03 = "null,null,null,null,null,-1,null,19,17,null,-1,null,null,null,-1,null,-1,5,-1,12,null,null,3,5,5,null,null,1,null,-1,null,30,5,30,null"
    			+ ",null,null,-1,null,-1,24,null,null,18,null,null,null,null,14,null,null,18,null,null,11,null,null,null,null,null,18,null,null,-1,null,4,29,30,null,12,11,null,null,null,null,29,null,null,null,null,17,-1,18,null,null,null,-1,null,null,null,20,null,null,null,29,18,18,null,null,null,null,20,null,null,null,null,null,null,null";
    	runner(methods03, values(svalues03), new LFUCache(10), expected03);
    	
    	
    	
    	
    	

	}
}