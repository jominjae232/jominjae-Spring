package com.human.app;

public class Roominfo {
	private int roomcode;
	private String roomname;
	private String name;
	private String typename;
	private String type;
	private int howmany;
	private int howmuch;
	private String checkin;
	private String checkout;
	public Roominfo() {}
	public Roominfo(int roomcode, String roomname, String name, String typename, String type, int howmany, int howmuch,
			String checkin, String checkout) {
		this.roomcode = roomcode;
		this.roomname = roomname;
		this.name = name;
		this.typename = typename;
		this.type = type;
		this.howmany = howmany;
		this.howmuch = howmuch;
		this.checkin = checkin;
		this.checkout = checkout;
	}
	public int getRoomcode() {
		return roomcode;
	}
	public void setRoomcode(int roomcode) {
		this.roomcode = roomcode;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getHowmany() {
		return howmany;
	}
	public void setHowmany(int howmany) {
		this.howmany = howmany;
	}
	public int getHowmuch() {
		return howmuch;
	}
	public void setHowmuch(int howmuch) {
		this.howmuch = howmuch;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	
}