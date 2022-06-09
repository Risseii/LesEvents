<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edicion de eventos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h2>Editar evento</h2>
		<form:form action="/events/update" method="post" modelAttribute="evento">
			<input type="hidden" name="_method" value="put">
			<form:hidden path="planner" value="${user_session.id}"/>
			<form:hidden path="id" value="${evento.id}"/>
			<div class="form-group">
				<form:label path="name">Nombre:</form:label>
				<form:input path="name" class="form-control"/>
				<form:errors path="name" class="text-danger" />
			</div>
			
			<div class="form-group">
				<form:label path="eventDate">Fecha:</form:label>
				<form:input path="eventDate" type="date" class="form-control"/>
				<form:errors path="eventDate" class="text-danger" />
			</div>
			
			<div class="form-group">
				<form:label path="location">Location:</form:label>
				<form:input path="location" class="form-control"/>
				<form:errors path="location" class="text-danger" />
			</div>
			
			<div class="form-group">
				<form:label path="state">Estados:</form:label>
				<form:select path="state" class="form-control">
					<c:forEach var="s" items="${states}">
						<c:choose>
							<c:when test="${s.equals(evento.state) }">
								<option selected value="${s}">${s}</option>
							</c:when>
							<c:otherwise>
								<option value="${s}">${s}</option>
							</c:otherwise>
						</c:choose>	
				 	</c:forEach>
				</form:select>
				<form:errors path="state" class="text-danger"/>
			</div>
			<input type="submit" class="btn btn-success" value="Actualizar"> 
		</form:form>
	
	</div>
</body>
</html>