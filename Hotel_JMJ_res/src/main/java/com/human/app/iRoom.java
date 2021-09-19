package com.human.app;

import java.util.ArrayList;

public interface iRoom {
	
	/* 객실관리 페이지의 객실 리스트를 출력하는 코드입니다. */
	ArrayList<Roominfo> getRoomList();
	/*//객실관리 페이지의 객실 리스트를 출력하는 코드입니다. */
	
	/* 방의 리스트를 출력하는 코드입니다. */
	ArrayList<Roominfo> doFindAvailable(String checkin, String checkout);
	/* // 방의 리스트를 출력하는 코드입니다. */
	
	ArrayList<Booking> getBookingList(String checkin, String checkout);
	
	/* 예약된 객실 목록을 출력하는 코드입니다. */
	ArrayList<Book> getBookList(String checkin,String checkout);
	/*// 예약된 객실 목록을 출력하는 코드입니다. */
	
	ArrayList<RoomType> getRoomType();
	void doDeleteRoom(int roomcode);
	
	/* 예약이 완료된 객실의 데이터를 지우는 코드입니다. */
	void doDeleteBooking(int bookcode);
	/* 예약이 완료된 객실의 데이터를 지우는 코드입니다. */
	
	
	/* 객실목록 리스트에 객실을 추가하는 코드입니다. */
	void doAddRoom(String roomname,int roomtype,int howmany,int howmuch);
	/* //객실목록 리스트에 객실을 추가하는 코드입니다. */
	
	/* 객실목록 리스트에 객실을 업데이트 하는 코드입니다. */
	void doUpdateRoom(int roomcode,String roomname,int roomtype,int howmany,int howmuch);
	/* //객실목록 리스트에 객실을 업데이트 하는 코드입니다. */
	
	/* 예약된 객실에 값을 추가하는 코드입니다. */
	void doAddBooking(String roomname,int roomcode,int txtNum,int human,
					  String checkin,String checkout,int total,String name,String mobile);
	/* //예약된 객실에 값을 추가하는 코드입니다. */
	
	
	
	/* 예약된 객실의 데이터를 업로드 하는 코드입니다. */
	void doUpdatebooking(int bookcode,int human,String txtNames,String txtmobile);
	/* //예약된 객실의 데이터를 업로드 하는 코드입니다. */
	
	//회원가입을 하는 코드입니다.
	void doSignin(String txtname,String loginid, String passcode);
	//회원가입을 하는 코드입니다.
	
	/* 등록된 유저가 있는지 확인하는 코드입니다. */
	int doCheckUser(String userid,String passcode);
	/* 등록된 유저가 있는지 확인하는 코드입니다. */
	
	//void doAddFind(String checkin,String checkout);
	
	
}
