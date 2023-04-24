package com.sample1.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample1.Sample1Application;
import com.sample1.model.RegistrationModel;
import com.sample1.services.RegistrationService;

@Controller
public class RegisterServlet {
	private static Logger logger=Logger.getLogger(Sample1Application.class);
	{
		BasicConfigurator.configure();
	}
	@Autowired
	private RegistrationService registrationservice;
	@GetMapping("/")
	public String signuppage() {
		return "signup";
	}

	 @RequestMapping("/signup") 
	 public String redirect() { 
		 return "signup"; 
		 }
	 
	 @RequestMapping("/index")
	 public String login1() {
		 return "index";
	 }
	 @RequestMapping("/chatroom")
	 public String chat() {
		 return "chatroom";
	 }
	 @RequestMapping("/logout")
	 public String logout() {
		 logger.info("logged out...");
		 return "logout";
	 }
	@PostMapping("/register")
	public String register(HttpServletRequest request) {
		String password=request.getParameter("password");
		String passenc=Base64.getEncoder().encodeToString(password.getBytes());
		RegistrationModel registrationmodel=new RegistrationModel(request.getParameter("name"),passenc,request.getParameter("mobilenumber"));
		registrationservice.register(registrationmodel);
		logger.info("User Registered");
		return "index";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request) {
		HttpSession session=request.getSession();
		String password=request.getParameter("password");
		String passenc=Base64.getEncoder().encodeToString(password.getBytes());
		RegistrationModel model=new RegistrationModel();
		model.setMobilenumber(request.getParameter("mobilenumber"));
		model.setPassword(passenc);
		List<String> userdetails=registrationservice.validate(model);
		if(userdetails==null) {
			logger.info("Wrong Credentials");
			session.setAttribute("error", "Wrong Crendentials");
			return "index";
		}
		logger.info("User LoggedIn..");
		session.removeAttribute("error");
		session.setAttribute("userdetails", userdetails);
		return "redirect:/chatroom";
		
	}
	
}
