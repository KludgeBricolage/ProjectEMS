<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<head>
	<title>Database Manipulation</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript"> //Convert to a JSP file
		$(document).ready(function() {
			$('#btnSubmit').on( 'click', function () {
				var category = $('#category').val();
				var brand = $('#brand').val();
				var serialNo = $('#serialNo').val();
				var propertyNo = $('#propertyNo').val();
				
				var sendData = category + "," + brand + "," + serialNo + "," + propertyNo;
				$('#sendData').val(sendData);
			});
		});
	</script>
</head>
<body>

	<s:set name="itemList" value="%{itemList}"/>
	<s:set name="categoryList" value="%{categoryList}"/>
	<s:set name="brandList" value="%{brandList}"/>		
	
	<h2>Item List</h2>
	<s:iterator value="itemList" var="item" status="pos">
		Item ID: <s:property value="itemId"/> <br/>
		Category: <s:property value="category"/> <br/>
		Brand: <s:property value="brand"/> <br/>
		Serial: <s:property value="serialNo"/> <br/>
		Property: <s:property value="propertyNo"/> <br/>
		Available: <s:property value="available"/> <br/>
	</s:iterator>  
	
	<hr/>
	<h2>List categories and brands</h2>
	<b>Categories:</b> 
	<s:iterator value="categoryList">
		<s:property/>, 
	</s:iterator>
	<br/>
	<b>Brands:</b> 
	<s:iterator value="brandList">
		<s:property/>, 
	</s:iterator>
	<hr/>
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
		
		<p><input id="btnSubmit" type="submit" value="Submit"/></p>
		<p><input type="reset"/></p>
	</form>    
</body>
</html>