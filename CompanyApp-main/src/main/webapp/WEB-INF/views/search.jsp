<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search</title>
</head>
<body>

	<div align="center">
		<h5>Search </h6>
		<form:form  action="search" method="post">
		<input type="text" name="employeeName" >
			<button type="submit" name="search" >Search</button>
		</form:form>
	</div>
	
	<br/>
	<br/>
	
	
			
	<div align="center">
		<table>
		   <tr>
			<th>Name</th>
			<th>ProjectDetails</th>
			<th>Mail Id</th>
			<th>phone number</th>
           </tr>
			<c:forEach items="${emplist}" var="emp">
				<tr align ="center">
					<td>${emp.name}</td>
					<td>${emp.project}</td>
					<td>${emp.mailId}</td>
					<td>${emp.phoneNo}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>