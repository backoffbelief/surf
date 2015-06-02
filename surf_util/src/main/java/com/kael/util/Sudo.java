package com.kael.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Sudo {
	
	static final int[][] sudoArray = {
			{ 0, 0, 0, 2, 0, 9, 0, 4, 0 },
			{ 5, 0, 0, 0, 7, 0, 9, 0, 2 }, 
			{ 7, 9, 0, 0, 6, 0, 1, 0, 0 },
			{ 0, 0, 0, 7, 8, 0, 0, 0, 9 }, 
			{ 0, 2, 8, 6, 0, 3, 4, 7, 0 },
			{ 9, 0, 0, 0, 2, 5, 0, 0, 0 }, 
			{ 0, 0, 4, 0, 5, 0, 0, 1, 6 },
			{ 2, 0, 9, 0, 4, 0, 0, 0, 3 }, 
			{ 0, 6, 0, 1, 0, 7, 0, 0, 0 },	
		}; 
	static final int totalSum = 405;
	
	static int totalVar= 0;
	
	static class P{
		final int i,j;

		public P(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	
	static class V{
		final int i,j;
		int[] pros ;
		public V(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		public V(int i, int j,int[] pros) {
			super();
			this.i = i;
			this.j = j;
			this.pros = pros;
		}
		
		public void setPros(int[] pros) {
			this.pros = pros;
		}

		public boolean remove(int v){
			try {
				pros = ArrayUtils.remove(pros, Arrays.binarySearch(pros, v));
			} catch (Exception e) {
			}
			return isOnlyOne();
		}

		boolean isOnlyOne(){
			return pros.length == 1;
		}

		boolean isEmpty(){
			return pros.length == 0;
		}

		@Override
		public String toString() {
			return String.format("V [i=%s, j=%s, pros=%s]", i, j,
					Arrays.toString(pros));
		}
		
		
	}
	
	public static void main(String[] args) {
		List<V> vs = new ArrayList<Sudo.V>();
//		boolean first = true;
		//while(totalVar < totalSum){
			
			for(int i = 0; i< sudoArray.length;i++){
				for (int j = 0; j < sudoArray[i].length; j++) {
					if(sudoArray[i][j] == 0){
						V e ;
						if(null !=(e = add(i, j))){
							vs.add(e);
						}else{
							remove(i, j, sudoArray[i][j], vs);
						}
//					}else{
//						if(first)
//							totalVar += sudoArray[i][j];
					}
				}
			}
			//first = false;
		//}
			
			
		
//		for(int[] arr :sudoArray){
//			System.out.println(Arrays.toString(arr));
//		}

		while(!vs.isEmpty()){
			for(Iterator<V> iterator = vs.iterator();iterator.hasNext();){
				V next = iterator.next();
				if(next.isOnlyOne()){
					iterator.remove();
				}
				
			}
		}
	
	}
	
	static final int[] orginalTmps = {1,2,3,4,5,6,7,8,9};
	
	static V add(int x, int y){
		int[] localTmp = Arrays.copyOf(orginalTmps, orginalTmps.length);
		for(int i = 0 ;i < 9 ;i++){
			if (sudoArray[i][y] != 0) {
//				sets.add(sudoArray[i][v.j]);
				try {
					localTmp = ArrayUtils.remove(localTmp, Arrays.binarySearch(localTmp, sudoArray[i][y]));
				} catch (Exception e) {
					continue;
				}
			}
		}

		for(int i = 0 ;i < 9 ;i++){
			if (sudoArray[x][i] != 0) {
//				sets.add(sudoArray[v.i][i]);
				try {
					localTmp = ArrayUtils.remove(localTmp, Arrays.binarySearch(localTmp, sudoArray[x][i]));
				} catch (Exception e) {
					//e.printStackTrace();
					continue;
				}
			}
		}
		int startI = (x/3)*3;
		int startJ = (y/3)*3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sudoArray[i + startI ][j + startJ] != 0) {
//					sets.add(sudoArray[i + startI][j + startJ]);
					try {
						localTmp = ArrayUtils.remove(localTmp, Arrays.binarySearch(localTmp, sudoArray[i + startI][j + startJ]));
					} catch (Exception e) {
						continue;
					}
				}
			}
		}
//		Integer[] tmp = sets.toArray(new Integer[0]);
		if(localTmp.length == 1){
			sudoArray[x][y] = localTmp[0];
			totalVar += sudoArray[x][y];
			return null;
		}
		return new V(x, y,localTmp);
	}
	
	static boolean remove(int x,int y,int v,List<V> vs){
		if(vs.isEmpty()){
			return false;
		}
		boolean flag = false;
		for(Iterator<V> iterator = vs.iterator();iterator.hasNext();){
			V e = iterator.next();
			if(x == e.i){
			    flag = refresh(iterator, e,v);
			}else if(y == e.j ){
				flag = refresh(iterator, e,v);
			}else {
				int startI = (x/3)*3;
				int startJ = (y/3)*3;
				
				if(e.i >= startI && e.i <= startI + 2 && e.j >= startJ && e.j <= startJ + 2){
					flag = refresh(iterator, e,v);
				}
			}
			
		}
		return flag;
	}
	
	
	static void rm(List<V> rms,Iterator<V> iterator, V e,int v){
	}

	private static boolean refresh(Iterator<V> iterator, V e,int v) {
		if(e.remove(v)){
			sudoArray[e.i][e.j] = e.pros[0];
			totalVar += sudoArray[e.i][e.j];
			iterator.remove();
		}
		return true;
	}
	
	
}
