package com.cognixia.jump.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.service.Transactions;
import com.cognixia.jump.utility.Validate;

/*
 * DepositServlet will handle the deposit action for account
 */
public class DepositServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	// will be used by DepositServlet to load deposit.jsp
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// request the deposit.jsp
		RequestDispatcher rd = request.getRequestDispatcher("deposit.jsp");
		rd.forward(request, response);
	}
	
	// will handle the deposit functionality
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		// make sure error is set to blank
		request.getSession().setAttribute("error", "");
		
		// get the amount in string form first
		String input = request.getParameter("deposit");
		
		// if they accidentally left it blank
		if(input.isBlank()) {
			// reload the page with an error message
			request.getSession().setAttribute("error", "Please input the deposit amount");
			request.getRequestDispatcher("deposit.jsp").forward(request, response);
			
		// make sure the session is still alive
		} else if(request.getSession(false) != null){
			// we need the deposit in float format so first check if it can
			if(Validate.checkInput(input)) {
				// get the deposit from the input
				Float deposit = Float.parseFloat(input);
				// transactions had deposit feature
				Transactions transactions = (Transactions)request.getSession().getAttribute("transactions");
				transactions.deposit(deposit);
//				// get the user
//				Customer user = (Customer)request.getSession().getAttribute("user");
//				// add the deposit
//				user.getAccount().deposit(deposit);
//				// log it in history
//				@SuppressWarnings("unchecked")
//				HashMap<String, ArrayList<String>> historyList = (HashMap<String, ArrayList<String>>)request.getSession().getAttribute("history");
//				ArrayList<String> userHistory = historyList.get(user.getUsername());
//				userHistory.add("Deposit: " + deposit);
				// return to AccountServlet
				AccountServlet as = new AccountServlet();
				as.doGet(request, response);
			}else {
				// reload the page with an error message
				request.getSession().setAttribute("error", "input should be in 00.00 format, and not negative");
				request.getRequestDispatcher("deposit.jsp").forward(request, response);
			}
		}
	}
}
