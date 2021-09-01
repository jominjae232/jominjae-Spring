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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/")
	public String home() {
		return "home"; //.jsp 생략
	}
	
	@RequestMapping("home")
	public String home1() {
		return "home"; //.jsp 생략
	}
	
	@RequestMapping("newbie")
	public String newbie() {
		return "newbie"; //.jsp 생략
	}
	
	@RequestMapping("/Login")
	public String Login() {
		return "Login"; //.jsp 생략
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest hsr) {
		HttpSession session=hsr.getSession();
		session.invalidate(); //세션값을 초기화
		return "redirect:/"; //.jsp 생략
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
	
	
	@RequestMapping(value="/check_user",method=RequestMethod.POST)
	public String check_user(HttpServletRequest hsr, Model model) {
		String userid=hsr.getParameter("userid");
		String passcode=hsr.getParameter("passcode");
		
		HttpSession session=hsr.getSession();
		session.setAttribute("loginid",userid);
		session.setAttribute("passcode",passcode);
		
		return "redirect:/booking"; //RequestMapping의 경로이름
	}
	
	
	/*
	@RequestMapping(value="/check_user",method=RequestMethod.GET)
	public String booking(HttpServletRequest hsr) {
		HttpSession session = hsr.getSession();
		String loginid=(String)session.getAttribute("userid");
		if(!loginid.equals("aud")) {
			return "booking"; //.jsp 생략
		} else {
			return "redirect:/"; //JSP화일이름
		}
	}
	*/
	
	@RequestMapping(value="/booking",method=RequestMethod.GET)
	public String booking1(HttpServletRequest hsr) {
		HttpSession session = hsr.getSession();
		String loginid=(String)session.getAttribute("loginid");
		if(loginid.equals("") || loginid==null) {
			return "redirect:/home"; //JSP 화일 이름
		} else {
			return "booking";
		}
	}
	
	@RequestMapping("/selected")
	public String doJob(HttpServletRequest hsr,Model model) {
	String strPath=hsr.getParameter("path");
		if(strPath.equals("Login")) {
			return "Login"; //.jsp 생략
		} else if(strPath.equals("newbie")) {
			return "newbie"; //.jsp 생략
		} else {
			return "redirect:home"; //redirect:요청경로명
		}
	}
	
	@RequestMapping("/newinfo")
	public String doInfo(HttpServletRequest hsr, Model model) {
		String una=hsr.getParameter("username");
		String uid=hsr.getParameter("userid");
		String pw=hsr.getParameter("passcode");
		String pw2=hsr.getParameter("passcode2");
		String mb=hsr.getParameter("mobile");
		
//		System.out.println("usename="+una);
//		System.out.println("userid="+uid);
//		System.out.println("passcode="+pw);
//		System.out.println("passcode2="+pw2);
//		System.out.println("mobile="+mb);
		
		model.addAttribute("username",una);
		model.addAttribute("userid",uid);
		model.addAttribute("passcode",pw);
		model.addAttribute("passcode2",pw2);
		model.addAttribute("mobile",mb);
		return "newinfo"; //.jsp 생략
	}
	
	@RequestMapping("/viewinfo")
	public String doInfo2(HttpServletRequest hsr2, Model model) {
		String uid=hsr2.getParameter("userid");
		String pw=hsr2.getParameter("passcode");
		
//		System.out.println("userid="+uid);
//		System.out.println("password="+pw);
		
		model.addAttribute("userid",uid);
		model.addAttribute("passcode",pw);
		return "viewinfo"; //.jsp 생략
	}
	
}
