package com.human.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private HttpSession session;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home1(Locale locale, Model m) {
		m.addAttribute("m_name","Jenny,Jisoo");
		return "members";
	}
	@RequestMapping(value = "/keeptouch", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("myName","Jo Min Jae");
		model.addAttribute("myAddress","ASAN");
		model.addAttribute("myMobile","010-1111-2222");
		return "home";
	}
	
	@RequestMapping("/getinfo")
	public String getInfo() {
		return "getinfo";
	}
	
	// ../info?userid=xaexal&address=Cheonan
	// ../info/xaexal/Cheonan
	@RequestMapping("/info")
//	public String doInfo(HttpServletRequest hsr, Model model) {
//		String uid=hsr.getParameter("userid");
//		String addr=hsr.getParameter("address");
//		String n=hsr.getParameter("income");
//		int salary=Integer.parseInt(n);
	public String doInfo(@RequestParam("userid") String uid,
						 @RequestParam("address") String addr,
						 @RequestParam("income") int salary,
						 Model model) {
	System.out.println("uid="+uid);
	System.out.println("addr="+addr);
	model.addAttribute("loginid",uid);
	model.addAttribute("region",addr);
	
	// userid -> uid -> loginid, address->addr->region
			return "viewinfo";
	}
	
	//Command??????
	public String doInfo(@ModelAttribute("pl") ParamList pl,Model model) {
		System.out.println("uid="+pl.userid);
		System.out.println("addr="+pl.address);
//		model.addAttribute("loginid",pl.userid);
//		model.addAttribute("region",pl.address);
		// userid -> uid -> loginid, address->addr->region
		return "viewInfo";
	}
		
	@RequestMapping("/choose")
	public String doChoose() {
		return "choose";
	}
	
	@RequestMapping("/selected")
	public String doJob(HttpServletRequest hsr,Model model) {
//	public String doJob(@RequestParam("Login") String Login,@RequestParam("newbie")String newbie,strPath strPath,Model model) {
	String strPath=hsr.getParameter("path");
		if(strPath.equals("Login")) {
			return "getinfo";
		} else if(strPath.equals("newbie")) {
			return "newbie";
		} else {
			return "redirect:choose"; //redirect:???????????????
		}
	}
	
	@RequestMapping("/today/{address}/{userid}")
	public String showNumber(@PathVariable String address,@PathVariable String userid,Model model) {
		model.addAttribute("addr",address);
		model.addAttribute("uid",userid);
		if(userid.equals("xaexal")) {
			return "today";
		} else {
			return "redirect:/choose";
		}
	}
	
	
	
	
}
