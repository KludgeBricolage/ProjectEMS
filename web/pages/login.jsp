<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>	
	<title>Login</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/semantic.min.css">
</head>
<body>
<div class="ui middle aligned center aligned grid">
  <div class="column" style="width:30%">
  	<h1 class="ui header" style="margin-top:30%">Login</h1>
  	<div class="ui raised segment">
	  	<h2>iACADEMY Item Reservation</h2>
		<form name="login" action="login" method="post" class="ui large form">
			<p><input type="text" id="user" name="userId" placeholder="User ID"></p>	
			<p><input type="submit" value="Login" class="ui fluid large submit blue button"/></p>
		</form>
	</div>
  </div>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		
		function checkSession() {
			var userSession = ${sessionScope.userIdSession};
			if(!(userSession === undefined || userSession === null)) {
				document.login.submit(); //http://www.javascript-coder.com/javascript-form/javascript-form-submit.phtml
			}
		}
		
		checkSession();
		
		$(window).on("navigate", function (event, data) { //Source = http://stackoverflow.com/a/18213393
			var direction = data.state.direction;
			if (direction == 'back') {
  				checkSession();
			}
		});
	});
	$(".submit").click(function(e) {
		$("#user").val($("#user").val().replace("-", "")); //Wait for database. Remove this if '-' is given
	});
</script>
</html>