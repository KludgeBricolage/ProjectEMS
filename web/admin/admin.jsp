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

	<s:set id="itemList" value="%{itemList}"/>
	<s:set id="categoryList" value="%{categoryList}"/>
	<s:set id="brandList" value="%{brandList}"/>
	<s:set id="requestList" value="%{requestList}"/>				

	<div class="ui tabular fixed menu">
		<a class="item active" data-tab="first">Requests</a>
		<a class="item" data-tab="second">Item Availability</a>
		<a class="item" data-tab="third">Add Items</a>
	</div>
	<div class="ui one column grid"></div><div class="ui one column grid"></div>
	<div class="ui one column grid"></div><div class="ui one column grid"></div>
	<div class="ui one column grid"></div><div class="ui one column grid"></div>
	<div class="ui bottom attached tab active segment" data-tab="first">
	  <form id="respondForm" action="admin.action" method="post">
		<input type="hidden" id="reqCount" value="<s:property value="%{#requestList.size()}"/>">
	  	
		  <table id="tableItems"class="ui fixed celled table" style="text-align:center">
		    <thead>
		      <tr><th>Request</th><th>Student ID</th><th>Category</th><th>Brand</th><th>Serial No.</th>
		      <th>Property No.</th><th>Room</th><th>Date Requested</th><th>Date Needed</th><th>Date Deadline</th>
		      <th>Decline</th><th>Allow</th><th>Returned</th></tr>
		    </thead>
		    <tbody>
		      <s:iterator value="requestList" var="item" status="pos">		  
				<tr>
				<td id="request<s:property value="%{#pos.index}"/>"><s:property value="requestId"/></td>
				<td><s:property value="studentId"/></td>
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
	<div class="ui bottom attached tab segment" data-tab="second">
	 	<h2>Item List</h2>
	 	<input type="hidden" id="itemCount" value="<s:property value="%{#itemList.size()}"/>">
	 	<form action="admin.action" method="post">
			<table id="tableItems"class="ui selectable fixed celled table" style="text-align:center">
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
	<div class="ui bottom attached tab segment" data-tab="third">		
	  <div class="ui center aligned grid" style="width:100%">
	  	<div class="five wide center aligned column">
			<h2>Add item</h2>
		
			<p>Category:
			  <select id="category"> <!-- Add default value + Error handling if default is selected -->
			    <s:iterator value="categoryList">
		  			  <option value="<s:property/>"><s:property/></option>
			    </s:iterator>
			  </select>
			</p>
			<p>Brand:
			  <select id="brand"> <!-- Add default value + Error handling if default is selected -->
			    <s:iterator value="brandList">
		  			  <option value="<s:property/>"><s:property/></option>
			    </s:iterator>
			  </select>
			</p>
			<p>Serial No.: <input type="text" id="serialNo"/></p>
			<p>Property No.: <input type="text" id="propertyNo"/></p>
		
			<form action="admin.action" method="post">
				<input type="hidden" id="sendData" name="addItem" value="">
				<p><input id="btnSubmit" type="submit" value="Submit"/> <input type="reset"/></p>
			</form>
		</div>
		<div class="five wide center aligned column">  
			<h2>Add category</h2>
			<form action="admin.action" method="post">
				<p>Category: <input type="text" name="addCategory"/></p>
				<p><input type="submit" value="Submit"/> <input type="reset"/></p>
			</form>
		</div>
		<div class="five wide center aligned column">
			<h2>Add brand</h2>
			<form action="admin.action" method="post">
				<p>Brand: <input type="text" name="addBrand"/></p>
				<p><input type="submit" value="Submit"/> <input type="reset"/></p>
			</form>
		</div>
	  </div>
	</div>	
</body>
</html>