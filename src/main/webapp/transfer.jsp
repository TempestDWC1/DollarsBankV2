<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Dollars Bank</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	
	</head>
	<body>
		
		<div class="d-grid gap-2 col-6 mx-auto">
			<h1 class="display-4">How much would you like to transfer and who will be receiving the money</h1>
			<h3 class="display-6" style="color: red"><%=request.getSession().getAttribute("error")%></h3>
			<form action="TransferServlet" method="POST">
			  <div class="mb-3">
                    <label for="transfer" class="form-label">transfer</label>
                    <input type="text" class="form-control" id="transfer" name="transfer"/>
                    <label for="username" class="form-label">username</label>
                    <input type="text" class="form-control" id="username" name="username"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	</body>
</html>