package com.github.isuru20ip.autoadmin.dao;

import com.github.isuru20ip.autoadmin.config.HibernateUtil;
import com.github.isuru20ip.autoadmin.dao.core.AdminDAOCore;
import com.github.isuru20ip.autoadmin.entity.Admin;
import com.github.isuru20ip.autoadmin.util.CustomExceptions;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminDAO implements AdminDAOCore {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDAO.class);

    private Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    @Override
    public boolean insertAdmin(Admin admin) throws Exception {
        Transaction transaction = null;
        try {
            Session session = getCurrentSession();
            transaction = session.beginTransaction();
            session.persist(admin);
            transaction.commit();
            LOGGER.info("new admin account has been inserted successfully");
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("error in inserting admin account", e);
            throw new CustomExceptions.RepositoryException("failed to save new admin", e);
        }
    }

    @Override
    public void updateAdmin(Admin admin) throws Exception {
        Transaction transaction = null;
        try {
            Session session = getCurrentSession();
            transaction = session.beginTransaction();
            session.merge(admin);
            transaction.commit();
            LOGGER.info("admin account has been updated successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("error in update admin account", e);
            throw new CustomExceptions.RepositoryException("failed to update Admin", e);
        }
    }

    @Override
    public Admin getAdminByUsernameAndPassword(String username, String password) throws Exception {
        Transaction transaction = null;
        Admin admin = null;
        try {
            Session session = getCurrentSession();
            transaction = session.beginTransaction();

            String hql = "FROM Admin A WHERE A.username = :username AND A.password = :password";
            Query<Admin> query = session.createQuery(hql, Admin.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            admin = query.getSingleResultOrNull();
            transaction.commit();
            LOGGER.info("admin account has been logged successfully");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.error("error in logging admin", e);
            throw new CustomExceptions.RepositoryException("failed to fetching Admin", e);
        }
        return admin;
    }
}
