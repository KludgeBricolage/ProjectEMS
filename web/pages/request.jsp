<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/tablesort.js"></script>				
</head>
<body style="overflow:hidden">
  <div class="container">
  <s:set id="itemList" value="%{itemList}"/>
  <s:set id="roomList" value="%{roomList}"/>
  <s:set id="categoryList" value="%{categoryList}"/>
  <s:set id="deadlineDuration" value="%{deadlineDuration}"/>
  <s:set id="user" value="%{i}"/>
  
  <input id="user" type="hidden" value="<s:property value="user"/>">
  <input id="categoryList" type="hidden" value="<s:property value="categoryList"/>">
  <input id="deadlineDuration" type="hidden" value="<s:property value="deadlineDuration"/>">
  <input type="hidden" id="catCount" value="<s:property value="%{#categoryList.size()}"/>">
  <s:iterator value="categoryList" var="item" status="pos">
     <input id="catItem<s:property value="%{#pos.index}"/>" type="hidden" value="<s:property/>" />
  </s:iterator>
  </div>
  <div class="ui top fixed menu">
    <div class="left menu">
	    <div class="item" style="margin:0;padding:0">
	      <img src="/ems/assets/images/logo.png" style="width:55px;height:55px">
	    </div>
    </div>
   	<div class="right menu">
   	  <form action="login.action" method="post" class="ui large form">	
		<input type="hidden" id="userId" name="userId" value="LOGOUTUSER">   	
	  	<a class="item" href="#" onclick="$(this).closest('form').submit()">Logout</a>
    </form>
    </div>
  </div>
  
  
  <div class="ui top aligned center aligned grid">
	<div class="fifteen center aligned column">
	<ul id="progressbar">
		<li class="active">Details</li>
		<li>Items</li>
		<li>Review</li>
	</ul>
	</div>
  </div>
	
	<fieldset id="fs1">	
	  <div class="ui column grid" style="height:800px !important">
	  	<div class="ui six wide column"></div>
		<div class="ui four wide column">
		  <div class="ui center aligned raised segment one column grid" >
		  	<div class="one wide" style="margin:2mm">
		  	  <h1>Insert Details</h1>
   			  <p>When and Where?</p>
	   		  <div class="ui input">
				<input id="dateNeeded" class="ui form" type="text" name="date" placeholder="Date Needed" required readonly style="margin-bottom: 2mm"/>	
	   		  </div>
	   		  <br/>
		  	  <select id="room" class="ui dropdown">
		  		<option value="">Room</option>
		  		<s:iterator value="roomList"><option value="<s:property/>"><s:property/></option></s:iterator>
		  	  </select>
		  	  <br/>
		  	  <div class="ui bottom attached negative message transition hidden">
				<i class="close icon"></i><div class="header">Error!</div><p id="errorFs1"></p>
		  	  </div>
		  	  <input id="next1" type="button" name="next" class="ui blue button" value="Next" style="margin-top: 2mm"/>
		   </div>
		  </div>
	    </div>
	    <div class="ui six wide column"></div> 
	  </div>
	</fieldset>
	
	<fieldset id="fs2">
	 <div class="ui raised segment">
	  <div class="ui top aligned centered grid" style="height:800px !important">
	  	<div class="fifteen wide center aligned column"><h1>Select Items to Reserve</h1></div>
		<div class="four wide center aligned column" style="height:80%;overflow:auto">
			<select id="catSearch" name="categories" class="ui fluid search selection dropdown">
				<option value="">Search Categories</option>
				<option value="All">All</option>
			</select>
			<div id="cart" class="ui vertical fluid menu">
				<div class="header item">Selected Items</div>
			</div>
			<div class="ui bottom attached negative message transition hidden">
			  <i class="close icon"></i><div class="header">Error!</div><p id="errorFs2"></p>
		  	</div> 	
			<input type="button" name="previous" class="ui blue button" value="Previous" />
			<input id="next2" type="button" name="next" class="ui blue button" value="Next" />
		</div>
		<div class="twelve wide column" style="height:100%;overflow:auto">
		<table id="tableItems"class="ui selectable sortable fixed celled table" style="text-align:center">
	      <thead>
	        <tr><th class="sorted ascending">Item ID</th><th>Category</th><th>Brand</th><th>Serial No.</th><th>Property No.</th></tr>
	      </thead>
	      <tbody>
			<s:iterator value="itemList" var="item" status="pos"><tr>
	          <td id="itemId"><s:property value="itemId"/></td><td id="item"><s:property value="category"/></td>
	          <td><s:property value="brand"/></td><td><s:property value="serialNo"/></td><td><s:property value="propertyNo"/></td>
	        </tr></s:iterator>
		  </tbody>
		</table>
		</div>
		</div>
		<table id="tableDead" style="display: none;"></table>
		</div>
	</fieldset>
	<fieldset>
	
	<div class="ui column grid" style="height:800px !important">
	  	<div class="ui six wide column"></div>
		<div class="ui four wide column">
		  <div class="ui center aligned raised segment one column grid" >
		  	<div class="one wide" style="margin:2mm">
		  	
	  	    	<table id="reviewTable" class="ui definition fixed table"></table>
	  	    
  	  	    	<input type="button" name="previous" class="ui blue button" value="Previous" style="margin-bottom: 2mm"/>
  	 
	  	  		<form id="request" action="user.action" method="post">	
					<input type="hidden" id="data" name="data" value="">
					<input id="subBtn" type="submit" name="submit" class="ui blue button" value="Submit" />
				</form>
		  	</div>
		  </div>
		</div>
	</div>
	

	</fieldset>

</body>