package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Inscripcion {

    @Id
    @GeneratedValue
    private Long id;
    private float nota;

    @ManyToOne
    Curso curso;

    @ManyToOne
    Student student;
}
