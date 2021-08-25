package com.human.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ncs")
public class NcsController {

		@RequestMapping("/student")
		public String viewStudent() {
			return "viewStudent";
		}
		
		@RequestMapping("/teacher")
		public String viewTeacher() {
			return "viewTeacher";
		}
		
		@RequestMapping("/class")
		public String viewClass() {
			return "viewClass";
		}
}
