package com.github.isuru20ip.autoadmin.dao.core;

import com.github.isuru20ip.autoadmin.dao.AdminDAO;
import com.github.isuru20ip.autoadmin.entity.Admin;
import com.github.isuru20ip.autoadmin.util.AdminType;
import com.github.isuru20ip.autoadmin.util.CustomExceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminDAOTest {

    private final AdminDAO adminDAO = new AdminDAO();
    private static Admin searchAdmin;

    @Test
    void insert_admin_user_name_null() {
        Admin admin = new Admin(null, "@admiN123", AdminType.MASTER.name());
        assertThrows(CustomExceptions.RepositoryException.class, () -> adminDAO.insertAdmin(admin));
    }

    @Test
    void insert_admin_password_null() {
        Admin admin = new Admin("admin", null, AdminType.CASHIER.name());
        assertThrows(CustomExceptions.RepositoryException.class, () -> adminDAO.insertAdmin(admin));
    }

    @Test
    void insert_admin_type_null() {
        Admin admin = new Admin("admin", "@admiN123", null);
        assertThrows(CustomExceptions.RepositoryException.class, () -> adminDAO.insertAdmin(admin));
    }

    @Test
    void insert_admin_user_name_long_than_size() {
        //max 20 char for all columns;
        Admin admin = new Admin("admin01234567890123456789123", "@admiN123", AdminType.CASHIER.name());
        assertThrows(CustomExceptions.RepositoryException.class, () -> adminDAO.insertAdmin(admin));
    }

    @Test
    void insert_admin_password_long_than_size() {
        //max 20 char for all columns;
        Admin admin = new Admin("admin", "@admiN12301234567890123456789123", AdminType.CASHIER.name());
        assertThrows(CustomExceptions.RepositoryException.class, () -> adminDAO.insertAdmin(admin));
    }

    @Test
    void insert_admin_type_long_than_size() {
        //max 20 char for all columns;
        Admin admin = new Admin("admin", "@admiN12301234567890123456789123", "12301234567890123456789123");
        assertThrows(CustomExceptions.RepositoryException.class, () -> adminDAO.insertAdmin(admin));
    }

    @Test
    void insert_admin_user_name() {
        Admin admin = new Admin("admin", "@admiN123", AdminType.MASTER.name());
        try {
            assertTrue(adminDAO.insertAdmin(admin));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void search_admin_without_username() {
        try {
            assertNull(adminDAO.getAdminByUsernameAndPassword("", "@admiN123"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void search_admin_without_password() {
        try {
            assertNull(adminDAO.getAdminByUsernameAndPassword("admin", ""));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void search_admin_with_false_username() {
        try {
            assertNull(adminDAO.getAdminByUsernameAndPassword("testusername", "@admiN123"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void search_admin_with_false_password() {
        try {
            assertNull(adminDAO.getAdminByUsernameAndPassword("admin", "testpassword"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void search_admin_with_true_details() {
        try {
            searchAdmin = adminDAO.getAdminByUsernameAndPassword("admin", "@admiN123");
            assertNotNull(searchAdmin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
