package com.kael.util;

public class StringUtil {
	
	public static enum RndType {
		ONLY_LOWER,
		ONLY_UPPER,
		LETTERS,
		ONLY_NUMBERS,
		COMPLEX_NUM_LETTER;
	}

	static String rand(int len, RndType rt) {
		char[] cs = new char[len];
		for (int i = 0; i < len; i++) {
			switch (rt) {
			case ONLY_LOWER:
				cs[i] = (char) RandUtil.rand('a', 'z' + 1);
				break;
			case ONLY_UPPER:
				cs[i] = (char) RandUtil.rand('A', 'Z' + 1);
				break;
			case ONLY_NUMBERS:
				cs[i] = (char) RandUtil.rand('0', '9' + 1);
			default:
				break;
			}
		}
		return new String(cs);
	}

	public static void main(String[] args) {
		int c = 5;
		while(c-- >= 0){
			System.out.println(rand(10, RndType.ONLY_NUMBERS));
		}
	}
}
