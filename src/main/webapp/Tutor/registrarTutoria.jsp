<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Registrar Tutoría</title>
</head>
<body>
<h2>Registrar una Nueva Tutoría</h2>
<form action="${pageContext.request.contextPath}/RegistrarTutoriaServlet" method="post">
  <!-- Menú desplegable para seleccionar la materia -->
  <label for="materia">Materia:</label>
  <select id="materia" name="materia" required>
    <option value="">Selecciona una materia</option>
    <c:forEach var="materia" items="${materias}">
      <option value="${materia.codigomateria}">${materia.nombre}</option>
    </c:forEach>
  </select><br>

  <label for="fecha">Fecha:</label>
  <input type="date" id="fecha" name="fecha" required><br>

  <label for="horaInicio">Hora de inicio:</label>
  <input type="time" id="horaInicio" name="horaInicio" required><br>

  <label for="horaFin">Hora de fin:</label>
  <input type="time" id="horaFin" name="horaFin" required><br>

  <button type="submit">Registrar</button>
</form>


</body>
</html>
