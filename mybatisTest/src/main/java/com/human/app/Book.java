package com.human.app;

public class Book {
	String roomname;
	int bookcode;
	String type;
	int txtNum;
	int human;
	String checkin;
	String checkout;
	int total;
	String name;
	String mobile;
 public Book() {}
public Book(String roomname, int bookcode, String type, int txtNum, int human, String checkin, String checkout,
		int total, String name, String mobile) {
	this.roomname = roomname;
	this.bookcode = bookcode;
	this.type = type;
	this.txtNum = txtNum;
	this.human = human;
	this.checkin = checkin;
	this.checkout = checkout;
	this.total = total;
	this.name = name;
	this.mobile = mobile;
}
public String getRoomname() {
	return roomname;
}
public int getBookcode() {
	return bookcode;
}
public String getType() {
	return type;
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
public void setRoomname(String roomname) {
	this.roomname = roomname;
}
public void setBookcode(int bookcode) {
	this.bookcode = bookcode;
}
public void setType(String type) {
	this.type = type;
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
