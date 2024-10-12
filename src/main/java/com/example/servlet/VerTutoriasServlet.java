package com.example.servlet;

import com.example.dao.TutoriaDAO;
import com.example.model.Tutoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/VerTutoriasServlet")
public class VerTutoriasServlet extends HttpServlet {

    private TutoriaDAO tutoriaDAO;

    @Override
    public void init() {
        tutoriaDAO = new TutoriaDAO(); // Inicializar el DAO que maneja las tutorías
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Aquí debes obtener el ID del tutor logueado
        int tutorId = 1; // Temporal, cambiar por el ID real del tutor logueado

        // Obtener todas las tutorías creadas por el tutor
        List<Tutoria> tutorias = tutoriaDAO.getTutoriasByTutorId(tutorId);

        // Pasar la lista de tutorías a la vista (JSP)
        request.setAttribute("tutorias", tutorias);

        // Redirigir a la página JSP que muestra las tutorías creadas por el tutor
        request.getRequestDispatcher("/Tutor/verTutorias.jsp").forward(request, response);
    }
}
