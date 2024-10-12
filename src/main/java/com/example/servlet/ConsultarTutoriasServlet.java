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

//@WebServlet("/User/consultarTutorias")
public class ConsultarTutoriasServlet extends HttpServlet {

    private TutoriaDAO tutoriaDAO;

    @Override
    public void init() {
        tutoriaDAO = new TutoriaDAO(); // Inicializar el DAO que maneja las tutorías
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del alumno (quemado para pruebas, debería obtenerse de la sesión)
        int alumnoId = 1; // Aquí puedes obtener el ID del alumno de la sesión en un entorno de producción

        // Obtener todas las tutorías disponibles que el alumno no ha aceptado
        List<Tutoria> tutorias = tutoriaDAO.getTutoriasDisponiblesParaAlumno(alumnoId);

        // Verificar que la lista de tutorías no esté vacía
        System.out.println("Tutorías disponibles para el alumno: " + tutorias.size());
        for (Tutoria tutoria : tutorias) {
            System.out.println("id: " + tutoria.getId() + " Fecha: " + tutoria.getFecha() + " - Materia: " + tutoria.getMateria());
        }

        // Pasar la lista de tutorías a la vista (JSP)
        request.setAttribute("tutorias", tutorias);

        // Redirigir a la página JSP que muestra las tutorías disponibles
        request.getRequestDispatcher("/User/tutoriasDisponibles.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirigir la solicitud POST a doGet para simplificar el manejo
        doGet(request, response);
    }
}
