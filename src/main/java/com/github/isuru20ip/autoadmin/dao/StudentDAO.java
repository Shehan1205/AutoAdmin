package com.github.isuru20ip.autoadmin.dao;

import com.github.isuru20ip.autoadmin.config.HibernateUtil;
import com.github.isuru20ip.autoadmin.dao.core.StudentDAOCore;
import com.github.isuru20ip.autoadmin.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StudentDAO implements StudentDAOCore {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDAO.class);

    private Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    @Override
    public void insert(Student student) throws Exception {
        Transaction transaction = null;
        try (Session session = getCurrentSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
            LOGGER.info("Student {} saved successfully.", student.getFirstName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Student Registration failed for {}. Transaction rolled back.", student.getFirstName(), e);
            throw e;
        }
    }

    @Override
    public void update(Student student) throws Exception {
        Transaction transaction = null;
        try (Session session = getCurrentSession()) {
            transaction = session.beginTransaction();
            session.merge(student);
            transaction.commit();
            LOGGER.info("Student {} updated successfully.", student.getFirstName());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("Student Update  failed for {}. Transaction rolled back.", student.getFirstName(), e);
            throw e;
        }
    }

    @Override
    public Student findById(Long studentId) throws Exception {
        Transaction transaction = null;
        try (Session session = getCurrentSession()) {
            transaction = session.beginTransaction();
            Student student = session.find(Student.class, studentId);
            transaction.commit();
            return student;
        } catch (Exception e) {
            LOGGER.error("Student Search failed for {}.", studentId);
            throw e;
        }
    }

    @Override
    public List<Student> findAll() throws Exception {
        Transaction transaction = null;
        try (Session session = getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<Student> query = session.createQuery("from Student", Student.class);
            List<Student> students = query.getResultList();
            transaction.commit();
            return students;
        } catch (Exception e) {
            LOGGER.error("Student All Search failed", e);
            throw e;
        }
    }
}
