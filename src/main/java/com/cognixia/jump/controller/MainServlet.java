package com.cognixia.jump.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.model.Customer;
import com.cognixia.jump.service.Transactions;
import com.cognixia.jump.utility.DataGenerator;

/*
 * MainServlet will be how users can choice between Login and Create Users
 * This will be the initial servlet that gets called from index.jsp
 * Will route to either the login.jsp or createUser.jsp
 */
public class MainServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	HashMap<String, Customer> listOfUsers;
	HashMap<String, ArrayList<String>> history;
	
	public void init() throws ServletException{
		System.out.println("hello");
		listOfUsers = DataGenerator.testingDataList();
		history = DataGenerator.testingHistoryList();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse responce) 
			throws ServletException, IOException{
//		// Start by appending the user list to the request
//		request.getSession().setAttribute("users", listOfUsers);
//		// Also need the list of transactions
//		request.getSession().setAttribute("history", history);
		// will be the main object that will be called to perform all transactions
		Transactions transactions = new Transactions(listOfUsers, history);
		request.getSession().setAttribute("transactions", transactions);
		
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
