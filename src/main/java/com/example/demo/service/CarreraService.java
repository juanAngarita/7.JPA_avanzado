package com.example.demo.service;

import java.util.List;

import com.example.demo.entities.Carrera;

public interface CarreraService {

    public List<Carrera> searchAll();

    public Carrera searchById(Long id);

}
