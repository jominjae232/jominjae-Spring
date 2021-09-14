package com.human.app;

public class Booking {
private int bookcode;
private String roomname;
private int roomcode;
private int txtNum;
private int human;
private String checkin;
private String checkout;
private int total;
private String name;
private String mobile;
public Booking() {}
public Booking(int bookcode, String roomname, int roomcode, int txtNum, int human, String checkin, String checkout,
		int total, String name, String mobile) {
	this.bookcode = bookcode;
	this.roomname = roomname;
	this.roomcode = roomcode;
	this.txtNum = txtNum;
	this.human = human;
	this.checkin = checkin;
	this.checkout = checkout;
	this.total = total;
	this.name = name;
	this.mobile = mobile;
}
public int getBookcode() {
	return bookcode;
}
public String getRoomname() {
	return roomname;
}
public int getRoomcode() {
	return roomcode;
}
public int getTxtNum() {
	return txtNum;
}
public int getHuman() {
	return human;
}
public String getCheckin() {
	return checkin;
}
public String getCheckout() {
	return checkout;
}
public int getTotal() {
	return total;
}
public String getName() {
	return name;
}
public String getMobile() {
	return mobile;
}
public void setBookcode(int bookcode) {
	this.bookcode = bookcode;
}
public void setRoomname(String roomname) {
	this.roomname = roomname;
}
public void setRoomcode(int roomcode) {
	this.roomcode = roomcode;
}
public void setTxtNum(int txtNum) {
	this.txtNum = txtNum;
}
public void setHuman(int human) {
	this.human = human;
}
public void setCheckin(String checkin) {
	this.checkin = checkin;
}
public void setCheckout(String checkout) {
	this.checkout = checkout;
}
public void setTotal(int total) {
	this.total = total;
}
public void setName(String name) {
	this.name = name;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
}