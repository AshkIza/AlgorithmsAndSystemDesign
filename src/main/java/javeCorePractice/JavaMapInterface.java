package javeCorePractice;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Collections;


/*  HashMap
 *  TreeMap
 *  LinkedHashMap
 *  HashTable
 *  same as HashSet   LinkedHashSet TreeSet
 *  https://www.geeksforgeeks.org/differences-treemap-hashmap-linkedhashmap-java/
 *  
 *  iterate over the map
 *  https://www.geeksforgeeks.org/iterate-map-java/
 * */
public class JavaMapInterface {

	public static void main(String[] args) {
		System.out.println("hashMap does NOT maintain insertion order for the keys");
		Map<String, Integer> hashMap = new HashMap<>();
		hashMap.put("Interlocking", 3240);
		hashMap.put("Driveway", 1750);
		hashMap.put("FrontEdge", 160);
		hashMap.put("Roof", 220);
		hashMap.put("BackYard", 500);
		hashMap.put("Deck", 150);
		for(String key :  hashMap.keySet()){
			System.out.println("key : " + key);
		}
		System.out.println();

		System.out.println("linkedHashMap  maintains insertion order for the keys");
		Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.put("Interlocking", 3240);
		linkedHashMap.put("Driveway", 1750);
		linkedHashMap.put("FrontEdge", 160);
		linkedHashMap.put("Roof", 220);
		linkedHashMap.put("BackYard", 500);
		linkedHashMap.put("Deck", 150);
		for(String key :  linkedHashMap.keySet()){
			System.out.println("key : " + key);
		}
		System.out.println();
		
		
		System.out.println("TreeMap  does NOT maintain insertion order, BUT it keeps the natural ordering");
		Map<String, Integer> treeMap = new TreeMap<>();
		treeMap.put("Interlocking", 3240);
		treeMap.put("Driveway", 1750);
		treeMap.put("FrontEdge", 160);
		treeMap.put("Roof", 220);
		treeMap.put("BackYard", 500);
		treeMap.put("Deck", 150);
		for(String key :  treeMap.keySet()){
			System.out.println("key : " + key);
		}
		
		System.out.println("hashTable (like hashMap)  does NOT maintain insertion order");
		Map<String, Integer> hashTable = new Hashtable<>();
		hashTable.put("Interlocking", 3240);
		hashTable.put("Driveway", 1750);
		hashTable.put("FrontEdge", 160);
		hashTable.put("Roof", 220);
		hashTable.put("BackYard", 500);
		hashTable.put("Deck", 150);
		for(String key :  hashTable.keySet()){
			System.out.println("key : " + key);
		}
		
		System.out.println();
		System.out.println(" iterating through a Map ;  keySet / values / entrySet / forEach java8 ");
		System.out.println();

		System.out.println(" for(Map.Entry<K, V> entry : map.entrySet()) {}");
		for(Map.Entry<String, Integer> entry : hashTable.entrySet()){
			System.out.print("hashTable entry.getKey() : " + entry.getKey());
			System.out.println(" | hashTable entry.getValue() : " + entry.getValue());
		}
		
		System.out.println();
		System.out.println(" for(K key : map.keySet()) {}");
		for(String key : treeMap.keySet()){
			System.out.println("treeMap key : " + key);
		}
		
		System.out.println();
		System.out.println(" for(V value : map.values()) {}");
		for(Integer value : linkedHashMap.values()){
			System.out.println("linkedHashMap value : " + value);
		}
		
		
		System.out.println("\nRemove an Entry using key from HashMap while Iterating over it");
		System.out.println("	before java8 - map.keySet().iterator(); it.next() it.remove() ");
		HashMap<Integer, String> map = new HashMap<>(); 
	    map.put(1, "Geeks"); 
	    map.put(2, "ForGeeks"); 
	    map.put(3, "GeeksForGeeks"); 
	    System.out.println("Original HashMap : " + map);
	    Iterator<Integer> keySetIt = map.keySet().iterator();
	    while(keySetIt.hasNext()) {//fail-fast concurrentModificationeXCEPTION
	    	Integer k = keySetIt.next();
	    	if(k.equals(2)) {
	    		keySetIt.remove();//iterator.remove()
	    	}
	    }
	    System.out.println("Modified HashMap : " + map);

		System.out.println("	java8 - map.keySet().removeIf(Predicate)");
	    map.put(2, "ForGeeks"); 
	    System.out.println("Original HashMap : " + map);
	    map.keySet().removeIf( key -> key.equals(2));
	    System.out.println("Modified HashMap : " + map);
	    
	    map.put(2, "ForGeeks"); 
		System.out.println("\nRemove an Entry using value and key from HashMap while Iterating over it");
		System.out.println("	before java8 - map.entrySet().iterator(); it.next() it.remove() ");
	    System.out.println("Original HashMap : " + map);
		Iterator<Map.Entry<Integer, String>> entryIt = map.entrySet().iterator();
		while(entryIt.hasNext()){
			Map.Entry<Integer, String> mapEntry = entryIt.next();
			if(mapEntry.getValue().equals("GeeksForGeeks")) {
				entryIt.remove();//iterator.remove()
			}
		}
	    System.out.println("Modified HashMap : " + map);
	   
	    System.out.println("	java8 - map.entrySet().removeIf(Predicate)");
	    map.put(3, "GeeksForGeeks"); 
	    System.out.println("Original HashMap : " + map);
	    map.entrySet().removeIf(mapEntry -> mapEntry.getValue().equals("GeeksForGeeks"));
	    System.out.println("Modified HashMap : " + map);

		System.out.println("\nHashMap 'Value remove(Object key)' Method");
		String valueRemoved = map.remove(2);
	    System.out.println("Modified HashMap : " + map + " valueRemoved : " + valueRemoved);
	    
		System.out.println("\nInitialize a HashMap ");
	    Map<Integer, Double> emptyMap = Collections.emptyMap();
	    Map<Integer, Double> singletonMap = Collections.singletonMap(12, 13.56);//Returns an immutable map
		Map<Integer, Double> doubleBracesMap = new HashMap<Integer, Double>() {{
			put(12, 13.90);
			put(-98, 6778.78);
		}};// not recommended!
		Map<Integer, Double> streamOfCollectorsMap = Stream.of(new Object[][] {
			{987, 163.78},
			{7, 7.1},
			{9, -0.01},
		}).collect(Collectors.toMap(entry -> (Integer)entry[0], entry -> (Double)entry[1]));
		System.out.println("emptyMap : " + emptyMap);
		System.out.println("singletonMap : " + singletonMap);
		System.out.println("streamOfCollectorsMap : " + streamOfCollectorsMap);


		System.out.println("\nprocess maps with streams, entrySet(), keySet(), values() ");
		Map<String, String> books = new HashMap<>();
		books.put("978-0201633610", "Design patterns : elements of reusable object-oriented software");
		books.put("978-1617291999", "Java 8 in Action: Lambdas, Streams, and functional-style programming");
		books.put("978-0134685991", "Effective Java");
		books.put("978-0321356680", "Effective Java: Second Edition");
		
		List<String> multipleMatchResults = books.entrySet().stream()
			.filter(entry -> entry.getValue().startsWith("Effective Java"))
			.map(Map.Entry::getKey)
			.collect(Collectors.toList()); // we collect the results – instead of picking the first 
		Optional<String> oneMatch = books.entrySet().stream()
				.filter(entry -> entry.getValue().startsWith("Effective Java"))
				.map(Map.Entry::getKey)
				.findFirst();
		System.out.println("multipleMatchResults (.collect(Collectors.toList())) : " + multipleMatchResults);
		System.out.println("oneMatch (.findFirst()) : " + oneMatch.get());

		
		System.out.println("\nMap.getOrDefault() Map.putIfAbsent()  Map.remove(K, V)");
		System.out.println("Map.replace(K,V)    Map.replace(K,V,V)");
		
		Map statesAndCapitals = new HashMap<>();
	    statesAndCapitals.put("Alaska", "Anchorage");
	    statesAndCapitals.put("California", "Sacramento");
	    statesAndCapitals.put("Colorado", "Denver");
	    statesAndCapitals.put("Florida", "Tallahassee");
	    
	    
	    System.out.println("statesAndCapitals.getOrDefault(\"Oregon\", \"Unknown\") : " 
	    		+ statesAndCapitals.getOrDefault("Oregon", "Unknown"));
	    System.out.println("statesAndCapitals.putIfAbsent(\"Oregon\", \"Portland\") : " 
	    		+ statesAndCapitals.putIfAbsent("Oregon", "Portland"));
	    System.out.println("statesAndCapitals.putIfAbsent(\"Oregon\", \"seattle\") : " 
	    		+ statesAndCapitals.putIfAbsent("Oregon", "seattle"));
	    System.out.println("statesAndCapitals : " + statesAndCapitals);
	    System.out.println("statesAndCapitals.remove(\"Oregon\", \"Portland\") : " 
	    		+ statesAndCapitals.remove("Oregon", "Portland"));
	    System.out.println("statesAndCapitals : " + statesAndCapitals);
	    System.out.println("statesAndCapitals.replace(\"Colorado\", \" Boulder\") : " + 
	    		statesAndCapitals.replace("Colorado", " Boulder"));
	    System.out.println("statesAndCapitals.replace(\"California\", \" Sacramento\", \"San Francisco\") : "
	    		+ statesAndCapitals.replace("California", " Sacramento", "San Francisco"));
	    System.out.println("statesAndCapitals : " + statesAndCapitals);
 
	    
	    System.out.println("\n\nConditional Map get/put -> handle not present key/value");
	    // https://www.geeksforgeeks.org/hashmap-getordefaultkey-defaultvalue-method-in-java-with-examples/
	    System.out.println("Map.getOrdefualt()");
	    System.out.println(" default V getOrDefault(Object key, V defaultValue)");
	    System.out.println("	This method returns value mapped with the specified key, otherwise default value is returned.");
	    HashMap<String, Integer> mapConditionalPutGet  = new HashMap<>();
	 
	    //https://www.geeksforgeeks.org/hashmap-computeifpresentkey-bifunction-method-in-java-with-examples/
	    System.out.println("Map.computeIfPresent()");
	    System.out.println(" Object computeIfPresent(Object key, BiFunction remappingFunction)");
	    System.out.println("	This method returns new remapped value associated with the specified key, or null if mapping returns null.");
	    mapConditionalPutGet.put("a", 12);
	    mapConditionalPutGet.put("b", 39);
	    Integer defaultVal = mapConditionalPutGet.getOrDefault("z", 56);
	    mapConditionalPutGet.computeIfPresent("a", (key,value) -> 999);
	    System.out.println("mapConditionalPutGet.getOrDefault(\"z\", 56) : " + mapConditionalPutGet.getOrDefault("z", 56));
	    System.out.println("mapConditionalPutGet.computeIfPresent(\"a\", (key,value) -> 999) :" + mapConditionalPutGet.computeIfPresent("a", (key,value) -> 999));

	}

}


