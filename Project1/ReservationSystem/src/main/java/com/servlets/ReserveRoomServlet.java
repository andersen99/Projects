package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.core.Reservation;
import com.core.Room;
import com.dao.RoomDao;
import com.dao.ReservationDao;
public class ReserveRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = (String)req.getParameter("action");	
		PrintWriter pw = resp.getWriter();
		if(action == null) {
			RoomDao r = new RoomDao();
			ArrayList<Room> rooms = r.getAvailableRooms();
			for(Room rm : rooms) {
				pw.println("Room " + rm.getRoomId());
			}
		}
		else if("Make Reservation".equals(action)) {
			int roomNum = Integer.parseInt((String)req.getParameter("roomNo"));
			HttpSession session = req.getSession();
			String guestId = (String)session.getAttribute("guestName");
			ReservationDao r = new ReservationDao();
			r.insertReservation(roomNum,guestId);
			
			pw.println("Reservation sent to Host");
		}
		else if("Show Reservation Status".equals(action)) {
			ReservationDao r = new ReservationDao();
			HttpSession session = req.getSession();
			String guestId = (String)session.getAttribute("guestName");
			ArrayList<Reservation> reservations = r.getGuestReservations(guestId);
			for(Reservation res : reservations) {
				pw.println("Reservation No: " + res.getReserveId() + " for room " + res.getRoomId());
				if(res.getStatus() == 0)pw.println("Reservation is pending");
				else if(res.getStatus() == 1)pw.println("Reservation has been approved");
				else pw.println("Reservation has been denied");
				pw.println("----------------------------");
			}
		}
	}
}
