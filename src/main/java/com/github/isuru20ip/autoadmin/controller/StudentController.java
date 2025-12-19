package com.github.isuru20ip.autoadmin.controller;

import com.github.isuru20ip.autoadmin.entity.Student;
import com.github.isuru20ip.autoadmin.service.StudentService;
import com.github.isuru20ip.autoadmin.service.core.StudentServiceCore;

import java.util.List;
import javax.swing.JOptionPane;

public class StudentController {

    private final StudentServiceCore studentServiceCore;

    public StudentController() {
        this.studentServiceCore = new StudentService();
    }

    public void saveStudent(Student student) {
        try {
            studentServiceCore.registerNewStudent(student);
            JOptionPane.showMessageDialog(null,
                    "Student '" + student.getFirstName() + "' registered successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "Error registering student:\n" + e.getMessage(),
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Student getStudentById(Long id) {
        try {
            return studentServiceCore.getStudentById(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error retrieving student from database.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public List<Student> getAllStudents() {
        try {
            return studentServiceCore.getAllStudents();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error retrieving student list from database.",
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return List.of();
        }
    }

    public void updateStudent(Student student) {
        try {
            studentServiceCore.updateStudentDetails(student);
            JOptionPane.showMessageDialog(null,
                    "Student ID " + student.getId() + " updated successfully!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error updating student:\n" + e.getMessage(),
                    "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}