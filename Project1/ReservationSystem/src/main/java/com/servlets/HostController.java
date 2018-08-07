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
import com.core.Host;
import com.core.Reservation;
import com.core.Room;
import com.core.Issue;
import com.core.Guest;
import com.dao.HostDao;
import com.dao.ReservationDao;
import com.dao.RoomDao;
import com.dao.IssueDao;
import com.dao.GuestDao;

public class HostController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();
		session.invalidate();
		resp.sendRedirect("Hotel Reservations/Login Page/Login.html");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		
		GuestDao g = new GuestDao();
		RoomDao r = new RoomDao();
		ArrayList<Guest> guests = g.getAllGuests();
		ArrayList<Room> rooms = r.getAllRooms();
		PrintWriter pw = resp.getWriter();
		pw.println("Guest Listing");
		for(Guest guest : guests) {
			pw.println(guest.getName());
		}
		pw.println("\nRoom Listing");
		for(Room room : rooms) {
			if(room.getGuestId() == null) {
				pw.println("Room " + room.getRoomId() + " No guests staying");
			}
			else {
				pw.println("Room " + room.getRoomId() + " " + room.getGuestId() + " is staying");
			}
		}
	
		pw.close();
	}
}