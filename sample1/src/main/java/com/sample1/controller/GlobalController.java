package com.sample1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample1.Sample1Application;
import com.sample1.services.GlobalService;

@Controller
public class GlobalController {
	private static Logger logger=Logger.getLogger(Sample1Application.class);
	{
		BasicConfigurator.configure();
	}
	@Autowired
	private GlobalService globalservice;
	
	@PostMapping("/GlobalController")
	public void chatroom(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int maxid = globalservice.GetMaxid();
		int currentId = Integer.parseInt(request.getParameter("currentId"));
		PrintWriter out = response.getWriter();
		String result = "";
		if (maxid > currentId) {
			result = globalservice.GetnewMessage(currentId, maxid);
		}
		out.write(result);
	}
	
	@GetMapping("/GlobalController")
	public void chat(HttpServletRequest request,HttpServletResponse response) {
		response.setContentType("text/plain");
		HttpSession session=request.getSession(false);
		ArrayList<String> userdetails=(ArrayList<String>)session.getAttribute("userdetails");
		String messagee=request.getParameter("message");
		globalservice.InsertMessage(userdetails.get(0),messagee,userdetails.get(1));
		logger.info("message sent");
	}
	
	@RequestMapping("/privatechat")
	public String privateChat() {
		logger.info("Redirecting to Private Chat");
		return "privatechat";
	}
}
