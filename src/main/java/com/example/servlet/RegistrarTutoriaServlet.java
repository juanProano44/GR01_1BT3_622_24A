package com.example.servlet;

import com.example.dao.TutoriaDAO;
import com.example.model.Tutor;
import com.example.model.Tutoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegistrarTutoriaServlet")
public class RegistrarTutoriaServlet extends HttpServlet {

    private TutoriaDAO tutoriaDAO;

    public void init() {
        tutoriaDAO = new TutoriaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String materia = request.getParameter("materia");
        String fecha = request.getParameter("fecha");

        Tutoria tutoria = new Tutoria();
        tutoria.setMateria(materia);
        tutoria.setFecha(fecha);
        tutoria.setEstado("Pendiente");
        // Asignar el tutor (deberás obtener el ID del tutor de la sesión o asignar manualmente)
        Tutor tutor = new Tutor();
        tutor.setId(1);  // Aquí colocas el ID del tutor (cambiarlo según el caso real)
        tutoria.setTutor(tutor);

        tutoriaDAO.saveTutoria(tutoria);
        response.sendRedirect("Tutor/tutor.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
