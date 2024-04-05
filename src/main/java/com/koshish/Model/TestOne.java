package com.koshish.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestOne {

	private int id;
	private int name;
	
	public TestOne() {}
	
	public TestOne(int id, int name) {
		this.id = id;
		this.name = name;
	}
	
}
