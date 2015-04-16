package com.kael.util;
public class MathUtil {

	/**
	 * 以mod取整
	 * 
	 * @param src
	 * @param mod
	 * @return
	 */
	public static int round(int src, int mod) {
		return round((float) src, mod);
	}

	public static int round(float src, int mod) {
		return Math.round(src / mod) * mod;
	}

	public static long round(double src, int mod) {
		return Math.round(src / mod) * mod;
	}

	public static long round(long src, int mod) {
		return round((double) src, mod);
	}

	public static int floor(int src, int mod) {
		return (int) (Math.floor(src / mod) * mod);
	}
	
	public static int ceil(int src, int mod) {
		return (int) (Math.ceil(src / mod) * mod);
	}
	
	/**
	 * MROUND rounds up, away from zero, if the remainder of dividing number by
	 * multiple is greater than or equal to half the value of multiple.
	 * 
	 * @param number
	 *            is the value to round.
	 * @param multiple
	 *            is the multiple to which you want to round number.
	 * @return Returns a number rounded to the desired multiple.
	 */
	public static float mround(double number, float multiple) {
		if (number * multiple < 0)
			throw new IllegalArgumentException("Arguments hava different signs!");
		return Math.round(number / multiple) * multiple;
	}
	
	public static boolean between(int src, int a, int b) {
		if(a < b) {
			return src > a && src < b;
		}
		
		return src > b && src < a;
	}
	
	public static boolean betweenOrEqual(int src, int a, int b) {
		return src == a || src == b || between(src, a, b);
	}
	
	public static boolean between(long src, long a, long b) {
		if(a < b) {
			return src > a && src < b;
		}
		
		return src > b && src < a;
	}
	
	public static boolean betweenOrEqual(long src, long a, long b) {
		return src == a || src == b || between(src, a, b);
	}
	
}