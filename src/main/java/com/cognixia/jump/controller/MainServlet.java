package com.cognixia.jump.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.model.Customer;
import com.cognixia.jump.utility.DataGenerator;

/*
 * MainServlet will be how users can choice between Login and Create Users
 * This will be the initial servlet that gets called from index.jsp
 * Will route to either the login.jsp or createUser.jsp
 */
public class MainServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	HashMap<String, Customer> listOfUsers;
	
	public void init() throws ServletException{
		System.out.println("hello");
		listOfUsers = DataGenerator.testingDataList();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse responce) 
			throws ServletException, IOException{
		// Start by appending the user list to the request
		request.getSession().setAttribute("users", listOfUsers);
		
		// check the value of the submit button pressed, if one is null then it wasn't pressed
		if(request.getParameter("login") != null) {
			// request the LoginServlet
			RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
			rd.include(request, responce);
			
		}else if(request.getParameter("createUser") != null) {
			// request the CreateUserServlet
			RequestDispatcher rd = request.getRequestDispatcher("CreateUserServlet");
			rd.include(request, responce);
		}
	}
	
	// Since the form is a POST we must redirect to doGet
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}


}
