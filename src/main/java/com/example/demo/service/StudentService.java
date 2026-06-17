package com.example.demo.service;

import java.util.Collection;

import com.example.demo.entities.Student;

public interface StudentService {

    public Student searchById(Long id);

    public Collection<Student> searchAll();

    public void save(Student student, Long carreraId);

    public void delete(Long id);

}
