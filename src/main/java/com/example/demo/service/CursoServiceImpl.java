package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Curso;
import com.example.demo.repository.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Override
    public Curso searchById(Long id) {
        return cursoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Curso> searchAll() {
        return cursoRepository.findAll();
    }

}
