package javeCorePractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

public class hashCode {
	
	public static class Pojo{
		String name;
		char rank;
		double degree;
		
		public Pojo(String name, char rank, double degree) {
			super();
			this.name = name;
			this.rank = rank;
			this.degree = degree;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(degree);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + rank;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pojo other = (Pojo) obj;
			if (Double.doubleToLongBits(degree) != Double.doubleToLongBits(other.degree))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (rank != other.rank)
				return false;
			return true;
		}
		
	}

	public static void main(String[] args) {
		
		System.out.println("Integer.hashCode(123) : " + Integer.hashCode(123));
		System.out.println("Long.hashCode(128859560600606L) : " + Long.hashCode(128859560600606L));
		System.out.println("Double.hashCode(1204.27) : " + Double.hashCode(1204.27));

		
		Double dvalue = Double.valueOf(1204.27);
		char cvalue = 'a';
		String stringValue = "hashing string";
		
		System.out.println("Double.valueOf(1204.27); dvalue.hashCode() : " + dvalue.hashCode());
		System.out.println("char cvalue = 'a' , hash code -> (int) cvalue : " + (int) cvalue );
		System.out.println("stringValue.hashCode() : " + stringValue.hashCode());

		char[] charArray = new char[] {'a','b','c'};
		List<String> listOfString = Arrays.asList(new String[]{"hash", "code", "for", "lists"});
		List<Double> listOfDoubles = Arrays.asList(new Double[]{-8484.09, 83737373733.98888});
		Set<Double> setsOfDoubles = new HashSet<>(listOfDoubles);
		Map<String, Long> mapOfStringLongs = new TreeMap<String, Long>(){{
			put("first", -1699954333L);
			put("second", 87L);
			put("third", -4L);
		}};
		System.out.println("Arrays.hashCode(charArray) : " + Arrays.hashCode(charArray));
		System.out.println("List<String> listOfString ; listOfString.hashCode() : " + listOfString.hashCode());
		System.out.println("Set<Double> setsOfDoubles ; setsOfDoubles.hashCode() : " + setsOfDoubles.hashCode());
		System.out.println("Map<String, Long> mapOfStringLongs ; mapOfStringLongs.hashCode() : " + mapOfStringLongs.hashCode());
		System.out.println("Objects.hash(Object ...); Objects.hash(\"firstName\", \"lastName\", 'b',  Integer.valueOf(1984), Long.valueOf(127473282l)) : " + 
				Objects.hash("firstName", "lastName", 'b',  Integer.valueOf(1984), Long.valueOf(127473282l)));

		System.out.println("\nObjects.hash(Object ... values) /  Objects.hashCode(Object)");
		Pojo pojo = new Pojo("john", 'a', 19.76);
		System.out.println("Objects.hash(pojo.name, pojo.rank, pojo.degree)) : " 
				+ Objects.hash(pojo.name, pojo.rank, pojo.degree));
		System.out.println("Objects.hashCode(pojo) : " + Objects.hashCode(pojo));
	}

}
