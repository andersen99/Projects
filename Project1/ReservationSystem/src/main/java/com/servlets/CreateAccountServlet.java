package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.GuestDao;
import com.core.Guest;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String fName = req.getParameter("fname");
		String lName = req.getParameter("lname");
		PrintWriter pw = resp.getWriter();
		boolean isDuplicate = false;
		if(action == null) {
			pw.println("Failed to create account");
		}
		else if("Create Account".equals(action)) {
			GuestDao g = new GuestDao();
			ArrayList<Guest> guests= g.getAllGuests();
			for(Guest guest : guests) {
				if(userName.equals(guest.getGuestId())) isDuplicate = true;
			}
			if(isDuplicate) pw.println("That user name is in use");
			else {
				g.insertGuest(userName,password,fName,lName);
			}
		}
	}

}
