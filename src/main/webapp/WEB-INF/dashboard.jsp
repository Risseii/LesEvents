<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	
	<div class="container">
		<nav class="d-flex justify-content-between align-items-center">
			<h1>Bienvenido ${user_session.first_name}</h1>
			<a href="/logout" class="btn btn-danger">Cerrar sesion</a>
		</nav>
		<div class="row">
		<h2>Eventos en tu estado</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Evento</th>
					<th>Fecha</th>
					<th>Location</th>
					<th>Estado</th>
					<th>Planner</th>
					<th>Acciones</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="evento" items="${eventos_miestado}">
					<tr>
						<td><a href="/events/${evento.id}">${evento.name}</a></td>
						<td>${evento.eventDate}</td>
						<td>${evento.location}</td>
						<td>${evento.state}</td>
						<td>${evento.planner.first_name}</td>
						<td>
							<c:if test="${evento.planner.id == user.id}">
								<a href="/events/edit/${evento.id}" class="btn btn-warning">Editar</a>
								<form action="/events/delete/${evento.id}" method="post">
									<input type="hidden" name="_method" value="DELETE">
									<button type="submit" class="btn btn-danger">Eliminar</button>
								</form>
							</c:if>
							
							<c:if test="${evento.planner.id != user.id}">
								<c:choose>
									<c:when test="${evento.attendees.contains(user)}">
										<span>Asistirá - <a href="/events/remove/${evento.id}" class="btn btn-danger">Ya no asistiré</a></span>
									</c:when>
									<c:otherwise>
										<a href="/events/join/${evento.id}" class="btn btn-success">Asistir</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</td>
				
				</c:forEach>
			
			</tbody>
		
		</table>
	</div>
	
	<div class="row">
		<h2>Eventos en otros estados</h2>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Evento</th>
					<th>Fecha</th>
					<th>Location</th>
					<th>Estado</th>
					<th>Planner</th>
					<th>Acciones</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="evento" items="${eventos_otrosedos}">
					<tr>
						<td><a href="/events/${evento.id}">${evento.name}</a></td>
						<td>${evento.eventDate}</td>
						<td>${evento.location}</td>
						<td>${evento.state}</td>
						<td>${evento.planner.first_name}</td>
						<td>
							<c:if test="${evento.planner.id == user.id}">
								<a href="/events/edit/${evento.id}" class="btn btn-warning">Editar</a>
								<form action="/events/delete/${evento.id}" method="post">
									<input type="hidden" name="_method" value="DELETE">
									<button type="submit" class="btn btn-danger">Eliminar</button>
								</form>
							</c:if>
							
							<c:if test="${evento.planner.id != user.id}">
								<c:choose>
									<c:when test="${evento.attendees.contains(user)}">
										<span>Asistirá - <a href="/events/remove/${evento.id}" class="btn btn-danger">Ya no asistiré</a></span>
									</c:when>
									<c:otherwise>
										<a href="/events/join/${evento.id}" class="btn btn-success">Asistir</a>
									</c:otherwise>
								</c:choose>
							</c:if>
						</td>
				
				</c:forEach>
			
			</tbody>
		</table>
	</div>
	<div class="row">
		<h2>Crear evento</h2>
		<form:form action="/events/create" method="post" modelAttribute="event">
			<form:hidden path="planner" value="${user_session.id}"/>
			<div class="form-group">
				<form:label path="name">Nombre:</form:label>
				<form:input path="name" class="form-control"/>
				<form:errors path="name" class="text-danger"/>
			</div>
			
			<div class="form-group">
				<form:label path="eventDate">Fecha:</form:label>
				<form:input path="eventDate" type="date" class="form-control"/>
				<form:errors path="eventDate" class="text-danger"/>
			</div>
			
			<div class="form-group">
				<form:label path="location">Location:</form:label>
				<form:input path="location" class="form-control"/>
				<form:errors path="location" class="text-danger"/>
			</div>
			
			<div class="form-group">
						<form:label path="state">Estados:</form:label>
						<form:select path="state" class="form-control">
							<c:forEach var="state" items="${states}">
								<option value="${state}">${state}</option>	
							</c:forEach>
						</form:select>
						<form:errors path="state" class="text-danger"/>
			</div>
			
			<input type="submit" value="Crear evento" class="btn btn-success">
			
		</form:form>
	
	</div>
		
	</div>
	
	
</body>
</html>