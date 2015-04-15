package com.kael.util;

import com.kael.util.TimeUtil.TimeSource;

public class TimeManager {
	
	public static void changeTime(long timeInMills){
		final long diff = timeInMills - System.currentTimeMillis();
		
		TimeSource ts = new TimeSource(diff) {
			
			@Override
			public long currentTimeMillis() {
				return System.currentTimeMillis() + diff;
			}
		};
		
		TimeUtil.setCurrentSource(ts);
	}

}
