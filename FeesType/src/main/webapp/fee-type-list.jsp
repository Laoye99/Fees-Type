<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ibt Fee Type </title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: blue">
			<div>
				<a href="https://www.xadmin.net" class="navbar-brand">Ibt Fee Type  </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">List</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Fee Types</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Fee Type</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						
						<th>FEE TYPE ID</th>
						<th>FEE TYPE DESCRIPTION</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="type" items="${listType}">

						<tr>
							<td><c:out value="${type.fee_type_id}" /></td>
							<td><c:out value="${type.fee_type_desc}" /></td>
							<td><a href="edit?fee_type_id=<c:out value='${type.fee_type_id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?fee_type_id=<c:out value='${type.fee_type_id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>