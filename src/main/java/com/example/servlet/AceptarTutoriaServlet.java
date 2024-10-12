package com.example.servlet;

import com.example.dao.SolicitudTutoriaDAO;
import com.example.dao.TutoriaDAO;
import com.example.model.Alumno;
import com.example.model.SolicitudTutoria;
import com.example.model.Tutoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AceptarTutoriaServlet")
public class AceptarTutoriaServlet extends HttpServlet {

    private SolicitudTutoriaDAO solicitudTutoriaDAO;
    private TutoriaDAO tutoriaDAO;  // DAO para manipular las tutorías

    @Override
    public void init() {
        solicitudTutoriaDAO = new SolicitudTutoriaDAO();
        tutoriaDAO = new TutoriaDAO();  // Inicializar el DAO para las tutorías
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tutoriaId = Integer.parseInt(request.getParameter("tutoriaId"));

        // Asignar un valor quemado para el ID del alumno (temporalmente)
        int alumnoId = 1; // Valor quemado para pruebas, asume que el alumno con ID 1 existe en la tabla alumno

        // Crear un objeto Alumno con el ID quemado
        Alumno alumno = new Alumno();
        alumno.setId(alumnoId);  // Valor quemado

        // Obtener la tutoría correspondiente desde la base de datos
        Tutoria tutoria = tutoriaDAO.getById(tutoriaId);
        if (tutoria == null) {
            // Si no se encuentra la tutoría, redirigir o manejar el error
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        // Crear la solicitud de tutoría
        SolicitudTutoria solicitud = new SolicitudTutoria();
        solicitud.setAlumno(alumno);
        solicitud.setTutoria(tutoria);
        solicitud.setEstado("Aceptada");

        // Guardar la solicitud de tutoría
        solicitudTutoriaDAO.guardarSolicitud(solicitud);

        // Actualizar el estado de la tutoría a "Aceptada"
        tutoria.setEstado("Aceptada");
        tutoriaDAO.update(tutoria);  // Método para actualizar la tutoría en la base de datos

        // Redirigir a la página de tutorías disponibles
        response.sendRedirect(request.getContextPath() + "/User/consultarTutorias");
    }

}
