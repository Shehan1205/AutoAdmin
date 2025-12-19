package com.github.isuru20ip.autoadmin.service.core;

import com.github.isuru20ip.autoadmin.entity.Student;

import java.util.List;

public interface StudentServiceCore {

    // Business rule: Save a new student after validation
    void registerNewStudent(Student student) throws Exception;

    // Business rule: Get all students
    List<Student> getAllStudents() throws Exception;

    // Business rule: Update existing student details
    void updateStudentDetails(Student student) throws Exception;


    // Utility for the Controller
    Student getStudentById(Long id) throws Exception;
}
