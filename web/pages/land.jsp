<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Your request is being reviewed</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/semantic.min.css">
</head>
<body>

	<div class="ui middle aligned center aligned grid">
	  <div class="column" style="width:30%">
	  	<h2 class="ui header" style="margin-top:30%">Your request has been filed. </h2>
	  	<div class="ui raised segment">
		  	<h3>Wait for a moderators response.</h3>
			<form action="login.action" method="post" class="ui large form">	
				<input type="hidden" id="userId" name="userId" value="LOGOUTUSER">
				<p><button type="submit" name="submit" class="ui blue button" value="LOGOUT">Logout</button></p>
			</form>
		</div>
	  </div>
	</div>	
</body>
</html>