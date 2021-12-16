package com.cognixia.jump.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * HistoryServlet will show the last 5 transactions
 * Most of the critical code is embedded into the history.jsp
 * The servlet just loads and redirects back to account
 */
public class HistoryServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	// will be used by HistoryServlet to load history.jsp
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// request the withdraw.jsp
		RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
		rd.forward(request, response);
	}
	
	// for HistoryServlet the only thing is for it to send it back to AccountServlet
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		// return to AccountServlet
		AccountServlet as = new AccountServlet();
		as.doGet(request, response);
	}
}
