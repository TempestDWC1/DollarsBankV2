<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.cognixia.jump.service.Transactions"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Dollars Bank</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	
	</head>
	<body>
		
		<div class="d-grid gap-2 col-6 mx-auto">
			<h1 class="display-4">Last 5 transactions</h1>
			<form action="HistoryServlet" method="POST">
				<div class="mb-3">
					<%
						Transactions transactions = (Transactions)request.getSession().getAttribute("transactions");
						ArrayList<String> userHistory = transactions.getUsersTransactions();
						List<String> history;
						if(userHistory.size() > 5){
							history = userHistory.subList(userHistory.size()-5, userHistory.size());
						}else{
							history = userHistory;
						}
						
						for(String entry: history){%>
							<h4 class="display-8"><%=entry %></h4>
						<%}
					%>
				</div>
                <button type="submit" class="btn btn-primary">Go Back</button>
            </form>
        </div>
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	</body>
</html>