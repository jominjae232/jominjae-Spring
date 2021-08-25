package com.human.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {
	
 @RequestMapping(value="/contactus",method=RequestMethod.GET)
 public String method1(Model model) { //Model class 이용
model.addAttribute("myCompany","저희 회사 연락처는 ....입니다.");
	 return "contactus";
 }
 @RequestMapping("/contactus")
 public ModelAndView method2() {
	 ModelAndView mav = new ModelAndView();
	 mav.addObject("model2","041-561-1122");
	 return mav;
 }
 
}
