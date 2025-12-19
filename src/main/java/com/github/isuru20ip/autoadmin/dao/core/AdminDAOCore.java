package com.github.isuru20ip.autoadmin.dao.core;

import com.github.isuru20ip.autoadmin.entity.Admin;

public interface AdminDAOCore {
    public boolean insertAdmin(Admin admin) throws Exception;
    public void updateAdmin(Admin admin) throws Exception;
    public Admin getAdminByUsernameAndPassword(String username, String password)throws Exception;
}
