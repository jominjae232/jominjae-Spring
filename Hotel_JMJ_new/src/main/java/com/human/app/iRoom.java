package com.human.app;

import java.util.ArrayList;

public interface iRoom {
	ArrayList<Roominfo> getRoomList();
	ArrayList<Roominfo> doFindAvailable(String checkin, String checkout);
	ArrayList<Booking> getBookingList(String checkin, String checkout);
	ArrayList<Book> getBookList(String checkin,String checkout);
	ArrayList<RoomType> getRoomType();
	void doDeleteRoom(int roomcode);
	void doDeleteBooking(int bookcode);
	void doAddRoom(String roomname,int roomtype,int howmany,int howmuch);
	void doAddBooking(String roomname,int roomcode,int txtNum,int human,String checkin, String checkout,int total,String name, String mobile);
	void doUpdateRoom(int roomcode,String roomname,int roomtype,int howmany,int howmuch);
	void doUpdatebooking(int bookcode,int human,String txtNames,String txtmobile);
	void doSignin(String txtname,String loginid, String passcode);
	int doCheckUser(String userid,String passcode);
	//void doAddFind(String checkin,String checkout);
	
	
}
