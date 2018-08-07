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
import com.core.Reservation;
import com.core.Room;
import com.core.Issue;
import com.dao.GuestDao;
import com.dao.ReservationDao;
import com.dao.RoomDao;
import com.dao.IssueDao;

public class GuestController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{	
		HttpSession session = req.getSession();
		String action = (String)req.getParameter("action");
		String currentId =(String)session.getAttribute("guestName");
		String newData = req.getParameter("info");
		GuestDao g = new GuestDao();
		PrintWriter pw = resp.getWriter();
		if(action == null) {
			Guest guest = g.getGuest((String)session.getAttribute("guestName"));
			String name = guest.getName();
			String guestId = guest.getGuestId();
			String password = guest.getGuestPword();
			pw.println("Name: " + name);
			pw.println("Username: " + guestId);
			pw.println("Password: " + password);
			pw.close();
		}
		else if("Update Username".equals(action)) {
			g.updateGuest(newData, currentId, "userId");
			session.setAttribute("guestName",req.getParameter("info"));
			pw.println("Username updated");
			pw.println("Please refresh browser");
		}
		else if("Update Password".equals(action)) {
			g.updateGuest(newData, currentId, "userPword");
			pw.println("Password updated");
			pw.println("Please refresh browser");
		}
		else if("Update First Name".equals(action)) {
			g.updateGuest(newData, currentId, "fName");
			pw.println("First Name updated");
			pw.println("Please refresh browser");
		}
		else if("Update Last Name".equals(action)) {
			g.updateGuest(newData, currentId, "lName");
			pw.println("Last Name updated");
			pw.println("Please refresh browser");
		}
		else if("Logout".equals(action)) {
			session.invalidate();
			resp.sendRedirect("Hotel Reservations/Login Page/Login.html");
		}	
	}
}