package com.github.isuru20ip.autoadmin.service;

import java.util.List;

import com.github.isuru20ip.autoadmin.dao.StudentDAO;
import com.github.isuru20ip.autoadmin.dao.core.StudentDAOCore;
import com.github.isuru20ip.autoadmin.entity.Student;
import com.github.isuru20ip.autoadmin.service.core.StudentServiceCore;

public class StudentService implements StudentServiceCore {

    private final StudentDAOCore studentDAOCore;


    public StudentService() {
        this.studentDAOCore = new StudentDAO();
    }

    @Override
    public void registerNewStudent(Student student) throws Exception {
        studentDAOCore.insert(student);
    }

    @Override
    public void updateStudentDetails(Student student) throws Exception {
        studentDAOCore.update(student);
    }

    @Override
    public List<Student> getAllStudents() throws Exception {
            return studentDAOCore.findAll();
    }

    @Override
    public Student getStudentById(Long id) throws Exception {
            return studentDAOCore.findById(id);
    }
}
