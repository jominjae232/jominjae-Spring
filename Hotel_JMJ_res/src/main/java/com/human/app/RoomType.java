package com.human.app;

public class RoomType {
	private int typecode;
	private String name;
	public RoomType() {}
	public RoomType(int typecode, String name) {
		this.name = name;
		this.typecode = typecode;
	}
	public String getName() {
		return name;
	}
	public int getTypecode() {
		return typecode;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTypecode(int typecode) {
		this.typecode = typecode;
	}
}
