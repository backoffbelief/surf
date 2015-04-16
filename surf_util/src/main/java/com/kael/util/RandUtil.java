package com.kael.util;

import java.util.Random;

public class RandUtil {

	private RandUtil() {
	}
	
	static ThreadLocal<Random> rs = new ThreadLocal<Random>(){

		@Override
		protected Random initialValue() {
			return new Random();
		}
		
	};

	private static Random getR(){
		return rs.get();
	}
	
	
	//[min,max)
	public static int rand(int min,int max){
		if(max <= min){
			throw new RuntimeException(String.format("[min=%d] >= [max=%d]", min,max));
		}
		int r = getR().nextInt((max - min) << 5);
		return (r >> 5) + min;
	}
	
	//[0,max)
	public static int rand(int max){
		return rand(0,max) ;
	}
	
	public static int randInArray(int[] array){
		int sum = 0;
		for (int i : array) {
			sum += i;
		}
		
		if(sum <= 0){
			throw new RuntimeException(String.format("[sum(array)=%d] <= 0", sum));
		}
		
	    final int r = rand(sum);
	    
	    sum = 0;
	    for (int i = 0; i < array.length; i++) {
			sum += array[i];
			if(r < sum ){
				return i;
			}
		}
	    throw new RuntimeException("cannot be here!");
	}
	
	
	public static boolean nextBoolean(){
		return getR().nextBoolean();
	}
	
	public static float nextFloat(){
		return getR().nextFloat();
	}
	
	/*
	 * @param f% 
	 */
	public static boolean randWithPercentage(float f){
	    return getR().nextFloat() < f / 100 ;
	}
}
