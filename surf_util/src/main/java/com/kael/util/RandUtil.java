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
}
