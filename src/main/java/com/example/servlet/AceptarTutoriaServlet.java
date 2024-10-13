package com.example.servlet;

import com.example.dao.SolicitudDAO;
import com.example.model.Alumno;
import com.example.model.Solicitud;
import com.example.model.Tutor;
import com.example.model.Tutoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AceptarTutoriaServlet")
public class AceptarTutoriaServlet extends HttpServlet {

    private SolicitudDAO solicitudDAO;

    @Override
    public void init() {
        solicitudDAO = new SolicitudDAO(); // Inicializar DAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tutoriaId = Integer.parseInt(request.getParameter("tutoriaId"));

        // Cargar el alumno y tutor de forma apropiada
        int alumnoId = 1; // Valor quemado para pruebas, asume que el alumno con ID 1 existe
        int tutorId = 1;  // Valor quemado para pruebas, asume que el tutor con ID 1 existe

        // Crear los objetos de entidad correspondientes
        Alumno alumno = new Alumno();
        alumno.setId(alumnoId);

        Tutor tutor = new Tutor();
        tutor.setId(tutorId);

        Tutoria tutoria = new Tutoria();
        tutoria.setId(tutoriaId);

        // Crear la solicitud de tutoría
        Solicitud solicitud = new Solicitud();
        solicitud.setTutoria(tutoria);  // Asociar la tutoría
        solicitud.setAlumno(alumno);    // Asociar el alumno
        solicitud.setTutor(tutor);      // Asociar el tutor
        solicitud.setEstado("Pendiente");  // Estado inicial

        // Debug: Verificación de los datos en la consola
        System.out.println("Tutoria ID: " + solicitud.getTutoria().getId());
        System.out.println("Alumno ID: " + solicitud.getAlumno().getId());
        System.out.println("Tutor ID: " + solicitud.getTutor().getId());
        System.out.println("Estado: " + solicitud.getEstado());

        // Guardar la solicitud en la base de datos
        solicitudDAO.solicitarTutoria(solicitud);

        // Redirigir al servlet que consulta las tutorías disponibles
        response.sendRedirect(request.getContextPath() + "/User/consultarTutorias");

    }
}
