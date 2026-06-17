package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
// @Table(name = "STUNDENT_TABLE")
public class Student {

    // 1,2,3,4....1000
    // asdasda-asdasdasd-asdasdasd

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_name", length = 50, nullable = false)
    private String nombre;
    @Column(nullable = false)
    private int semestre = 1;
    @Column(length = 70, unique = true)
    private String correo;
    private String imageUrl;

    @ManyToOne
    private Carrera carrera;

    @ManyToMany(mappedBy = "students")
    private List<Profesor> profesores = new ArrayList<>();

    public Student(String nombre, int semestre, String correo, String imageUrl) {
        this.nombre = nombre;
        this.semestre = semestre;
        this.correo = correo;
        this.imageUrl = imageUrl;
    }

}
