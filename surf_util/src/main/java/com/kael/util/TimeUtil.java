package com.kael.util;

/**
 * 时间
 * 
 * @author hanyuanliang 2015-4-15 下午4:19:57
 */
public class TimeUtil {

	private static TimeSource currentSource = new DefaultStandTimeSource();
	
	private static class DefaultStandTimeSource extends TimeSource{

		@Override
		public long currentTimeMillis() {
			return System.currentTimeMillis();
		}
		
	}
	
	static abstract class TimeSource {

		final long diff;

		public TimeSource() {
			this(0);
		}

		public TimeSource(long diff) {
			this.diff = diff;
		}

		public abstract long currentTimeMillis();
	}

	public static long currentTimeMillis() {
		return currentSource.currentTimeMillis();
	}

	public static void setCurrentSource(TimeSource ts) {
		currentSource = ts;
	}

}
