package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNombre(String nombre);

}
