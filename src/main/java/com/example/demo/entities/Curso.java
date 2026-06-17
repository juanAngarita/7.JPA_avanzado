package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String semestre;

    @ManyToOne
    private Profesor profesor;

    @OneToMany(mappedBy = "curso")
    private List<Inscripcion> inscripciones = new ArrayList<>();

}
