package com.example.dao;

import com.example.model.Tutoria;
import com.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TutoriaDAO {

    public List<Tutoria> getAllTutorias() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Tutoria", Tutoria.class).list();
        }
    }

    public void saveTutoria(Tutoria tutoria) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(tutoria);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTutoria(int tutoriaId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tutoria tutoria = session.get(Tutoria.class, tutoriaId);
            if (tutoria != null) {
                session.delete(tutoria);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public Tutoria getTutoriaById(int tutoriaId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tutoria.class, tutoriaId); // Obtiene la tutoría por su ID
        }
    }

    public List<Tutoria> getTutoriasByTutorId(int tutorId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Tutoria t WHERE t.tutor.id = :tutorId", Tutoria.class)
                    .setParameter("tutorId", tutorId)
                    .list();
        }
    }
    public void update(Tutoria tutoria) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(tutoria);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una tutoría por su ID
    public Tutoria getById(int id) {
        Transaction transaction = null;
        Tutoria tutoria = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Obtener la tutoría por su ID
            tutoria = session.get(Tutoria.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return tutoria;
    }

    // Método para obtener las tutorías disponibles, excluyendo las ya aceptadas por el alumno
    public List<Tutoria> getTutoriasDisponiblesParaAlumno(int alumnoId) {
        Transaction transaction = null;
        List<Tutoria> tutorias = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Consulta para obtener las tutorías que no han sido aceptadas por el alumno
            String hql = "FROM Tutoria t WHERE t.id NOT IN " +
                    "(SELECT s.tutoria.id FROM SolicitudTutoria s WHERE s.alumno.id = :alumnoId AND s.estado = 'Aceptada')";

            tutorias = session.createQuery(hql, Tutoria.class)
                    .setParameter("alumnoId", alumnoId)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return tutorias;
    }



}
