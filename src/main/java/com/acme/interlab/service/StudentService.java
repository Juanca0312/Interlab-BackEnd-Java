package com.acme.interlab.service;

import com.acme.interlab.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    Page<Student> getAllStudents(Pageable pageable);
    ResponseEntity<?> deleteStudent(int id);
    Student updateStudent(int Id, Student studentRequest);
    Student createStudent(Student student);
    Student getStudentById(int id);
}
