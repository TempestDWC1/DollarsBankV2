package com.cognixia.jump.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.cognixia.jump.model.Customer;

/*
 * Transactions will be used for all transactions and will be the only object instance stored in session
 * This will help limit how many objects are being stored in session and all transactions will
 * be performed in one locations
 * 
 * It will also 
 */
public class Transactions {

	private Customer user;
	private HashMap<String, Customer> allUsers;
	private HashMap<String, ArrayList<String>> transactions;
	
	// constructor will not need user, this is because user will be changeable at login
	public Transactions(HashMap<String, Customer> listOfUsers,
						HashMap<String, ArrayList<String>> history) {
		super();
		this.allUsers = listOfUsers;
		this.transactions = history;
	}
	
	// logging transactions
	private void logTransaction(String username, String transaction) {
		transactions.get(username).add(transaction);
	}
	
	// Adding a new user to allUsers
	public void addUser(Customer user) {
		allUsers.put(user.getUsername(), user);
		// create a new transaction list for them
		transactions.put(user.getUsername(), new ArrayList<String>());
		// have logTransactions take care of logging the transaction
		logTransaction(user.getUsername(), "Initial Balance: " + user.getAccount().getBalance());
	}
	
	// checking permissions to see if user exists
	public boolean checkPermissions(String username, String password) {
		if(allUsers.containsKey(username) && 
		   allUsers.get(username).getPassword().equals(password)) {
			// make the current user this user
			user = allUsers.get(username);
			// both username and password are accurate so return true
			return true;
		}else {
			// permissions are incorrect so return false
			return false;
		}
	}
	
	// make a deposit
	public void deposit(Float deposit) {
		// deposit into user account
		user.getAccount().deposit(deposit);
		// log the deposit
		logTransaction(user.getUsername(), "Deposit: " + deposit);
	}
	
	// make a withdrawal
	public void withdraw(Float withdrawal) {
		// withdraw from user's account
		user.getAccount().withdraw(withdrawal);
		// log the withdrawal
		logTransaction(user.getUsername(), "Withdraw: " + withdrawal);
	}
	
	// transfer funds will need both the transfer amount and the receiver
	public void transfer(Float transfer, String receiverUsername) {
		// make a withdraw from user
		user.getAccount().withdraw(transfer);
		// then deposit into receiver
		allUsers.get(receiverUsername).getAccount().deposit(transfer);
		// finally log both transactions for each of the users
		logTransaction(user.getUsername(), "Transfer amount: " + transfer + ", Transfer receiver: " + receiverUsername);
		logTransaction(receiverUsername, "Transfer amount: " + transfer + ", Transfer sender: " + user.getUsername());
	}
	
	// get the current user
	public Customer getUser() {
		return user;
	}
	
	// get the allUsers
	public HashMap<String, Customer> getAllUsers(){
		return allUsers;
	}
	
	// get the users transactions
	public ArrayList<String> getUsersTransactions(){
		return transactions.get(user.getUsername());
	}
}
