package com.github.isuru20ip.autoadmin.dao.core;

import com.github.isuru20ip.autoadmin.entity.Student;

import java.util.List;

public interface StudentDAOCore {
    public void insert(Student student) throws Exception;
    public void update(Student student) throws Exception;
    public Student findById(Long student) throws Exception;
    public List<Student> findAll() throws Exception;
}
