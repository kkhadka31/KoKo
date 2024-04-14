package com.koshish.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class TestOne {

	private int id;
	private int name;
	
	public TestOne() {}
	
	public TestOne(int id, int name) {
		this.id = id;
		this.name = name;
	}
	
}
