package com.github.sigrist.spotify;

import java.util.Iterator;

public class Test implements Iterator<String> {

	public static void main(String[] args) {
		Test test = new Test("a", "b", "c");
		
		while (test.hasNext()) {
			String next = (String) test.next();
			
			System.out.println(next);
		}
	}

	private String[] values;
	private int index;
	
	public Test(String... values) {
		this.values = values;
		this.index = 0;
	}
	
	
	@Override
	public boolean hasNext() {
		return values.length > index;
	}

	@Override
	public String next() {
		
		String next = values[index];
		index++;
		return next;
	}

}
