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
 * TransferServlet will handle the transfer action for account
 */
public class TransferServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	// will be used by TransferServlet to load transfer.jsp
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// request the withdraw.jsp
		RequestDispatcher rd = request.getRequestDispatcher("transfer.jsp");
		rd.forward(request, response);
	}
	
	// will handle the transfer functionality
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		// make sure error is set to blank
		request.getSession().setAttribute("error", "");
		
		// get the amount in string form first and the username of the receiver
		String amount = request.getParameter("transfer");
		String username = request.getParameter("username");
		
		// transactions will haves the stored data
		Transactions transactions = (Transactions)request.getSession().getAttribute("transactions");
		
//		// get the list of users
//		@SuppressWarnings("unchecked")
//		HashMap<String, Customer> listOfUsers = (HashMap<String, Customer>)request.getSession().getAttribute("users");
		
		// if they accidentally left it blank
		if(amount.isBlank() || username.isBlank()) {
			// reload the page with an error message
			request.getSession().setAttribute("error", "Please input the transfer amount and username of the receiver");
			request.getRequestDispatcher("transfer.jsp").forward(request, response);
			
		// check the list to see if there is NOT a user with that username
		} else if(!transactions.getAllUsers().containsKey(username)) {
//		} else if(!listOfUsers.containsKey(username)) {
			// username doesn't exist so let the user know
			request.getSession().setAttribute("error", "Username does not exist, please try again");
			request.getRequestDispatcher("transfer.jsp").forward(request, response);
			
		// make sure the session is still alive
		} else if(request.getSession(false) != null){
			// we need the deposit in float format so first check if it can
			if(Validate.checkInput(amount)) {
				// get the deposit from the input
				Float transfer= Float.parseFloat(amount);
				// perform the transfer
				transactions.transfer(transfer, username);
//				// get the user
//				Customer user = (Customer)request.getSession().getAttribute("user");
//				// get the receiver
//				Customer receiver = listOfUsers.get(username);
//				// remove the transfer
//				user.getAccount().withdraw(transfer);
//				// give the amount to the receiver
//				receiver.getAccount().deposit(transfer);
//				// log the transfer
//				@SuppressWarnings("unchecked")
//				HashMap<String, ArrayList<String>> historyList = (HashMap<String, ArrayList<String>>)request.getSession().getAttribute("history");
//				// will need both userhistory and receiverhistory so they both can show the transactions
//				ArrayList<String> userHistory = historyList.get(user.getUsername());
//				ArrayList<String> receiverHistory = historyList.get(receiver.getUsername());
//				userHistory.add("Transfer amount: " + transfer + ", Transfer receiver: " + receiver.getUsername());
//				receiverHistory.add("Transfer amount: " + transfer + ", Transfer sender: " + user.getUsername());
				// return to AccountServlet
				AccountServlet as = new AccountServlet();
				as.doGet(request, response);
			}else {
				// reload the page with an error message
				request.getSession().setAttribute("error", "input should be in 00.00 format, and not negative");
				request.getRequestDispatcher("transfer.jsp").forward(request, response);
			}
		}
	}
}