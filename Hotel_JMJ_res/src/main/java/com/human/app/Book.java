package com.human.app;

public class Book {
	String roomname;
	int bookcode;
	String type;
	String roomtype;
	int txtNum;
	int human;
	String checkin;
	String checkout;
	int total;
	String name;
	String mobile;
 public Book() {}
public Book(String roomname, int bookcode, String type, String roomtype, int txtNum, int human, String checkin,
		String checkout, int total, String name, String mobile) {
	this.roomname = roomname;
	this.bookcode = bookcode;
	this.type = type;
	this.roomtype = roomtype;
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
public void setRoomname(String roomname) {
	this.roomname = roomname;
}
public int getBookcode() {
	return bookcode;
}
public void setBookcode(int bookcode) {
	this.bookcode = bookcode;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getRoomtype() {
	return roomtype;
}
public void setRoomtype(String roomtype) {
	this.roomtype = roomtype;
}
public int getTxtNum() {
	return txtNum;
}
public void setTxtNum(int txtNum) {
	this.txtNum = txtNum;
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
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
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
