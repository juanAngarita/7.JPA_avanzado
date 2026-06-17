package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Profesor {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String correo;

    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "profesor")
    private List<Curso> cursos = new ArrayList<>();

    public Profesor(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

}
