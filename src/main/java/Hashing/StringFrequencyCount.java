package Hashing;

/* https://www.hackerearth.com/practice/data-structures/hash-tables/basics-of-hash-tables/tutorial/
 *  You are required to count the frequency of all the characters in this string.
		string S = “ababcd”
		Output
		a 2
		b 2
		c 1
		d 1
		e 0
		f 0
		…
		z 0
 * */
public class StringFrequencyCount {
	
   static void frequencyCount(String st){
		System.out.println("frequencyCount for string " + st + " :");
		int[] frequency = new int[26];//english language has 26 alphabets
		for(char ch : st.toCharArray()){
			int index = hashFunc(ch);
			frequency[index]++;
		}
		for(char ch = 'a'; ch <= 'z'; ch++){
			System.out.println(ch + " : " + frequency[hashFunc(ch)]);
		}
	}
	
	static int hashFunc(char ch){
		return ch - 'a';
	}

	public static void main(String[] args) {
		String string01 = "ababcd";
		frequencyCount(string01);
	}

}
