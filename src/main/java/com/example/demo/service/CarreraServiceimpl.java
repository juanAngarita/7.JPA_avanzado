package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Carrera;
import com.example.demo.repository.CarreraRepository;

@Service
public class CarreraServiceimpl implements CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Override
    public List<Carrera> searchAll() {
        return carreraRepository.findAll();
    }

    @Override
    public Carrera searchById(Long id) {
        return carreraRepository.findById(id).orElseThrow();
    }

}
