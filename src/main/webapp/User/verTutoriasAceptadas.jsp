<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Tutorías Aceptadas</title>
</head>
<body>
<h2>Tutorías Aceptadas</h2>
<table border="1">
  <thead>
  <tr>
    <th>ID Tutoría</th>
    <th>Materia</th>
    <th>Fecha</th>
    <th>Estado</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="solicitud" items="${tutoriasAceptadas}">
    <tr>
      <td>${solicitud.tutoria.id}</td>
      <td>${solicitud.tutoria.materia}</td>
      <td>${solicitud.tutoria.fecha}</td>
      <td>${solicitud.estado}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
