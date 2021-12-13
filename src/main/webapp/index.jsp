<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Dollars Bank</title>
		
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	
	</head>
	<body>
		
		<div class="d-grid gap-2 col-6 mx-auto justify-content-md-center">
			<h1 class="display-2">Welcome to Dollars Bank</h1>
			<form action="MainServlet" method="GET">
			  <div class="d-grid gap-2 col-6 mx-auto">
				  <button type="submit" value="Login" name="login" class="btn btn-primary bt-lg">Login</button>
				  <button type="submit" value="Create User" name="createUser" class="btn btn-secondary bt-sm">Create User</button>
			  </div>
			</form>
		</div>
	
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
	</body>
</html>