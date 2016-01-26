<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
	<title>Request Page</title>

	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery.easing.min.js"></script>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/daterangepicker.min.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/moment.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/daterangepicker.min.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/semantic.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/semantic.min.js"></script>   
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/step.form.css">	
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/step.form.js"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/request.js"></script>		
</head>
<body style="overflow:hidden">
  <form id="msform">
	<ul id="progressbar">
		<li class="active">Account Setup</li>
		<li>Social Profiles</li>
		<li>Personal Details</li>
	</ul>
	<fieldset>
		<h2 class="fs-title">Create your account</h2>
		<h3 class="fs-subtitle">This is step 1</h3>
					
   		<div class="ui fluid input">
			<input id="dateNeeded" type="text" name="date" placeholder="Date Needed" required readonly onblur="yes()"/>	
			<!-- TODO: Can't get past dates ;; Default is current date ;; Icons ;; Re-enforce readonly-->
   		</div>
  		
		<input type="button" name="next" class="next action-button" value="Next" />
	</fieldset>
	<fieldset>
		<h2 class="fs-title">Social Profiles</h2>
		<h3 class="fs-subtitle">Your presence on the social network</h3>
		<input type="text" name="twitter" placeholder="Twitter" />
		<input type="text" name="facebook" placeholder="Facebook" />
		<input type="text" name="gplus" placeholder="Google Plus" />
		<input type="button" name="previous" class="previous action-button" value="Previous" />
		<input type="button" name="next" class="next action-button" value="Next" />
	</fieldset>
	<fieldset>
		<h2 class="fs-title">Personal Details</h2>
		<h3 class="fs-subtitle">We will never sell it</h3>
		<input type="text" name="fname" placeholder="First Name" />
		<input type="text" name="lname" placeholder="Last Name" />
		<input type="text" name="phone" placeholder="Phone" />
		<textarea name="address" placeholder="Address"></textarea>
		<input type="button" name="previous" class="previous action-button" value="Previous" />
		<input type="submit" name="submit" class="submit action-button" value="Submit" />
	</fieldset>
  </form>

</body>