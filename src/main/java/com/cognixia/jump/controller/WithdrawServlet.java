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
 * WithdrawServlet will handle the withdraw action for account
 */
public class WithdrawServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	// will be used by WithdrawServlet to load withdraw.jsp
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// request the withdraw.jsp
		RequestDispatcher rd = request.getRequestDispatcher("withdraw.jsp");
		rd.forward(request, response);
	}
	
	// will handle the withdraw functionality
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		// make sure error is set to blank
		request.getSession().setAttribute("error", "");
		
		// get the amount in string form first
		String input = request.getParameter("withdraw");
		
		// if they accidentally left it blank
		if(input.isBlank()) {
			// reload the page with an error message
			request.getSession().setAttribute("error", "Please input the withdraw amount");
			request.getRequestDispatcher("withdraw.jsp").forward(request, response);
			
		// make sure the session is still alive
		} else if(request.getSession(false) != null){
			// we need the deposit in float format so first check if it can
			if(Validate.checkInput(input)) {
				// get the deposit from the input
				Float withdrawal = Float.parseFloat(input);
				// transactions had withdrawal feature
				Transactions transactions = (Transactions)request.getSession().getAttribute("transactions");
				transactions.withdraw(withdrawal);
//				// get the user
//				Customer user = (Customer)request.getSession().getAttribute("user");
//				// remove the withdraw
//				user.getAccount().withdraw(withdraw);
//				// log the withdraw
//				@SuppressWarnings("unchecked")
//				HashMap<String, ArrayList<String>> historyList = (HashMap<String, ArrayList<String>>)request.getSession().getAttribute("history");
//				ArrayList<String> userHistory = historyList.get(user.getUsername());
//				userHistory.add("Withdraw: " + withdraw);
				// return to AccountServlet
				AccountServlet as = new AccountServlet();
				as.doGet(request, response);
			}else {
				// reload the page with an error message
				request.getSession().setAttribute("error", "input should be in 00.00 format, and not negative");
				request.getRequestDispatcher("withdraw.jsp").forward(request, response);
			}
		}
	}
}