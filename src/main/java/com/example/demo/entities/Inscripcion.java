package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    public Inscripcion(float nota) {
        this.nota = nota;
    }
}
