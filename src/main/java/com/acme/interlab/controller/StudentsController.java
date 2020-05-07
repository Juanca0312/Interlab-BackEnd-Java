package com.acme.interlab.controller;

import com.acme.interlab.exception.ResourceNotFoundException;
import com.acme.interlab.model.Student;
import com.acme.interlab.repository.StudentRepository;
import com.acme.interlab.resource.SaveStudentResource;
import com.acme.interlab.resource.StudentResource;
import com.acme.interlab.service.StudentService;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.SafeHtml;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentsController  {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudentService studentService;

    public Page<StudentResource> getAllStudents(Pageable pageable) {
        Page<Student> studentPage = studentService.getAllStudents(pageable);
        List<StudentResource> resources = studentPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/students/{id}")
    public StudentResource getStudentById(
            @PathVariable(name = "id") int id) {
        return convertToResource(studentService.getStudentById(id));
    }


    @PostMapping("/students")
    public StudentResource createPost(@Valid @RequestBody SaveStudentResource resource)  {
        Student student = convertToEntity(resource);
        return convertToResource(studentService.createStudent(student));
    }

    @PutMapping("/student/{id}")
    public StudentResource updatePost(@PathVariable(name = "id") int id, @Valid @RequestBody SaveStudentResource resource) {
        Student student = convertToEntity(resource);
        return convertToResource(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") int id) {
        return studentService.deleteStudent(id);
    }






    private Student convertToEntity(SaveStudentResource resource) {
        return mapper.map(resource, Student.class);
    }

    private StudentResource convertToResource(Student entity) {
        return mapper.map(entity, StudentResource.class);
    }







}
