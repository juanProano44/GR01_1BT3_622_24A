package com.example.servlet;

import com.example.dao.SolicitudTutoriaDAO;
import com.example.model.SolicitudTutoria;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/responderSolicitud")
public class ResponderSolicitudTutoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int solicitudId = Integer.parseInt(request.getParameter("solicitudId"));
        String accion = request.getParameter("accion");

        SolicitudTutoriaDAO solicitudDAO = new SolicitudTutoriaDAO();
        SolicitudTutoria solicitud = solicitudDAO.getById(solicitudId);

        // Actualizar el estado según la acción
        if ("aceptar".equals(accion)) {
            solicitud.setEstado("aceptada");
        } else if ("rechazar".equals(accion)) {
            solicitud.setEstado("rechazada");
        }

        solicitudDAO.update(solicitud);

        // Redirigir de vuelta a la lista de solicitudes
        response.sendRedirect("tutor/tutor.jsp");
    }
}
