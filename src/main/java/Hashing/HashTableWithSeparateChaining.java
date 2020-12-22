package Hashing;

import java.util.ArrayList;

/*  https://www.geeksforgeeks.org/implementing-our-own-hash-table-with-separate-chaining-in-java/
 * */
public class HashTableWithSeparateChaining<K,V> {
	
	static  class HashNode<K,V>{
		K key;
		V value;
		HashNode<K, V> next;
		HashNode(K k, V v){
			key = k;
			value = v;
		}
		
		public V getValue(){
			return value;
		}
	}
	
	static  class LinkedHashNode<K,V>{//Linked list suing HashNodes
		HashNode<K,V> head;
		
		LinkedHashNode(){
			head = new HashNode<K, V>(null, null);
		}
		
		void add(K k, V v){
			if(head.key == null && head.value == null){
				head.key = k;
				head.value = v;
				return;
			}
			HashNode<K,V> node = new HashNode<K,V>(k , v);
			node.next = head;
			head = node;
		}
		
		HashNode<K, V> get(K k){
			HashNode<K, V> node = head;
			while (node != null){
				if(node.key == k){
					return node;
				}else{
					node = node.next;
				}
			}
			return null;
		}
		
		HashNode<K, V> remove(K k){
			if(head.next == null){//only value
				if(head.key == k){
					HashNode<K, V> temp = head;
					head.key = null;
					head.value = null;
					return temp;
				}
				return null;
			}
			HashNode<K, V> node = head;
			while (node.next != null){
				if(node.next.key == k){//found the key, need to be remove node.next
					HashNode<K, V> temp = node.next;
					node.next = node.next.next;
					return temp;
				}else{
					node = node.next;
				}
			}
			return null;
		}
		
		ArrayList<K> getKeys(){
			ArrayList<K> keys = new ArrayList<>();
			HashNode<K, V> node = head;
			while (node != null){
				if(node.key != null){
					keys.add(node.key);
				}
				node = node.next;
			}
			return keys;
		}
	}
	
	int size;
	ArrayList<LinkedHashNode<K, V>> hashtable;
	
	HashTableWithSeparateChaining(int N){
		size = N;
		hashtable = new ArrayList<>();
		for(int i = 0; i < N ; i++ ){
			hashtable.add(new LinkedHashNode<K,V>());
		}
	}
	
	 void add(K k, V v){
		int index = Math.abs(k.hashCode()) % size;
		LinkedHashNode<K, V> bucket = hashtable.get(index);
		if (bucket != null){
			bucket.add(k, v);
		}
	}
	
	 V  get(K k){
		int index = Math.abs(k.hashCode()) % size;
		LinkedHashNode<K, V> bucket = hashtable.get(index);
		if(bucket != null){
			HashNode<K, V> hashNode = bucket.get(k);
			if(hashNode != null){
				return  hashNode.getValue();
			}
		}
		return null;
	}
	 
	 V remove(K k){
		 int index = Math.abs(k.hashCode()) % size;
			LinkedHashNode<K, V> bucket = hashtable.get(index);
			if(bucket != null){
				HashNode<K, V> hashNode = bucket.remove(k);
				if(hashNode != null){
					return  hashNode.getValue();
				}			
			}
			return null;
	}
	 
	 ArrayList<K> getKeys(){
		 ArrayList<K> keys = new ArrayList<>();

		for(LinkedHashNode<K, V> bucket : hashtable){
			keys.addAll(bucket.getKeys());
		 }
		return keys;
	}
	 
	int size(){
		return getKeys().size();
	 }
	
	//todo: 
	 // remove
	 // String.hashCode Negative --> Math.abs(String.hashCode)
	 // generic array in java not allower
	 //ArrayList<Object> list = new ArrayList(); //does not neccearily call Object.ctr should explicity caal it
	 // a custom object with custom hashCode
	 // string hash functions & integer hash functions

	public static void main(String[] args) {
		HashTableWithSeparateChaining<String, Integer> hashMap = new HashTableWithSeparateChaining<String, Integer>(20);
		hashMap.add("interlocking", 3200);
		hashMap.add("ashphalt", 1400);
		hashMap.add("backyard", 1000);
		hashMap.add("frontyard", 300);
		hashMap.add("roof", 220);

		System.out.println("hashMap.get(dummy) " + hashMap.get("dummy"));
		System.out.println("hashMap.get(roof) " + hashMap.get("roof"));
		System.out.println("hashMap.get(interlocking) " + hashMap.get("interlocking"));
		System.out.println("hashMap.get(frontyard) " + hashMap.get("frontyard"));
		System.out.println("hashMap.size() " + hashMap.size());
		System.out.println("hashMap.remove(frontyard); " + hashMap.remove("frontyard"));
		System.out.println("hashMap.get(frontyard) " + hashMap.get("frontyard"));
		System.out.println("hashMap.size() " + hashMap.size());


	}

}
