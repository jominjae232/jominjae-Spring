package com.human.app;

public class Booking {
private int bookcode;
private int roomcode;
private int human;
private String checkin;
private String checkout;
private String name;
private String mobile;
public Booking() {}
public Booking(int bookcode, int roomcode, int human, String checkin, String checkout, String name, String mobile) {
	this.bookcode = bookcode;
	this.roomcode = roomcode;
	this.human = human;
	this.checkin = checkin;
	this.checkout = checkout;
	this.name = name;
	this.mobile = mobile;
}
public int getBookcode() {
	return bookcode;
}
public void setBookcode(int bookcode) {
	this.bookcode = bookcode;
}
public int getRoomcode() {
	return roomcode;
}
public void setRoomcode(int roomcode) {
	this.roomcode = roomcode;
}
public int getHuman() {
	return human;
}
public void setHuman(int human) {
	this.human = human;
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
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
}
