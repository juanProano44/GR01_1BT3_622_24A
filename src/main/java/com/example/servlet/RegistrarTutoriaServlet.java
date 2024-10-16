package com.example.servlet;

import com.example.dao.TutoriaDAO;
import com.example.dao.MateriaDAO;
import com.example.model.Materia;
import com.example.model.Tutor;
import com.example.model.Tutoria;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet("/RegistrarTutoriaServlet")
public class RegistrarTutoriaServlet extends HttpServlet {

    private TutoriaDAO tutoriaDAO;
    private MateriaDAO materiaDAO; // Definir la variable materiaDAO

    public void init() {
        tutoriaDAO = new TutoriaDAO();
        materiaDAO = new MateriaDAO(); // Inicializar materiaDAO
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigoMateria = Integer.parseInt(request.getParameter("materia"));
        String fecha = request.getParameter("fecha");
        String horaInicio = request.getParameter("horaInicio");
        String horaFin = request.getParameter("horaFin");

        Tutoria tutoria = new Tutoria();
        Materia materia = new Materia();
        materia.setCodigomateria(codigoMateria);

        tutoria.setMateria(materia);
        tutoria.setFecha(java.sql.Date.valueOf(fecha)); // Convertir a java.sql.Date
        tutoria.setHoraInicio(horaInicio + ":00"); // Convertir a java.sql.Time
        tutoria.setHoraFin(horaFin + ":00"); // Convertir a java.sql.Time

        // Asignar el tutor
        Tutor tutor = new Tutor();
        tutor.setId(1);  // ID del tutor
        tutoria.setTutor(tutor);

        // Guardar la tutoría
        tutoriaDAO.registarTutoria(tutoria);

        // Redirigir
        response.sendRedirect(request.getContextPath() + "/Tutor/tutor.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener todas las materias disponibles
        List<Materia> materias = materiaDAO.getAllMaterias();

        // Imprimir la lista de materias en la consola para verificar
        System.out.println("Materias recuperadas de la base de datos:");
        for (Materia materia : materias) {
            System.out.println("Código: " + materia.getCodigomateria() + ", Nombre: " + materia.getNombre());
        }

        // Pasar la lista de materias al JSP
        request.setAttribute("materias", materias);
        request.getRequestDispatcher("/Tutor/registrarTutoria.jsp").forward(request, response);
    }
}
