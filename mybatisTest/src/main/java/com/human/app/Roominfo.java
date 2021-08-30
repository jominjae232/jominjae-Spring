package com.human.app;

public class Roominfo {
	private int roomcode;
	private String name;
	private int type;
	private int howmany;
	private int howmuch;
	public Roominfo() {}
	public Roominfo(int roomcode, String name, int type, int howmany, int howmuch) {
		//super();
		this.roomcode = roomcode;
		this.name = name;
		this.type = type;
		this.howmany = howmany;
		this.howmuch = howmuch;
	}
	public int getRoomcode() {
		return roomcode;
	}
	public String getName() {
		return name;
	}
	public int getType() {
		return type;
	}
	public int getHowmany() {
		return howmany;
	}
	public int getHowmuch() {
		return howmuch;
	}
	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setHowmany(int howmany) {
		this.howmany = howmany;
	}
	public void setHowmuch(int howmuch) {
		this.howmuch = howmuch;
	}
}