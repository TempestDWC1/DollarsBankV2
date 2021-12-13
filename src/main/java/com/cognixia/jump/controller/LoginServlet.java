package com.cognixia.jump.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.model.Customer;

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
		rd.include(request, response);
	}
	
	// will be used by login.jsp to send users with correct credentials to account.jsp
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// request the login.jsp
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.include(request, response);
		
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
		// next see if the user exists and check password
		} else if(request.getSession(false) != null){
			// get the list of users
			@SuppressWarnings("unchecked")
			HashMap<String, Customer> listOfUsers = (HashMap<String, Customer>) request.getSession().getAttribute("users");
			// check if username is correct and then check if password is correct
			if(listOfUsers.containsKey(username) && 
					listOfUsers.get(username).getPassword().equals(password)) {
				
				System.out.println("Logged In");	
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
