package com.human.app;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping("/home")
	public String doChoose() {
		return "home";
	}
	
	@RequestMapping("/Login")
	public String doChoose1() {
		return "Login";
	}
	
	@RequestMapping("/room")
	public String doChoose2() {
		return "room";
	}
	
	@RequestMapping("/booking")
	public String doChoose3() {
		return "booking";
	}
	
	@RequestMapping("/selected")
	public String doJob(HttpServletRequest hsr,Model model) {
	String strPath=hsr.getParameter("path");
		if(strPath.equals("Login")) {
			return "Login";
		} else if(strPath.equals("newbie")) {
			return "newbie";
		} else {
			return "redirect:choose"; //redirect:요청경로명
		}
	}
	
	@RequestMapping("/newinfo")
	public String doInfo(HttpServletRequest hsr, Model model) {
		String una=hsr.getParameter("username");
		String uid=hsr.getParameter("userid");
		String pw=hsr.getParameter("password1");
		String pw2=hsr.getParameter("password2");
		String mb=hsr.getParameter("mobile");
		
//		System.out.println("usename="+una);
//		System.out.println("userid="+uid);
//		System.out.println("password1="+pw);
//		System.out.println("password2="+pw2);
//		System.out.println("mobile="+mb);
		
		model.addAttribute("username",una);
		model.addAttribute("userid",uid);
		model.addAttribute("password1",pw);
		model.addAttribute("password2",pw2);
		model.addAttribute("mobile",mb);
		return "newinfo";
	}
	
	@RequestMapping("/viewinfo")
	public String doInfo2(HttpServletRequest hsr2, Model model) {
		String uid=hsr2.getParameter("userid");
		String pw=hsr2.getParameter("password1");
		
//		System.out.println("userid="+uid);
//		System.out.println("password="+pw);
		
		model.addAttribute("userid",uid);
		model.addAttribute("password1",pw);
		return "viewinfo";
	}
	
}
