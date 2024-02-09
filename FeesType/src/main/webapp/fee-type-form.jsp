<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Ibt Fee Type</title>
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
				<a href="https://www.xadmin.net" class="navbar-brand"> Ibt Fee Type</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">List</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${type != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${type == null}">
					<form action="insert" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${type != null}">
            			Edit Amount
            		</c:if>
						<c:if test="${type == null}">
            			Add New Amount
            		</c:if>
					</h2>
				</caption>
				
				<c:if test="${type != null}">
					<input type="hidden" name="fee_type_id" value="<c:out value='${type.fee_type_id}' />" />
				</c:if>
			
				
				<fieldset class="form-group">
					<label>FEE TYPE ID</label> <input type="text"
						value="<c:out value='${type.fee_type_id}' />" class="form-control"
						name="fee_type_id" >
				</fieldset>

				<fieldset class="form-group">
					<label>FEE TYPE DESCRIPTION</label> <input type="text" 
						value="<c:out value='${type.fee_type_desc}' />" class="form-control"
						name="fee_type_desc" >
				</fieldset>

				

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>