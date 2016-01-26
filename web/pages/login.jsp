<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>	
	<title>Login</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/semantic.min.css">
</head>
<body>

<div class="ui middle aligned center aligned grid">
  <div class="column" style="width:30%">
  	<h2 class="ui header" style="margin-top:30%">iACADEMY Item Reservation</h2>
  	<div class="ui stacked segment">
	  	<h1>Login</h1>
		<form action="login" method="post" class="ui large form">
			<p><input type="text" name="userId" placeholder="User ID" required></p>	
			<p><input type="submit" value="Login" class="ui fluid large submit button"/></p>
		</form>
	</div>
  </div>
</div>
</html>