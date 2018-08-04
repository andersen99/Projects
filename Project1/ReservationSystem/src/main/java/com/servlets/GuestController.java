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
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{	
		HttpSession session = req.getSession();
		String action = (String)req.getParameter("action");
		String currentId =(String)session.getAttribute("guestName");
		String newData = req.getParameter("info");
		String button = req.getParameter("checkRooms");
		GuestDao g = new GuestDao();
		if(action != null)System.out.println(action);
		if(action == null) {
			displayInfo(req,resp,session,g);
		}
		else if("Update Username".equals(action)) {
			g.updateGuest(newData, currentId, "userId");
			session.setAttribute("guestName",req.getParameter("info"));
		}
		else if("Update Password".equals(action)) {
			g.updateGuest(newData, currentId, "userPword");
		}
		else if("Update First Name".equals(action)) {
			g.updateGuest(newData, currentId, "fName");
		}
		else if("Update Last Name".equals(action)) {
			g.updateGuest(newData, currentId, "lName");
		}
		// Work in Progress, will likely move to another page to check rooms
		else if("Check Availability".equals(button)) {
			RoomDao r = new RoomDao();
			ArrayList<Room> rooms = r.getAvailableRooms();
			PrintWriter pw = resp.getWriter();
			for(Room rm : rooms) {
				pw.println("Room " + rm.getRoomId());
			}
		}
		else if("Logout".equals(action)) resp.sendRedirect("Hotel Reservations/Login Page/Login.html");
		
	}
	protected void displayInfo(HttpServletRequest req, HttpServletResponse resp,HttpSession session, GuestDao g) throws ServletException,IOException{
		Guest guest = g.getGuest((String)session.getAttribute("guestName"));
		String name = guest.getName();
		String guestId = guest.getGuestId();
		String password = guest.getGuestPword();
		PrintWriter pw = resp.getWriter();
		pw.println("Name: " + name);
		pw.println("Username: " + guestId);
		pw.println("Password: " + password);
		pw.close();
	}
}