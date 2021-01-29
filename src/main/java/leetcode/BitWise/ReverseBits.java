package leetcode.BitWise;

public class ReverseBits {
	
	public static int reverseBits(int n) {
        int left = Integer.MIN_VALUE;
        int right = 1;//right bit mask
        for(int i = 1; i <= 16; i++){//32/2 = 16
            int a = (n & left) != 0 ? 1 : 0;
            int b = (n & right) != 0 ? 1 : 0;
            if((a ^ b) == 1) {//if a and b the same no need to reverse
                n = n ^ (right | left);//negate bit x ^ 1 = ~x ; x ^ 0 = x
            }
            left >>>= 1;//unsigned right-shift
            right <<= 1;//left-shift
        }
        return n;
    }

	public static void main(String[] args) {
		int case1 = 43261596;
		int reversed_case1 = reverseBits(case1);
		System.out.println("case1 : " + case1 + 
				", reversed_case1 : " + reversed_case1);
		
		
		System.out.println("\nInteger.MAX_VALUE : 0111..111 or 2^31-1");
		System.out.println("Integer.MIN_VALUE : 1000..000 or -2^31");
		System.out.println("Integer.MAX_VALUE + 1 = Integer.MIN_VALUE");
		System.out.println("Integer.MIN_VALUE 1 1 = Integer.MAX_VALUE");
		
		System.out.println("\nhow to create sign bit mask 1000..00");
		int two_pow_30 = (int)Math.pow(2, 30);
		System.out.println("	2 ^ 30 << 1 : " + (two_pow_30 << 1));
		System.out.println("	Integer.MIN_VALUE : " + Integer.MIN_VALUE);
		System.out.println("	Integer.MAX_VALUE + 1 : " + (int)(Integer.MAX_VALUE + 1));
		System.out.println("NOTE: you can not use 2^31 (int Math.pow(2,31)) for sign bit mask since 2 ^31 > Intger.MAX_VALUE");
		
		System.out.println("\nunsigned right-shift for bit masks");
		System.out.println("signed right-shift for dividing integer by 2 (while maitning its sign)");
		System.out.println("	unsigned right-shift (does NOT maintain sign bit)");
		System.out.println("		Integer.MIN_VALUE >>> 1");
		System.out.println("		100..00 >>> 1 : 010..000");
		System.out.println("	signed right-shift (maintains sign bit)");
		System.out.println("		Integer.MIN_VALUE >> 1");
		System.out.println("		100..00 >>> 1 : 110..000");


	}

}
