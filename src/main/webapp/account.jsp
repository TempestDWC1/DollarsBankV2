<%@page import="com.cognixia.jump.service.Transactions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Dollars Bank</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	</head>
	<body>
		<%
			Transactions transactions = (Transactions)request.getSession().getAttribute("transactions");
		%>
		<div class='container'>
	        <form action="AccountServlet" method="POST">
		        <div class="d-grid gap-2 col-6 mx-auto">
		        <h1 class="display-2">Hello <%=transactions.getUser().getUsername()%></h1>
		        <h3 class="display-6">Current Balance: <%=transactions.getUser().getAccount().getBalance() %></h3>
				  <button type="submit" value="Deposit" name="Deposit" class="btn btn-primary bt-lg">Deposit</button>
				  <button type="submit" value="Withdraw" name="Withdraw" class="btn btn-primary bt-lg">Withdraw</button>
				  <button type="submit" value="Transfer" name="Transfer" class="btn btn-primary bt-lg">Transfer</button>
				  <button type="submit" value="History" name="History" class="btn btn-primary bt-lg">History</button>
				  <button type="submit" value="SignOut" name="SignOut" class="btn btn-primary bt-lg">Sign Out</button>
			  </div>
	        </form>
	    </div>
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	</body>
</html>