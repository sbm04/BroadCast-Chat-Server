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

import com.sample1.Sample1Application;
import com.sample1.services.ReadPrivateChatService;

@Controller
public class ReadPrivateChat {
	private static Logger logger=Logger.getLogger(Sample1Application.class);
	{
		BasicConfigurator.configure();
	}
	@Autowired
	private ReadPrivateChatService service;
	@GetMapping("/ReadPrivatechat")
	public void get(HttpServletRequest request ,HttpServletResponse response) {
		response.setContentType("text/plain");
		HttpSession session=request.getSession(false);
		ArrayList<String> userdetails=(ArrayList<String>)session.getAttribute("userdetails");
		service.InsertMessage(userdetails.get(1),request.getParameter("message"),Integer.parseInt(request.getParameter("chatid")));
		logger.debug("message sent from private Chat");
	}
	@PostMapping("/ReadPrivatechat")
	public void post(HttpServletRequest request ,HttpServletResponse response) throws IOException {
		PrintWriter out=response.getWriter();
		String result=service.ReadMessage(Integer.parseInt(request.getParameter("chatid")), Integer.parseInt(request.getParameter("currentId")));
		out.write(result);
	}
}
