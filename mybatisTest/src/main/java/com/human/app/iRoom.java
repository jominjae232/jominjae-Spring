package com.human.app;

import java.util.ArrayList;

public interface iRoom {
	ArrayList<Roominfo> getRoomList();
	ArrayList<Booking> getBookingList();
	ArrayList<RoomType> getRoomType();
	void doDeleteRoom(int roomcode);
	void doAddRoom(String roomname,int roomtype,int howmany,int howmuch);
	void doAddBooking(int roomcode, int human,String checkin, String checkout,String name, String mobile);
	void doUpdateRoom(int roomcode,String roomname,int roomtype,int howmany,int howmuch);
	void doSignin(String txtname,String loginid, String passcode);
	int doCheckUser(String userid,String passcode);
}
