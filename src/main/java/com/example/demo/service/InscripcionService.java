package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Curso;
import com.example.demo.entities.Student;

public interface InscripcionService {
    void createAndSaveInscription(Student student, Curso curso);

    void createAndSaveMultipleInscriptions(Long studentId, List<Long> cursosIds);
}
