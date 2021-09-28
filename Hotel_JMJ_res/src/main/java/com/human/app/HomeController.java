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
	/* 기본을 home.jsp(홈페이지)로 이동 */
	@RequestMapping("/")
	public String login(HttpServletRequest hsr, Model model) {
		return "home";
	}
	/* // 기본을 home.jsp(홈페이지)로 이동 */
	
	/* home을 입력하여 검색할 경우, home.jsp(홈페이지)로 이동 */
	@RequestMapping("/home")
	public String login3(HttpServletRequest hsr,Model model) {
		return "hoom";
	}
	/* // home을 입력하여 검색할 경우, home.jsp(홈페이지)로 이동 */
	
	/* login을 입력하여 검색할 경우, login.jsp(로그인페이지)로 이동. 사용하지 않는 코드입니다. */
	@RequestMapping("/login")
	public String login1(HttpServletRequest hsr, Model model) {
		return "login";
	}
	/* // login을 입력하여 검색할 경우, login.jsp(로그인페이지)로 이동. 사용하지 않는 코드입니다. */
	
	/* newbie를 입력하여 검색할 경우, newbie.jsp(회원가입페이지)로 이동. */
	@RequestMapping("/newbie")
	public String newbie(HttpServletRequest hsr, Model model) {
		return "newbie";
	}
	/* // newbie를 입력하여 검색할 경우, newbie.jsp(회원가입페이지)로 이동. */
	
	/* 회원가입 페이지에서 사용하게 될 HomeController입니다. */
	@RequestMapping(value="/signin",method = RequestMethod.POST,
			produces = "application/text; charset=utf8")
	public String newbie(HttpServletRequest hsr) {
		String txtname=hsr.getParameter("txtname");
		//System.out.println("name["+txtname+"]"); == txtname값이 잘 들어오는지 확인하는 디버그 코드 입니다.
		String loginid=hsr.getParameter("login");
		//System.out.println("loginid["+loginid+"]"); == loginid값이 잘 들어오는지 확인하는 디버그 코드 입니다.
		String passcode=hsr.getParameter("passcode");
		//System.out.println("passcode["+passcode+"]"); == passcode값이 잘 들어오는지 확인하는 디버그 코드 입니다.
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doSignin(txtname,loginid,passcode);
		return "/home";
	}
	/*//회원가입 페이지에서 사용하게 될 HomeController입니다. */
	
	/* 로그인시, 사용하게 될 HomeController입니다. */
	@RequestMapping("/viewinfo")
	public String show(@RequestParam String userid, @RequestParam String passcode, Model model) {
		model.addAttribute("login", userid);	
		model.addAttribute("newbie", passcode);
		return "viewinfo";
	}
	/* // 로그인시, 사용하게 될 HomeController입니다. */
	
	/* 회원가입시, 사용하게 될 HomeController입니다. */
	@RequestMapping("/newinfo")
	public String sss(@RequestParam String name,@RequestParam String id, @RequestParam String pass,
					@RequestParam String mobile, Model model) {
		model.addAttribute("n",name);
		model.addAttribute("i",id);
		model.addAttribute("p",pass);
		model.addAttribute("m",mobile);
		return "newinfo";
	}
	/* // 회원가입시, 사용하게 될 HomeController입니다. */
	
	/* home.jsp에서 사용하게 될 HomeController입니다. */
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
	/* //home.jsp에서 사용하게 될 HomeController입니다. */
	
	/* room과 roomtype을 JOIN시키는 controller */
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
			model.addAttribute("list_type",roomtype);
				return "/booking";
		}
	}
	/* // room과 roomtype을 JOIN시키는 controller */
	
	/* // 원래 사용하던 booking(예약) 코드입니다.
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
	
	/* room을 입력하여 검색할 경우, room.jsp(객실관리)로 이동. 로그인이 안 되어 있을 경우, 이동 불가. */
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
	/* //room을 입력하여 검색할 경우, room.jsp(객실관리)로 이동. 로그인이 안 되어 있을 경우, 이동 불가. */
	
	/* logout시, 사용 할 HomeController입니다. */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession();
		session.invalidate();
		return "redirect:/";
}
	/* //logout시, 사용 할 HomeController입니다. */
	
	/* JSON 사용, 조회버튼 반응 예약 가능 객실 컨트롤러 부분*/
	// produces="application/text; charset=utf8" == 한글깨짐 방지 코드 
	@RequestMapping(value="/getBookingList",method=RequestMethod.POST,
					produces="application/text; charset=utf8")
	@ResponseBody
	public String doFindAvailable(HttpServletRequest hsr) {
		iRoom room=sqlSession.getMapper(iRoom.class);
		String checkin=hsr.getParameter("checkin");
		//System.out.println(checkin); == checkin 값 확인용 디버깅 코드
		String checkout=hsr.getParameter("checkout");
		//System.out.println(checkout); == checkout 값 확인용 디버깅 코드
		ArrayList<Roominfo> roominfo=room.doFindAvailable(checkin,checkout);
		// 찾아진 데이터로 JSONArray만들기
		JSONArray ja = new JSONArray(); //JSONArray ja 생성
		for(int i=0; i<roominfo.size();i++) { //roominfo 크기만큼 반복
			//System.out.println(i); // 예약 가능 객실 횟수 디버그용 코드
			JSONObject jo = new JSONObject(); // JSONObject jo 생성
			jo.put("roomcode", roominfo.get(i).getRoomcode()); // JSONObject jo에 데이터 입력
			//System.out.println(roominfo.get(i).getRoomcode());
			jo.put("roomname", roominfo.get(i).getRoomname()); // JSONObject jo에 데이터 입력
			//System.out.println(roominfo.get(i).getName());
			jo.put("typename", roominfo.get(i).getTypename()); // JSONObject jo에 데이터 입력
			//System.out.println(roominfo.get(i).getType());
			jo.put("howmany", roominfo.get(i).getHowmany()); // JSONObject jo에 데이터 입력
			//System.out.println(roominfo.get(i).getHowmany());
			jo.put("howmuch", roominfo.get(i).getHowmuch()); // JSONObject jo에 데이터 입력
			//System.out.println(roominfo.get(i).getHowmuch());
			jo.put("checkin", roominfo.get(i).getCheckin()); // JSONObject jo에 데이터 입력
			//System.out.println(roominfo.get(i).getCheckin()+"getCheckin");
			jo.put("checkout", roominfo.get(i).getCheckout()); // JSONObject jo에 데이터 입력
			//System.out.println(roominfo.get(i).getCheckout()+"getCheckou");
			ja.add(jo); // JSONArray ja에 JSONObject jo에 있는 데이터 값 입력
		}
		return ja.toString(); // JSON ja의 데이터를 문자열로 바꿈 
	}
	/* //조회버튼 반응 예약 가능 객실 컨트롤러 부분 */
	
	/* 조회버튼 반응 예약된 객실 컨트롤러 부분 */
	@RequestMapping(value="/getBookList",method=RequestMethod.POST,
			produces="application/text; charset=utf8") // 한글 깨짐 방지용 코드
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
			//System.out.println(getBookList.get(i).getRoomname()+":Roomname");
			jos.put("bookcode", getBookList.get(i).getBookcode()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getBookcode()+":Bookcode");
			jos.put("type", getBookList.get(i).getRoomtype()); // JSONObject jo에 데이터 입력
			//System.out.println(getBookList.get(i).getRoomtype()+":getType");
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
		return jas.toString(); // JSON ja의 데이터를 문자열로 바꿈 
	}
	/* //조회버튼 반응 예약된 객실 컨트롤러 부분 */
	
	
	/* JSON 사용
	 * //객실관리 페이지에서 객실목록을 출력 컨트롤러 부분입니다.
	 */
	// produces="application/text; charset=utf8" == 한글깨짐 방지 코드입니다.
	@RequestMapping(value="/getRoomList",method=RequestMethod.POST,
					produces="application/text; charset=utf8")
	@ResponseBody
	public String getRoomList1(HttpServletRequest hsr) {
		iRoom room=sqlSession.getMapper(iRoom.class);
		ArrayList<Roominfo> roominfo=room.getRoomList();
		// 찾아진 데이터로 JSONArray만들기
		JSONArray ja = new JSONArray(); //JSONArray ja 생성합니다.
		for(int i=0; i<roominfo.size();i++) { //roominfo 크기만큼 반복합니다.
			JSONObject jo = new JSONObject(); // JSONObject jo 생성합니다.
			jo.put("roomcode", roominfo.get(i).getRoomcode()); // JSONObject jo에 데이터 입력합니다.
			jo.put("roomname", roominfo.get(i).getRoomname()); // JSONObject jo에 데이터 입력합니다.
			jo.put("typename", roominfo.get(i).getTypename()); // JSONObject jo에 데이터 입력합니다.
			jo.put("howmany", roominfo.get(i).getHowmany()); // JSONObject jo에 데이터 입력합니다.
			jo.put("howmuch", roominfo.get(i).getHowmuch()); // JSONObject jo에 데이터 입력합니다.
			ja.add(jo); // JSONArray ja에 JSONObject jo에 있는 데이터 값 입력
		}
		// System.out.println(ja.toString()); == 디버그용 코드 입니다. 
		return ja.toString(); // JSON ja의 데이터를 문자열로 바꿔 반환합니다.
	}
	/* //객실관리 페이지에서 객실목록을 출력 컨트롤러 부분입니다. */
	
	@RequestMapping(value="/deleteRoom",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String deleteRoom(HttpServletRequest hsr) {
		int roomcode=Integer.parseInt(hsr.getParameter("roomcode"));
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doDeleteRoom(roomcode);
		return "ok";
	}
	
	/* 예약이 완료된 객실의 데이터를 지우는 코드입니다. */
	@RequestMapping(value="/deleteBooking",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String deleteBooking(HttpServletRequest hsr) {
		int bookcode=Integer.parseInt(hsr.getParameter("bookcode"));
		// System.out.println(bookcode); == 데이터 값이 잘 들어왔나 확인하는 코드
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doDeleteBooking(bookcode);
		return "ok";
	}
	/* //예약이 완료된 객실의 데이터를 지우는 코드입니다 */
	
	
	/* 예약관리 페이지에서 객실을 추가 코드입니다. */
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
	/* //예약관리 페이지에서 객실을 추가 코드입니다. */
	
	@RequestMapping(value="/addbooking",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String addbooking(HttpServletRequest hsr) {
		String roomname=hsr.getParameter("roomname");
		//System.out.println(roomname+"객실이름"); == 값을 확인하는 디버깅 코드입니다.
		int roomcode=Integer.parseInt(hsr.getParameter("roomcode"));
		//System.out.println(roomcode+"객실타입"); == 값을 확인하는 디버깅 코드입니다.
		int txtNum=Integer.parseInt(hsr.getParameter("txtNum"));
		//System.out.println(txtNum+"예약인원"); == 값을 확인하는 디버깅 코드입니다.
		int human=Integer.parseInt(hsr.getParameter("human"));
		//System.out.println(human+"최대인원"); == 값을 확인하는 디버깅 코드입니다.
		String checkin=hsr.getParameter("checkin");
		//System.out.println(checkin+"체크인"); == 값을 확인하는 디버깅 코드입니다.
		String checkout=hsr.getParameter("checkout");
		//System.out.println(checkout+"체크아웃"); == 값을 확인하는 디버깅 코드입니다.
		int total=Integer.parseInt(hsr.getParameter("total"));
		//System.out.println(total+"가격");
		String bname=hsr.getParameter("txtName");
		//System.out.println(bname+"예약자명"); == 값을 확인하는 디버깅 코드입니다.
		String bmobile=hsr.getParameter("txtmobile");
		//System.out.println(bmobile+"모바일번호"); == 값을 확인하는 디버깅 코드입니다.
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doAddBooking(roomname,roomcode,txtNum,human,checkin,checkout,total,bname,bmobile);
		return "ok";
	}
	
	/* 객실관리 페이지에서 이미 등록된 객실 목록을 업데이트 하는 코드입니다. */
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
	/*//객실관리 페이지에서 이미 등록된 객실 목록을 업데이트 하는 코드입니다. */

	/* 예약된 객실의 데이터 값을 업로드 하기 위한 코드입니다. */
	@RequestMapping(value="/updatebooking",method=RequestMethod.POST,
			produces="application/text; charset=utf8")
	@ResponseBody
	public String updatebooking(HttpServletRequest hsr) {
		iRoom room=sqlSession.getMapper(iRoom.class);
		room.doUpdatebooking(Integer.parseInt(hsr.getParameter("bookcode")),
				Integer.parseInt(hsr.getParameter("human")),
				hsr.getParameter("txtNames"),
				hsr.getParameter("txtmobile"));
		return "ok";
	}
	/* //예약된 객실의 데이터 값을 업로드 하기 위한 코드입니다. */



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
