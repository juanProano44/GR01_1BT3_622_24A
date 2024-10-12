package com.example.servlet;

import com.example.dao.SolicitudTutoriaDAO;
import com.example.model.SolicitudTutoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/VerTutoriasAceptadasServlet")
public class VerTutoriasAceptadasServlet extends HttpServlet {

    private SolicitudTutoriaDAO solicitudTutoriaDAO;

    @Override
    public void init() {
        solicitudTutoriaDAO = new SolicitudTutoriaDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del alumno (por ahora estamos usando un ID quemado, pero debería obtenerse de la sesión)
        int alumnoId = 1; // Cambiar según la lógica de la aplicación

        // Obtener las solicitudes de tutoría aceptadas por el alumno
        List<SolicitudTutoria> tutoriasAceptadas = solicitudTutoriaDAO.getTutoriasAceptadasPorAlumno(alumnoId);

        // Pasar las tutorías aceptadas al JSP
        request.setAttribute("tutoriasAceptadas", tutoriasAceptadas);

        // Redirigir a la página JSP que muestra las tutorías aceptadas
        request.getRequestDispatcher("/User/verTutoriasAceptadas.jsp").forward(request, response);
    }
}
