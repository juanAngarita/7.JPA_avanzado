package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "profesores", "carrera" })
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_name", length = 50, nullable = false)
    private String nombre;
    @Column(nullable = false)
    @Builder.Default
    private int semestre = 1;
    @Column(length = 70, unique = true)
    private String correo;
    private String imageUrl;

    @ManyToOne
    private Carrera carrera;

    @ManyToMany(mappedBy = "students")
    @Builder.Default
    private List<Profesor> profesores = new ArrayList<>();

    @OneToMany(mappedBy = "student")
    @Builder.Default
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Student(String nombre, int semestre, String correo, String imageUrl) {
        this.nombre = nombre;
        this.semestre = semestre;
        this.correo = correo;
        this.imageUrl = imageUrl;
    }

}
