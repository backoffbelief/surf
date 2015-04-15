package com.kael;

public class Post {

	private int id;
	private int tid;
	private String  subject;
	private String message;
	private long datetime;
	public Post(int id, int tid, String subject, String message, long datetime) {
		super();
		this.id = id;
		this.tid = tid;
		this.subject = subject;
		this.message = message;
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return String.format(
				"Post [id=%s, tid=%s, subject=%s, message=%s, datetime=%s]",
				id, tid, subject, message, datetime);
	}
	
}
