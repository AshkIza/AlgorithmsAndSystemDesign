package javeCorePractice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MethodReference {
    private static final Map<String, Integer> REDIX = new HashMap<String, Integer>() {{
    	put("x", 16);
    	put("b", 2);
    	put("d", 10);
    }};
    
    static class NumericData {
    	String d;
    	int redix;
    	Integer number;
    	String numString;
    	public NumericData(String d, BiFunction<String, Integer, Integer> parser) {
			this.d = d;
			 if(d.length() > 2 && d.startsWith("0")) {
				 this.numString = d.substring(2);
				 this.redix = REDIX.get(d.substring(1,2));
				 this.number =  parser.apply(numString, redix);
			 }
		}
		public Integer getNumber() {
			return number;
		}
		public String getNumberString() {
			return numString;
		}
		
		boolean lessThan(NumericData other) {
			return this.number.compareTo(other.getNumber()) < 0;
		}
		
		
    	boolean isHex() {
			return isValid() && redix == 16;
    	}
    	
    	boolean isValid() {
    		return redix!=0 && number!=null;
    	}
    }
    

	 //BiFunction<String, Integer, Integer> stringParser = (s, i) ->  Integer.parseUnsignedInt(s, i);
	 static BiFunction<String, Integer, Integer> stringParser = Integer::parseUnsignedInt;

	 //static Function<String, Integer> mapper = s ->  mapTo(s);
	 static Function<String, Integer> mapper = MethodReference::mapTo;

	 

	 static Integer mapTo(String s)  {
		 if(s.length() > 2 && s.startsWith("0")) {
			 String number = s.substring(2);
			 Integer redix = REDIX.get(s.substring(1,2));
			 return stringParser.apply(number, redix);
		 }
		 return null;
	 }

	public static void main(String[] args) {
		
		List<String> st = Arrays.asList(new String[] {"0x7fffffff", "0b11001100", "0d123456"});
		List<Integer> in = st.stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
		
		System.out.println(st);
		System.out.println(in);
		
		

		List<NumericData> input = Arrays.asList(
				new NumericData("0x7fffffff", Integer::parseUnsignedInt),
				new NumericData("0x80000000", Integer::parseUnsignedInt),
				new NumericData("0d16729", Integer::parseUnsignedInt),
				new NumericData("0xffffffff", Integer::parseUnsignedInt),
				new NumericData("0x", Integer::parseInt)
		);
		
		NumericData zeroObject = new NumericData("0b0", Integer::parseInt);
		List<Integer> positivenumbers = input.stream()
			.filter(NumericData::isValid)
			.filter(zeroObject::lessThan) // filter(d -> zeroObject.lessThan(d))
			.map(NumericData::getNumber)// map(d -> d.getNumber())
			.collect(Collectors.toList());
		
		List<String> hexNumbers = input.stream()
				.filter(NumericData::isValid)
				.filter(NumericData::isHex) 
				.map(NumericData::getNumberString)
				.collect(Collectors.toList());
		
		System.out.println("positivenumbers : " + positivenumbers);
		System.out.println("hexNumbers : " + hexNumbers);

		
	
	}
		 
	
	
	
	
	
	
	
	
	
	

}
