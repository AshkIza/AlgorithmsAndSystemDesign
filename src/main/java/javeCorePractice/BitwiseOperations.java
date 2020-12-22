package javeCorePractice;

import java.math.BigInteger;

public class BitwiseOperations {

	public static void main(String[] args) {
		System.out.println("BITwise shift operations - signed/unsigned");
		System.out.println("-10/4  vs -10>>2");
		System.out.print(-10/4);
		System.out.print("     ");
		System.out.println(-10>>2);
		
		System.out.println("-30/8  vs -30>>3");
		System.out.print(-30/8);
		System.out.print("     ");
		System.out.println(-30>>3);
		
		System.out.println("10/4  vs 10>>2");
		System.out.print(10/4);
		System.out.print("     ");
		System.out.println(10>>2);
		
		System.out.println("-10>>>2");
		System.out.println(-10>>>2);
		
		System.out.println("\nInterger <---> binary/hex string");
		System.out.println("Integer.toBinaryString(-10) : " + Integer.toBinaryString(-10));
		System.out.println("Integer.toBinaryString(Integer.MAX_VALUE) : " + Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println("Integer.toBinaryString(Integer.MIN_VALUE : " + Integer.toBinaryString(Integer.MIN_VALUE));
	
		System.out.println("Integer.toHexString(Integer.MAX_VALUE) : " + Integer.toHexString(Integer.MAX_VALUE));
		System.out.println("Integer.toHexString(Integer.MIN_VALUE) : " + Integer.toHexString(Integer.MIN_VALUE));
		
		System.out.println("Integer.parseInt(\"7fffffff\", 16) : " + Integer.parseInt("7fffffff", 16));
		System.out.println("(int)Long.parseLong(\"80000000\", 16) : " + (int)Long.parseLong("80000000", 16));
		System.out.println("Integer.parseUnsignedInt(\"80000000\", 16) : " + Integer.parseUnsignedInt("80000000", 16));

		
		System.out.println("\ncast largeLongValue to int : ");
		System.out.println("long largeLongValue = Long.valueOf(Integer.MAX_VALUE) + 1984L");
		long largeLongValue = Long.valueOf(Integer.MAX_VALUE) + 1984L;
		System.out.println("largeLongValue : " + largeLongValue);
		System.out.println("(int)largeLongValue " + (int)largeLongValue);
		
		System.out.println("\njava int overflow and underflow");
		System.out.println("int underflow");
		int value = Integer.MIN_VALUE+2;
		for(int i = 0; i < 6; i++, value--) {
		    System.out.println(value);
		}

		System.out.println("int overflow");
		value = Integer.MAX_VALUE-1;
		for(int i = 0; i < 4; i++, value++) {
		    System.out.println(value);
		}
		
		
		System.out.println("BigInteger - does not overflow!");
		BigInteger largeValue = new BigInteger(Integer.valueOf(Integer.MAX_VALUE -1).toString());
		for(int i = 0; i < 4; i++) {
			largeValue = largeValue.add(BigInteger.ONE);
		    System.out.println(largeValue);
		}
		

		





		
		
		
		
		

		

	}

}
