package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.core.Guest;
import com.core.Host;
import com.dao.GuestDao;
import com.dao.HostDao;

public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		String action = req.getParameter("action");
		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		boolean isValid = false;
		if("Guest Login".equals(action)) {
			GuestDao guestDao = new GuestDao();
			ArrayList<Guest> guests = guestDao.getAllGuests();
			for(Guest g : guests) {
				if(userName.equals(g.getGuestId()) && password.equals(g.getGuestPword())) {
					resp.sendRedirect("Hotel Reservations/User Main/Guest.html");
					session.setAttribute("guestName",userName);
					isValid = true;
				}
			}
			if(!isValid) {
				PrintWriter pw = resp.getWriter();
				pw.println("Invalid username or password");
			}
		}
		else if("Host Login".equals(action)) {
			HostDao hostDao = new HostDao();
			ArrayList<Host> hosts = hostDao.getAllHosts();
			for(Host h : hosts) {
				if(userName.equals(h.getHostId()) && password.equals(h.getHostPword())) {
					resp.sendRedirect("Hotel Reservations/Admin Main/Host.html");
					isValid = true;
					session.setAttribute("hostName",userName);
				}
			}
			if(!isValid) {
				PrintWriter pw = resp.getWriter();
				pw.println("Invalid admin credentials");
			}
		}
	}
}