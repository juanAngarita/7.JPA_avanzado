package com.example.demo;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Carrera;
import com.example.demo.entities.Profesor;
import com.example.demo.entities.Student;
import com.example.demo.repository.CarreraRepository;
import com.example.demo.repository.ProfesorRepository;
import com.example.demo.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Component
@Transactional // muchos queries rollback commit
public class DataLoader implements CommandLineRunner {

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private CarreraRepository carreraRepository;

        @Autowired
        private ProfesorRepository profesorRepository;

        @Override
        public void run(String... args) throws Exception {

                Random random = new Random(42);

                studentRepository.save(Student.builder()
                                .nombre("Juan")
                                .semestre(1)
                                .correo("Correo2@gmail.com")
                                .imageUrl("https://avatars.githubusercontent.com/u/1561955?v=4")
                                .build());
                studentRepository.save(new Student("Pedro", 2, "Correo1@pe.pe",
                                "https://avatars.githubusercontent.com/u/1561955?v=4"));
                studentRepository.save(new Student("miguel", 3, "Correo3@pe.pe",
                                "https://avatars.githubusercontent.com/u/1561955?v=4"));

                // carreras
                carreraRepository.save(Carrera.builder().nombre("Ingenieria").build());
                carreraRepository.save(new Carrera("Arquitectura"));
                carreraRepository.save(new Carrera("Admin"));

                int cantidadCarreras = carreraRepository.findAll().size();
                for (Student e : studentRepository.findAll()) {
                        int randomNum = random.nextInt(1, cantidadCarreras + 1);
                        Carrera c = carreraRepository.findById((long) randomNum).get();
                        e.setCarrera(c);
                        studentRepository.save(e);
                }

                // profesores
                profesorRepository.save(Profesor.builder().correo("Correo1@pe.pe").nombre("Profesor1").build());
                profesorRepository.save(new Profesor("profesor2", "Correo2@pe.pe"));
                profesorRepository.save(new Profesor("profesor3", "Correo3@pe.pe"));

                int cantidadEstudiantes = studentRepository.findAll().size();
                int CANTIDAD_ESTIANTES_PROFE = 2;

                for (Profesor p : profesorRepository.findAll()) {
                        for (int i = 0; i < CANTIDAD_ESTIANTES_PROFE; i++) {
                                int randomNum = random.nextInt(1, cantidadEstudiantes + 1);
                                Student s = studentRepository.findById((long) randomNum).get();
                                p.getStudents().add(s);
                                profesorRepository.save(p);
                        }
                }

        }

}
