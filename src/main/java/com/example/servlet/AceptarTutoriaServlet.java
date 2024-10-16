package com.example.servlet;

import com.example.dao.SolicitudDAO;
import com.example.model.Alumno;
import com.example.model.Solicitud;
import com.example.model.Tutor;
import com.example.model.Tutoria;
import com.example.service.AceptarTutoriaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AceptarTutoriaServlet")
public class AceptarTutoriaServlet extends HttpServlet {

    private SolicitudDAO solicitudDAO;
    private AceptarTutoriaService aceptarTutoriaService;

    @Override
    public void init() {
        solicitudDAO = new SolicitudDAO();
        this.aceptarTutoriaService = new AceptarTutoriaService();// Inicializar DAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tutoriaId = Integer.parseInt(request.getParameter("tutoriaId"));

        try {
            aceptarTutoriaService.aceptarTutoria(tutoriaId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Redirigir al servlet que consulta las tutorías disponibles
        response.sendRedirect(request.getContextPath() + "/User/consultarTutorias");

    }
}
