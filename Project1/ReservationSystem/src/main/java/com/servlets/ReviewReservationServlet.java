package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.core.Reservation;
import com.dao.ReservationDao;
import com.dao.RoomDao;

public class ReviewReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = (String)req.getParameter("action");
		PrintWriter pw = resp.getWriter();
		if(action == null) {
			ReservationDao res = new ReservationDao();
			ArrayList<Reservation> reservations = res.getAllReservations();
			for(Reservation r : reservations) {
				pw.println("Reservation No: " + r.getReserveId());
				pw.println(r.getGuestId() + " wants to reserve Room " + r.getRoomId());
				if(r.getStatus() == 0)pw.println("Reservation awaiting Host approval or denail");
				else if (r.getStatus() == 1)pw.println("Reservation has been approved");
				else pw.println("Reservation has been denied");
				pw.println("-----------------------------------");
			}
		}
		else if("Approve".equals(action)) {
			ReservationDao r = new ReservationDao();
			int reserveId = Integer.parseInt((String)req.getParameter("reserveNo"));
			r.updateReservation(1, reserveId);
			Reservation res = r.getReservation(reserveId);
			RoomDao room = new RoomDao();
			room.updateRoom(res.getGuestId(),res.getRoomId());
			pw.println("Reservation No: " + reserveId + " has been approved");
		}
		else if("Deny".equals(action)) {
			ReservationDao r = new ReservationDao();
			int reserveId = Integer.parseInt((String)req.getParameter("reserveNo"));
			r.updateReservation(-1,reserveId);
			pw.println("Reservation No: " + reserveId + " has been denied");
		}
		else if("Back to Main".equals(action))resp.sendRedirect("Hotel Reservations/Admin Main/Host.html");
	}

}
