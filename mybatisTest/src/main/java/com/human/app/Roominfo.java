package com.human.app;

public class Roominfo {
	private int roomcode;
	private String roomname;
	private String typename;
	private int howmany;
	private int howmuch;
	public Roominfo() {}
	public Roominfo(int roomcode, String roomname, String typename, int howmany, int howmuch) {
		this.roomcode = roomcode;
		this.roomname = roomname;
		this.typename = typename;
		this.howmany = howmany;
		this.howmuch = howmuch;
	}
	public int getRoomcode() {
		return roomcode;
	}
	public String getRoomname() {
		return roomname;
	}
	public String getTypename() {
		return typename;
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
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public void setHowmany(int howmany) {
		this.howmany = howmany;
	}
	public void setHowmuch(int howmuch) {
		this.howmuch = howmuch;
	}
	
}