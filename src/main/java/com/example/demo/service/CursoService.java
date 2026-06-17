package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Curso;

public interface CursoService {

    public Curso searchById(Long id);

    public List<Curso> searchAll();

}
