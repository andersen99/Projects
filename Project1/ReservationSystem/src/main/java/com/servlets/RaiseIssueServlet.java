package com.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.core.Issue;
import com.dao.IssueDao;

public class RaiseIssueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = (String)req.getParameter("action");
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		if(action == null) {
			IssueDao i = new IssueDao();
			String guestId = (String)session.getAttribute("guestName");
			ArrayList<Issue> issues = i.getGuestIssues(guestId);
			if(issues == null) {
				pw.println("No issues have been raised yet");
			}
			else {
				for(Issue iss : issues) {
					pw.println("Issue for " + iss.getGuestId() + " in room " + iss.getRoomId());
					pw.println("------------------------------");
					if(iss.getReply() == null) pw.println("Host has not replied yet");
					else pw.println("Host has replied: " + iss.getReply());
					pw.println("------------------------------");
				}
			}
			pw.println("Thank you for your patience");
		}
		else if("Send Issue".equals(action)) {
			IssueDao i = new IssueDao();
			String guestId = (String)session.getAttribute("guestName");
			int roomNum = Integer.parseInt((String)req.getParameter("roomNo"));
			String content = (String)req.getParameter("issContent");
			i.insertIssue(guestId, roomNum, content);
			pw.println("Issue sent to Host");
		}
	}
}