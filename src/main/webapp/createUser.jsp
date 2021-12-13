<%@page import="java.util.HashMap"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Dollars Bank</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	
	</head>
	<body>
		
		<div class='container'>
	        <form action="CreateUserServlet" method="POST">
	        	<%
		        	String name = "";
	        		String username = "";
	        		String password = "";
	        		String balance = "";
	        		if(request.getSession().getAttribute("errors") != null){
	        			@SuppressWarnings("unchecked")
		        		HashMap<String, String> errors = (HashMap<String, String>)request.getSession().getAttribute("errors");
		        		name = errors.get("name");
		        		username = errors.get("username");
		        		password = errors.get("password");
		        		balance = errors.get("balance");
	        		}
	        	%>
	            <div class="mb-3">
	                <label for="name" class="form-label">Name</label>
	                <input type="text" class="form-control" id="name" name="name"/>
	                <div style="color: red"><%=name %></div>
	            </div>
	            <div class="mb-3">
	                <label for="username" class="form-label">Username</label>
	                <input type="text" class="form-control" id="username" name="username"/>
	                <div style="color: red"><%=username%></div>
	            </div>
	            <div class="mb-3">
	                <label for="password" class="form-label">Password</label>
	                <input type="password" class="form-control" id="password" aria-describedby="passwordHelpBlock" name="password"/>
	                <div id="passwordHelpBlock" class="form-text" <%if(!password.isBlank()){ %> style="color: red" <% } %> >
	                    Your password must be 8 characters long, must contain at least one capital letter, number, and special character.
	                </div>
	            </div>
	            <div class="mb-3">
	                <label for="balance" class="form-label">Initial Balance</label>
	                <input type="text" class="form-control" id="balance" name="balance"/>
	                <div style="color: red"><%=balance%></div>
	            </div>
	            <button type="submit" class="btn btn-primary">Submit</button>
	        </form>
	    </div>
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	</body>
</html>