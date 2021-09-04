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
		System.out.println(userid);
		System.out.println(passcode);
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
	
	@RequestMapping(value="booking",method = RequestMethod.GET)
	public String booking(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession(true);
		String loginid=(String)session.getAttribute("loginid");	
			return "booking";
	}
	
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
