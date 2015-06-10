package com.kael.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;


public class Sudo {
	
	static final int[] orginalTmps = {1,2,3,4,5,6,7,8,9};
	static final int LEN = 9;
	
	public static void main(String[] args) {
		int[][] sudoArray = {
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
		
		ConcurrentHashMap<Integer,int[]> arrMaps = new ConcurrentHashMap<Integer,int[]>();
		for (int i = 0; i < LEN; i++) {
			for (int j = 0; j < LEN; j++) {
				if(sudoArray[i][j] == 0 ){
					//arrMaps.put(key, value);
					int[] localTmp = Arrays.copyOf(orginalTmps, orginalTmps.length);
					boolean over = false;
					for(int x = 0; x < LEN ;x ++){
						if(sudoArray[x][j] != 0){
							localTmp = remove(localTmp, sudoArray[x][j]);
							if(localTmp.length == 1){
								sudoArray[i][j] = localTmp[0];
								refresh(sudoArray,arrMaps,i,j);
								over = true;
								break;
							}
						}
					}
					if(over){
						continue;
					}
					
					for(int x = 0; x < LEN ;x ++){
						if(sudoArray[i][x] != 0){
							localTmp = remove(localTmp, sudoArray[i][x]);
							if(localTmp.length == 1){
								sudoArray[i][j] = localTmp[0];
								over = true;
								refresh(sudoArray,arrMaps,i,j);
								break;
							}
						}
					}
					if(over){
						continue;
					}
					
					for (int x = i / 3 * 3; x < i / 3 * 3 + LEN / 3; x++) {
						for (int y = j / 3 * 3; y < j / 3 * 3 + LEN / 3; y++) {
							if (x != i && j != y && sudoArray[x][y] != 0) {
								localTmp = remove(localTmp, sudoArray[x][y]);
								if (localTmp.length == 1) {
									sudoArray[i][j] = localTmp[0];
									over = true;
									refresh(sudoArray, arrMaps, i, j);
									break;
								}
							}
						}
					}
					if(over){
						continue;
					}
					
					arrMaps.put(i * 9 + j, localTmp);
				}
			}
		}
		
		for(int[] arr : sudoArray)
			System.out.println(Arrays.toString(arr));
		
	}
	
	
	private static void refresh(int[][] sudoArray, ConcurrentHashMap<Integer,int[]> arrMaps,
			int i, int j) {
		if(arrMaps.isEmpty()){
			return ;
		}
		for(Iterator<Entry<Integer,int[]>> iterator = arrMaps.entrySet().iterator();iterator.hasNext();){
			Entry<Integer,int[]> next = iterator.next();
			if(next != null){
				int x = next.getKey()/9;
				int y = next.getKey()%9;
				int[] localTmp = next.getValue();
				if(x == i){
					localTmp = remove(localTmp, sudoArray[i][j]);
				}else if(y == j){
					localTmp = remove(localTmp, sudoArray[i][j]);
				}else if(x/3 == i/3 && y/3 == j/3 ) {
					localTmp = remove(localTmp, sudoArray[i][j]);
				}
				
				if(localTmp.length == 1){
					sudoArray[x][y] = localTmp[0];
					iterator.remove();
					refresh(sudoArray, arrMaps, x, y);
				}else{
//					next.value = localTmp;
					arrMaps.put(next.getKey(), localTmp);
				}
			}
		}
	}


	static int[] remove(int[] array ,int toRemove){
		int tmp;
		if ((tmp = Arrays.binarySearch(array, toRemove)) >= 0) {
			return ArrayUtils.remove(array, tmp);
		}
		return array;
	}
}
