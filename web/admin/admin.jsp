<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
	<title>Admin Panel</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.11.3.min.js"></script>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/semantic.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/semantic.min.js"></script>   
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/admin.js"></script>
   
</head>
<body>`
	<div class="container">
	<s:set id="itemList" value="%{itemList}"/>
	<s:set id="categoryList" value="%{categoryList}"/>
	<s:set id="brandList" value="%{brandList}"/>
	<s:set id="studentList" value="%{studentList}"/>				
	<s:set id="courseList" value="%{courseList}"/>
	<s:set id="requestList" value="%{requestList}"/>
	
	<s:set id="deadline" value="%{deadline}"/>
	
	<input type="hidden" id="studCount" value="<s:property value="%{#studentList.size()}"/>">
	<input type="hidden" id="catCount" value="<s:property value="%{#categoryList.size()}"/>">
	<input type="hidden" id="reqCount" value="<s:property value="%{#requestList.size()}"/>">
    <s:iterator value="categoryList" var="item" status="pos">
      <input id="catItem<s:property value="%{#pos.index}"/>" type="hidden" value="<s:property/>" />
    </s:iterator>
	</div>
	<div class="ui tabular fixed menu">
		<a class="item active" data-tab="first">Requests</a>
		<a class="item" data-tab="second">Item Availability</a>
		<a class="item" data-tab="third">Add Items</a>
		<a class="item" data-tab="fourth">Add Users/Rooms</a>
		<a class="item" data-tab="fifth">Edit </a>
	</div>
	
	<div class="ui bottom attached tab active segment" data-tab="first" style="margin-top:5mm">
	  <form id="respondForm" action="admin.action" method="post">
		
	  	
		  <table class="ui fixed celled table" style="text-align:center">
		    <thead>
		      <tr><th>Request</th><th>Student ID</th><th>Item ID</th><th>Category</th><th>Brand</th><th>Serial No.</th>
		      <th>Property No.</th><th>Room</th><th>Date Requested</th><th>Date Needed</th><th>Date Deadline</th>
		      <th>Decline</th><th>Allow</th><th>Returned</th></tr>
		    </thead>
		    <tbody>
		      <s:iterator value="requestList" var="item" status="pos">		  
				<tr>
				<td id="request<s:property value="%{#pos.index}"/>"><s:property value="requestId"/></td>
				<td><s:property value="studentId"/></td><td><s:property value="itemId"/></td>
				<td><s:property value="category"/></td><td><s:property value="brand"/></td>
				<td><s:property value="serialNo"/></td><td><s:property value="propertyNo"/></td>
				<td><s:property value="room"/></td><td><s:property value="dateReq"/></td>
				<td><s:property value="dateOfRes"/></td><td><s:property value="dateDeadline"/></td>
				
				<td id="decline<s:property value="%{#pos.index}"/>"><s:property value="declined"/></td>
				<td id="allow<s:property value="%{#pos.index}"/>"><s:property value="allowed"/></td>
				<td id="return<s:property value="%{#pos.index}"/>"><s:property value="returned"/></td>				
			  </s:iterator>
			</tbody>
		  </table>
	  </form>
	</div>
	<div class="ui bottom attached tab segment" data-tab="second" style="margin-top:5mm">
	 	<h2 style="display:inline">Item List</h2> 	 
	 	<select id="catSearch" name="categories" class="ui search selection dropdown">
			<option value="">Search Categories</option>
			<option value="All">All</option>
		</select>
		<input type="hidden" id="itemCount" value="<s:property value="%{#itemList.size()}"/>">
	 	<form action="admin.action" method="post">
			<table id="tableItems"class="ui selectable fixed celled table" style="text-align:center;margin-top:3mm">
		      <thead>
		        <tr><th>Item ID</th><th>Category</th><th>Brand</th><th>Serial No.</th><th>Property No.</th><th>Available</th></tr>
		      </thead>
		      <tbody>
				<s:iterator value="itemList" var="item" status="pos"><tr>
		          <td id="itemId"><s:property value="itemId"/></td><td id="item"><s:property value="category"/></td>
		          <td><s:property value="brand"/></td><td><s:property value="serialNo"/></td><td><s:property value="propertyNo"/></td>
		          <td id="available<s:property value="%{#pos.index}"/>"><s:property value="available"/></td>
		        </tr></s:iterator>
			  </tbody>
			</table>
		</form>
	</div>
	<div class="ui bottom attached tab segment" data-tab="third" style="margin-top:5mm">		
	  <div class="ui center aligned grid" style="width:100%">
	  	<div class="five wide center aligned column">
		  <h2>Add item</h2>
		  <form action="admin.action" method="post" class="ui form">
		  
		  	<p><select id="category" class="ui fluid dropdown" required>
			  <option value="">Categories</option>
			  <s:iterator value="categoryList">
		  	    <option value="<s:property/>"><s:property/></option>
			  </s:iterator>
			</select></p>
		  	<p><select id="brand" class="ui fluid dropdown" required>
			  <option value="">Brands</option>
			  <s:iterator value="brandList">
		  	    <option value="<s:property/>"><s:property/></option>
			  </s:iterator>
			</select></p>
			<p><input type="text" id="serialNo" placeholder="Serial No." required></p>
			<p><input type="text" id="propertyNo" placeholder="Property No." required></p>		

			<input type="hidden" id="addItem" name="addItem" value="">
			<p><input id="btnAddItem" type="submit" value="Submit" class='ui small button'/> <input type="reset" class='ui small button'/></p>
		  </form>
		</div>
		<div class="five wide center aligned column">  
			<h2>Add category</h2>
			<form action="admin.action" method="post" class="ui form">
				<p><input type="text" name="addCategory" placeholder="Category: " required></p>	
				<p><input type="submit" value="Submit" class='ui small button'/> <input type="reset" class='ui small button'/></p>
			</form>
		</div>
		<div class="five wide center aligned column">
			<h2>Add brand</h2>
			<form action="admin.action" method="post" class="ui form">
				<p><input type="text" name="addBrand" placeholder="Brand: " required/></p>
				<p><input type="submit" value="Submit" class='ui small button'/> <input type="reset" class='ui small button'/></p>
			</form>
		</div>
	  </div>
	</div>	
	<div class="ui bottom attached tab segment" data-tab="fourth" style="margin-top:5mm">
		<div class="ui center aligned grid" style="width:100%">
		  <div class="five wide center aligned column">
			<h2>Add Student</h2>
			<form action="admin.action" method="post" class="ui form">
			  <p><input type="text" id="studentId" placeholder="Student ID " required/></p>
			  <p><input type="text" id="firstName" placeholder="First Name: " required/></p>
			  <p><input type="text" id="lastName" placeholder="Last Name: " required/></p>
			  <p><select id="course" class="ui fluid dropdown" required>
			      <option value="">Courses</option>
			      <s:iterator value="courseList">
		  			<option value="<s:property/>"><s:property/></option>
			      </s:iterator>
			  </select></p>	
			  <input type="hidden" id="addStudent" name="addStudent" value="">
			  <p><input id="btnAddStudent" type="submit" value="Submit" class='ui small button'/> <input type="reset" class='ui small button'/></p>
			</form>
		  </div>
		  <div class="five wide center aligned column">
		  	<h2>Add course</h2>
			<form action="admin.action" method="post" class="ui form">
				<p><input type="text" name="addCourse" placeholder="Course: " required/></p>
				<p><input type="submit" value="Submit" class='ui small button'/> <input type="reset" class='ui small button'/></p>
			</form>
		  </div>
		  <div class="five wide center aligned column">
		  	<h2>Add rooms</h2>
			<form action="admin.action" method="post" class="ui form">
				<p><input type="text" name="addRoom" placeholder="Room: " required/></p>
				<p><input type="submit" value="Submit" class='ui small button'/> <input type="reset" class='ui small button'/></p>
			</form>
		  </div>
  		  <div class="five wide center aligned column">
			<h2>Add Admin</h2>
			<form action="admin.action" method="post" class="ui form">
			  <p><input type="text" id="adminId" placeholder="Admin ID " required/></p>
			  <p><input type="text" id="adminName" placeholder="Name: " required/></p>
			  <p><input type="password" id="adminPw" placeholder="Password: " required/></p>
			
			  <input type="hidden" id="addAdmin" name="addAdmin" value="">
			  <p><input id="btnAddAdmin" type="submit" value="Submit" class='ui small button'/> <input type="reset" class='ui small button'/></p>
			</form>
		  </div>			  
		</div>
	</div>
	<div class="ui bottom attached tab segment" data-tab="fifth" style="margin-top:5mm">
		<div class="ui center aligned grid" style="width:100%">
	  	  <div class="five wide center aligned column">
			<h2>Update Deadline Duration</h2>
			<form action="admin.action" method="post" class="ui form">
				<p><input type="text" id="updateDeadline" name="updateDeadline" value="<s:property value="deadline"/>" placeholder="Deadline" required/></p>
				<p><input type="submit" value="Submit" class='ui small button'/> <input type="reset" class='ui small button'/></p>
			</form>
	  	  </div>
	  	</div>
	</div>
</body>
</html>