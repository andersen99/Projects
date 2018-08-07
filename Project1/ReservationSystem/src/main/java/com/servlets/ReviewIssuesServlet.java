package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.core.Issue;
import com.dao.IssueDao;

public class ReviewIssuesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = (String)req.getParameter("action");
		if(action == null) {
			PrintWriter pw = resp.getWriter();
			IssueDao i = new IssueDao();
			ArrayList<Issue> issues = i.getAllIssues();
			for(Issue iss : issues) {
				pw.println("Issue No " + iss.getIssueId() + " raised by " + iss.getGuestId());
				pw.println("Problem: " + iss.getContent());
				if(iss.getResolved() == 0)pw.println("Issue not yet resolved");
				else pw.println("Issue has been resolved");
				pw.println("----------------------------");
			}
		}
		else if("Reply".equals(action)) {
			String reply = (String)req.getParameter("replyContent");
			int issId = Integer.parseInt((String)req.getParameter("issueId"));
			IssueDao iss = new IssueDao();
			iss.replyIssue(reply,1);
			PrintWriter pw = resp.getWriter();
			pw.println("Issue No: " + issId + " resolved");
		}
		else if("Back to Main".equals(action))resp.sendRedirect("Hotel Reservations/Admin Main/Host.html");
	}

}
