<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Registrar Tutoría</title>
</head>
<body>
<h2>Registrar una Nueva Tutoría</h2>
<form action="${pageContext.request.contextPath}/RegistrarTutoriaServlet" method="post">
  <label for="materia">Materia:</label>
  <input type="text" id="materia" name="materia" required><br>
  <label for="fecha">Fecha:</label>
  <input type="date" id="fecha" name="fecha" required><br>
  <button type="submit">Registrar</button>
</form>
</body>
</html>
