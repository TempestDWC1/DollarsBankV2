package com.cognixia.jump.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * AccountServlet is were you get routed after logging in
 * This will be where all transactions will be handled by a user
 */
public class AccountServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	// will be used by LoginServlet to load account.jsp
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// request the account.jsp
		RequestDispatcher rd = request.getRequestDispatcher("account.jsp");
		rd.forward(request, response);
	}
	
	/*
	 *  account will have multiple buttons for the user to press so just like in the
	 *  MainServlet we will check the value of to see which button was pressed
	 *  If its not the log out button the servlet will create a prompt window and
	 *  hand off the transactions to the transaction service
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{

		// check the value of the submit button pressed, if one is null then it wasn't pressed
		if(request.getParameter("Deposit") != null) {
			// move to DepositServlet
			DepositServlet ds = new DepositServlet();
			ds.doGet(request, response);
		}else if(request.getParameter("Withdraw") != null) {
			// move to WithdrawServlet
			WithdrawServlet ws = new WithdrawServlet();
			ws.doGet(request, response);
		}else if(request.getParameter("Transfer") != null) {
			TransferServlet ts = new TransferServlet();
			ts.doGet(request, response);
		}else if(request.getParameter("History") != null) {
			HistoryServlet hs = new HistoryServlet();
			hs.doGet(request, response);
		}else if(request.getParameter("SignOut") != null) {
			// will need to use a new LoginSevlet to to use the doGet
			// otherwise LoginServlet doPost will automatically be called because this
			// request is from a doPost
			System.out.println("Signout");
			LoginServlet loginServlet = new LoginServlet();
			// request LoginServlet
			loginServlet.doGet(request, response);
		}
	}
}
