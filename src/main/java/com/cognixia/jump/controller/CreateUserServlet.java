package com.cognixia.jump.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.model.Customer;
import com.cognixia.jump.utility.Validate;

/*
 * CreateUserServlet it will load the createUser.jsp
 */
public class CreateUserServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	// Will be used by index to send user to the createUser.jsp
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		// request the createUser.jsp
		RequestDispatcher rd = request.getRequestDispatcher("createUser.jsp");
		rd.include(request, response);
	}
	
	// will be used my createUser.jsp and will redirect to login.jsp afterwards
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// Gather the data
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String balance = request.getParameter("balance");
		
		// check all inputs and compile it into a hashmap
		HashMap<String, String> errors = Validate.check(name, username, password, balance);

		// If errors does have a specific key that shows there is an overall error then display the errors
		if(errors.containsKey("error")) {
			request.getSession().setAttribute("errors", errors);
			request.getRequestDispatcher("createUser.jsp").forward(request, response);
		
		// make sure there is a session, getSession(false) returns null if there is no session
		} else if(request.getSession(false) != null){
			// get the list of users
			@SuppressWarnings("unchecked") // even though we check for a session it still goes "unchecked"
			HashMap<String, Customer> listOfUsers = (HashMap<String, Customer>) request.getSession().getAttribute("users");
			// make a new Customer object
			Customer user = new Customer(name, username, password, Float.parseFloat(balance));
			// Add the new user
			listOfUsers.put(username, user);
			// replace the old list
			request.getSession().setAttribute("users", listOfUsers);

			// will need to use a new LoginSevlet to to use the doGet
			// otherwise LoginServlet doPost will automatically be called because this
			// request is from a doPost
			LoginServlet loginServlet = new LoginServlet();
			// request LoginServlet
			loginServlet.doGet(request, response);
			
		}else {
			System.out.println("OH no");
		}
		
	}


}