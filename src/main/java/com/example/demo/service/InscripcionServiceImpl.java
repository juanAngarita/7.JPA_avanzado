package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Curso;
import com.example.demo.entities.Inscripcion;
import com.example.demo.entities.Student;
import com.example.demo.repository.InscripcionRepository;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    @Autowired
    InscripcionRepository inscripcionRepository;

    @Autowired
    StudentService studentService;

    @Autowired
    CursoService cursoService;

    @Override
    public void createAndSaveInscription(Student student, Curso curso) {

        Inscripcion inscripcion = Inscripcion.builder().curso(curso).student(student).build();
        inscripcionRepository.save(inscripcion);

    }

    @Override
    public void createAndSaveMultipleInscriptions(Long studentId, List<Long> cursosIds) {

        // eliminar todas las demas inscripciones

        Student student = studentService.searchById(studentId);

        for (Long cursoId : cursosIds) {
            Curso curso = cursoService.searchById(cursoId);
            createAndSaveInscription(student, curso);
        }

    }

}
