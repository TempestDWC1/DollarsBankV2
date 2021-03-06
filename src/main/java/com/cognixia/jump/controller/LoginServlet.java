package com.cognixia.jump.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.service.Transactions;

/*
 * LoginServlet it will load the login.jsp
 */
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	// Will be used by index.jsp to send user to login.jsp
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// make sure error is set to blank
		request.getSession().setAttribute("error", "");
		// request the login.jsp
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
	
	// will be used by login.jsp to send users with correct credentials to account.jsp
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// make sure error is set to blank
		request.getSession().setAttribute("error", "");
				
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		
		// make sure they entered something for both fields
		if(username.isBlank() || password.isBlank()) {
			request.getSession().setAttribute("error", "Please input both Username and Password");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		// make sure the session is still alive otherwise don't do anything with the data
		} else if(request.getSession(false) != null){
			// transactions holds the list of users
			Transactions transactions = (Transactions)request.getSession().getAttribute("transactions");
			// check the permissions
			if(transactions.checkPermissions(username, password)) {
//			// next see if the user exists and check password
//			// get the list of users
//			@SuppressWarnings("unchecked")
//			HashMap<String, Customer> listOfUsers = (HashMap<String, Customer>) request.getSession().getAttribute("users");
//			// check if username is correct and then check if password is correct
//			if(listOfUsers.containsKey(username) && 
//					listOfUsers.get(username).getPassword().equals(password)) {
//				// store the active user for AccountServlet
//				Customer user = listOfUsers.get(username);
//				request.getSession().setAttribute("user", user);
				// will need to use a new AccountServlet to to use the doGet
				// otherwise AccountServlet doPost will automatically be called because this
				// request is from a doPost
				AccountServlet as = new AccountServlet();
				as.doGet(request, response);
			}else {
				// throw an error if credentials were wrong
				request.getSession().setAttribute("error", "Incorrect username or password");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			
		}else {
			System.out.println("OH no");
		}
		
	}


}
