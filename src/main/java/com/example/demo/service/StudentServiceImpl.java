package com.example.demo.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Carrera;
import com.example.demo.entities.Profesor;
import com.example.demo.entities.Student;
import com.example.demo.errors.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository repo;

    @Autowired
    CarreraService carreraService;

    @Override
    public Student searchById(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new StudentNotFoundException(id));
    }

    @Override
    public Collection<Student> searchAll() {
        return repo.findAll();
    }

    @Override
    @Transactional
    public void save(Student student, Long carreraId) {
        Carrera carrera = carreraService.searchById(carreraId);
        student.setCarrera(carrera);
        repo.save(student);
    }

    @Override
    @Transactional // commit/ roleback
    public void delete(Long id) {
        // repo.deleteById(id);

        Student student = repo.findById(id).orElseThrow();

        for (Profesor t : student.getProfesores()) {
            t.getStudents().remove(student);
        }

        student.getProfesores().clear();
        repo.delete(student);
    }

    @Override
    public List<Student> findByNombre(String nombre) {
        if(nombre == null || nombre.isEmpty()) {
            return repo.findAll();
        }
        return repo.findByNombre(nombre);
    }

}
