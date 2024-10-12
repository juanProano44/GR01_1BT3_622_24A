<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Tutorías Creadas</title>
</head>
<body>
<h2>Tutorías Creadas</h2>
<table border="1">
  <tr>
    <th>ID</th>
    <th>Materia</th>
    <th>Fecha</th>
    <th>Estado</th>
  </tr>
  <c:forEach var="tutoria" items="${tutorias}">
    <tr>
      <td>${tutoria.id}</td>
      <td>${tutoria.materia}</td>
      <td>${tutoria.fecha}</td>
      <td>${tutoria.estado}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
