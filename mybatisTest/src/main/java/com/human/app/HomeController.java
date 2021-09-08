package com.human.app;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private HttpSession session;
	@Autowired
	private SqlSession sqlSession;
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping("/")
	public String login(HttpServletRequest hsr, Model model) {
		return "home";
		
	}
	@RequestMapping("/home")
	public String login3(HttpServletRequest hsr,Model model) {
		return "hoom";
	}
	@RequestMapping("/login")
	public String login1(HttpServletRequest hsr, Model model) {
		return "login";
	}
	@RequestMapping("/newbie")
	public String newbie(HttpServletRequest hsr, Model model) {
		return "newbie";
	}
	@RequestMapping(value="/signin",method = RequestMethod.POST,
			produces = "application/text; charset=utf8")
	public String newbie(HttpServletRequest hsr) {
		//디버깅 system.out.println이 하나또 안찎힘
	    System.out.println("debug");
		String txtname=hsr.getParameter("txtname");
		System.out.println("name["+txtname+"]");
		String loginid=hsr.getParameter("login");
		System.out.println("name["+loginid+"]");
		String passcode=hsr.getParameter("passcode");
		System.out.println("name["+passcode+"]");
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doSignin(txtname,loginid,passcode);
		return "/home";
	}
	@RequestMapping("/viewinfo")
	public String show(@RequestParam String userid, @RequestParam String passcode, Model model) {
		model.addAttribute("login", userid);	
		model.addAttribute("newbie", passcode);
		return "viewinfo";
	}
	@RequestMapping("/newinfo")
	public String sss(@RequestParam String name,@RequestParam String id, @RequestParam String pass,
					@RequestParam String mobile, Model model) {
		model.addAttribute("n",name);
		model.addAttribute("i",id);
		model.addAttribute("p",pass);
		model.addAttribute("m",mobile);
		return "newinfo";
	}
	@RequestMapping(value="/check_user",method = RequestMethod.POST)
	public String check_user(HttpServletRequest hsr,Model model) {
		String userid=hsr.getParameter("userid");
		String passcode=hsr.getParameter("passcode");
		//System.out.println(userid); == userid 값 받아오는지 확인하는 디버그용 코드
		//System.out.println(passcode); == passcode 값 받아오는지 확인하는 디버그용 코드
		iRoom room=sqlSession.getMapper(iRoom.class);
		int n=room.doCheckUser(userid,passcode);
		System.out.println(n);
		if(n>0) {
			HttpSession session=hsr.getSession();
			session.setAttribute("loginid",userid);		
			return "booking";//RequestMapping의 경로 이동
		} else { //비등록회원
			return "home";
		}		
	}
	
	@RequestMapping(value="/booking",method = RequestMethod.GET)
	public String booking(HttpServletRequest hsr,Model model) {
		HttpSession session=hsr.getSession();
		String loginid=(String)session.getAttribute("loginid");
		if(loginid==null) {
			return "redirect:/home"; //.jsp생략
		} else {
			iRoom room = sqlSession.getMapper(iRoom.class);
			iRoom room_type = sqlSession.getMapper(iRoom.class);
			ArrayList<RoomType> roomtype=room.getRoomType();
			model.addAttribute("roomtype",roomtype);
				return "/booking";
		}
	}
	
	/*
	@RequestMapping(value="booking",method = RequestMethod.GET)
	public String booking(HttpServletRequest hsr,Model model) {
		HttpSession session=hsr.getSession(true);
		String loginid=(String)session.getAttribute("loginid");
		
		iRoom room=sqlSession.getMapper(iRoom.class);
		iRoom room_type = sqlSession.getMapper(iRoom.class);
		ArrayList<RoomType> roomtype=room.getRoomType();
		model.addAttribute("list_types",roomtype);
			return "booking";
	}
	*/
	
	@RequestMapping("/room")
	public String room(HttpServletRequest hsr,Model model) {
		HttpSession session=hsr.getSession();
		if(session.getAttribute("loginid")==null) {
			return "redirect:login"; //.jsp 생략 
		}
		//여기서 interface호출하고 결과를 room.jsp에 전달
		iRoom room=sqlSession.getMapper(iRoom.class);
		/*
		*ArrayList<Roominfo> roominfo=room.getRoomList();
		*model.addAttribute("list",roominfo);
		*/
		
		iRoom room_type = sqlSession.getMapper(iRoom.class);
		ArrayList<RoomType> roomtype=room.getRoomType();
		model.addAttribute("list_type",roomtype);
		return "room";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession();
		session.invalidate();
		return "redirect:/";
}
	
	/* JSON 사용 */
	// produces="application/text; charset=utf8" == 한글깨짐 방지 코드 
	@RequestMapping(value="/getRoomList",method=RequestMethod.POST,
					produces="application/text; charset=utf8")
	@ResponseBody
	public String getRoomList(HttpServletRequest hsr) {
		//System.out.println("debugegetRoomlist"); == RoomList로 들어오는지 확인하는 디버그 코드
		iRoom room=sqlSession.getMapper(iRoom.class);
		ArrayList<Roominfo> roominfo=room.getRoomList();
		// 찾아진 데이터로 JSONArray만들기
		JSONArray ja = new JSONArray(); //JSONArray ja 생성
		for(int i=0; i<roominfo.size();i++) { //roominfo 크기만큼 반복
			JSONObject jo = new JSONObject(); // JSONObject jo 생성
			jo.put("roomcode", roominfo.get(i).getRoomcode()); // JSONObject jo에 데이터 입력
			jo.put("roomname", roominfo.get(i).getRoomname()); // JSONObject jo에 데이터 입력
			jo.put("typename", roominfo.get(i).getTypename()); // JSONObject jo에 데이터 입력
			jo.put("howmany", roominfo.get(i).getHowmany()); // JSONObject jo에 데이터 입력
			jo.put("howmuch", roominfo.get(i).getHowmuch()); // JSONObject jo에 데이터 입력
			ja.add(jo); // JSONArray ja에 JSONObject jo에 있는 데이터 값 입력
		}
		// System.out.println(ja.toString()); == debug용 코드 
		return ja.toString(); // JSON ja의 데이터를 문자열로 바꿈 
	}
	
	
	
	//예약 컨트롤러
	@RequestMapping(value="/getBookList",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String getBookingList(HttpServletRequest hsr) {
		
		iRoom book=sqlSession.getMapper(iRoom.class);
		
		String checkin=hsr.getParameter("checkin");
		String checkout=hsr.getParameter("checkout");
		ArrayList<Book> getBookList=book.getBookList(checkin,checkout);
		JSONArray jas = new JSONArray(); //JSONArray ja 생성
		for(int i=0; i<getBookList.size();i++) { //booking 크기만큼 반복
			JSONObject jos = new JSONObject(); // JSONObject jo 생성
			jos.put("roomname", getBookList.get(i).getRoomname()); // JSONObject jo에 데이터 입력
			//System.out.println("dd");
			//System.out.println(getBookList.get(i).getRoomname()+":Roomname");
			
			jos.put("bookcode", getBookList.get(i).getBookcode()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getBookcode()+":Bookcode");
			
			jos.put("type", getBookList.get(i).getType()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getType()+":getType");
			
			jos.put("txtNum", getBookList.get(i).getTxtNum()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getTxtNum()+"getMax_howmany");
			
			jos.put("human", getBookList.get(i).getHuman()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getHuman()+"getHuman");
			
			jos.put("checkin", getBookList.get(i).getCheckin()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getCheckin()+"getCheckin");
			
			jos.put("checkout", getBookList.get(i).getCheckout()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getCheckout()+"getCheckout");
			
			jos.put("total", getBookList.get(i).getTotal()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getTotal()+"getTotal");
			
			jos.put("name", getBookList.get(i).getName()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getName()+"getName");
			
			jos.put("mobile", getBookList.get(i).getMobile()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getMobile()+"getMobile");
			
			jas.add(jos); // JSONArray ja에 JSONObject jo에 있는 데이터 값 입력
		}
		System.out.println(jas.toString());// == debug용 코드 
		return jas.toString(); // JSON ja의 데이터를 문자열로 바꿈 
	}
	//예약 컨트롤러
	
	
	
	/*
	@RequestMapping(value="/getBookingList",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String getBookingList(HttpServletRequest hsr) {
		iRoom booking=sqlSession.getMapper(iRoom.class);
		ArrayList<Booking> getBookingList=booking.getBookingList();
	JSONArray ja1 = new JSONArray(); //JSONArray ja 생성
	for(int i=0; i<getBookingList.size();i++) { //booking 크기만큼 반복
		JSONObject jo1 = new JSONObject(); // JSONObject jo 생성
		jo1.put("roomname", getBookingList.get(i).getRoomname()); // JSONObject jo에 데이터 입력
		jo1.put("bookcode", getBookingList.get(i).getBookcode()); // JSONObject jo에 데이터 입력
		jo1.put("roomcode", getBookingList.get(i).getRoomcode()); // JSONObject jo에 데이터 입력
		jo1.put("txtNum", getBookingList.get(i).getTxtNum()); // JSONObject jo에 데이터 입력
		jo1.put("human", getBookingList.get(i).getHuman()); // JSONObject jo에 데이터 입력
		jo1.put("checkin", getBookingList.get(i).getCheckin()); // JSONObject jo에 데이터 입력
		jo1.put("checkout", getBookingList.get(i).getCheckout()); // JSONObject jo에 데이터 입력
		jo1.put("total", getBookingList.get(i).getTotal()); // JSONObject jo에 데이터 입력
		jo1.put("txtName", getBookingList.get(i).getName()); // JSONObject jo에 데이터 입력
		jo1.put("txtmobile", getBookingList.get(i).getMobile()); // JSONObject jo에 데이터 입력
		ja1.add(jo1); // JSONArray ja에 JSONObject jo에 있는 데이터 값 입력
	}
	//System.out.println(ja.toString());// == debug용 코드 
	return ja1.toString(); // JSON ja의 데이터를 문자열로 바꿈 
}
	*/
	
	@RequestMapping(value="/deleteRoom",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String deleteRoom(HttpServletRequest hsr) {
		int roomcode=Integer.parseInt(hsr.getParameter("roomcode"));
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doDeleteRoom(roomcode);
		return "ok";
	}
	
	@RequestMapping(value="/addRoom",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String addRoom(HttpServletRequest hsr) {
		String rname=hsr.getParameter("roomname");
		int rtype=Integer.parseInt(hsr.getParameter("roomtype"));
		int howmany=Integer.parseInt(hsr.getParameter("howmany"));
		int howmuch=Integer.parseInt(hsr.getParameter("howmuch"));
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doAddRoom(rname,rtype,howmany,howmuch);
		return "ok";
	}
	
	/*/////////////////
	@RequestMapping(value="/addFind",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String doaddFind(HttpServletRequest hsr) {
		System.out.println("debugFind");
		String checkin=hsr.getParameter("checkin");
		System.out.println(checkin+"checkin");
		String checkout=hsr.getParameter("checkout");
		System.out.println(checkout+"checkout");
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doAddFind(checkin,checkout);
		return "ok";
	}
	*/
		
	@RequestMapping(value="/addbooking",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String addbooking(HttpServletRequest hsr) {
		System.out.println("addbooking");
		//int bookcode=Integer.parseInt(hsr.getParameter("bookcode"));
		//System.out.println(bookcode);
		
		String roomname=hsr.getParameter("roomname");
		System.out.println(roomname+"객실이름");
		
		int roomcode=Integer.parseInt(hsr.getParameter("roomcode"));
		System.out.println(roomcode+"객실타입");
		
		int txtNum=Integer.parseInt(hsr.getParameter("txtNum"));
		System.out.println(txtNum+"예약인원");
		
		int human=Integer.parseInt(hsr.getParameter("human"));
		System.out.println(human+"최대인원");
		
		String checkin=hsr.getParameter("checkin");
		System.out.println(checkin+"체크인");
		
		String checkout=hsr.getParameter("checkout");
		System.out.println(checkout+"체크아웃");
		
		int total=Integer.parseInt(hsr.getParameter("total"));
		System.out.println(total+"가격");
		
		String bname=hsr.getParameter("txtName");
		System.out.println(bname+"예약자명");
		
		String bmobile=hsr.getParameter("txtmobile");
		System.out.println(bmobile+"모바일번호");
		
		iRoom room=sqlSession.getMapper(iRoom.class);
		System.out.println(roomname+","+roomcode+","+txtNum+","+human+","+checkin+","+checkout+","+total+","+bname+","+bmobile);
		room.doAddBooking(roomname,roomcode,txtNum,human,checkin,checkout,total,bname,bmobile);
		return "ok";
	}
	
	/*
	@RequestMapping(value="/selFind",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String selFind(HttpServletRequest hsr) {
		System.out.println("selFind");
		//int bookcode=Integer.parseInt(hsr.getParameter("bookcode"));
		//System.out.println(bookcode);
		
		int bookcode=Integer.parseInt(hsr.getParameter("bookcode"));
		System.out.println(bookcode+"bookcode");
		
		String roomname=hsr.getParameter("roomname");
		System.out.println(roomname+"roomname");
		
		String type=hsr.getParameter("type");
		System.out.println(type+"type");
		
		int max_howmany=Integer.parseInt(hsr.getParameter("max_howmany"));
		System.out.println(max_howmany+"max_howmany");
		
		int human=Integer.parseInt(hsr.getParameter("human"));
		System.out.println(human+"human");
		
		String checkin=hsr.getParameter("checkin");
		System.out.println(checkin+"체크아웃");
		
		String checkout=hsr.getParameter("checkout");
		System.out.println(checkout+"체크아웃");
		
		int total=Integer.parseInt(hsr.getParameter("total"));
		System.out.println(total+"가격");
		
		String name=hsr.getParameter("txtName");
		System.out.println(name+"예약자명");
		
		String mobile=hsr.getParameter("txtmobile");
		System.out.println(mobile+"모바일번호");
		
		iRoom room=sqlSession.getMapper(iRoom.class);
		System.out.println(bookcode+","+human+","+checkin+","+checkout+","+total+","+name+","+mobile);
		room.getBookList(bookcode,roomname,type,max_howmany,human,checkin,checkout,total,name,mobile);
		return "ok";
	}
	*/
	
	@RequestMapping(value="/updateRoom",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String updateRoom(HttpServletRequest hsr) {
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doUpdateRoom(Integer.parseInt(hsr.getParameter("roomcode")),
				hsr.getParameter("roomname"), 
				Integer.parseInt(hsr.getParameter("roomtype")),
				Integer.parseInt(hsr.getParameter("howmany")),
				Integer.parseInt(hsr.getParameter("howmuch")));
		return "ok";
	}






}
/*
		
		room.doUpdateRoom(Integer.parseInt(hsr.getParameter("roomcode")),
				hsr.getParameter("roomname"),
				Integer.parseInt(hsr.getParameter("roomtype")),
				Integer.parseInt(hsr.getParameter("howmany")),
				Integer.parseInt(hsr.getParameter("howmuch")));		
		return "ok";
	}
}	
*/
