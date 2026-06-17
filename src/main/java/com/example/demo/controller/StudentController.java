package com.example.demo.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Curso;
import com.example.demo.entities.Student;
import com.example.demo.service.CarreraService;
import com.example.demo.service.CursoService;
import com.example.demo.service.InscripcionService;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    CarreraService carreraService;

    @Autowired
    CursoService cursoService;

    @Autowired
    InscripcionService inscripcionService;

    Logger log = LoggerFactory.getLogger(StudentController.class);

    // http://localhost:8080/student
    @GetMapping()
    public String mostrarEstudiantes(Model model) {
        model.addAttribute("estudiantes", studentService.searchAll());
        return "mostrar_todos_estudiantes";
    }

    // http://localhost:8080/student/1
    @GetMapping("/{id}")
    public String mostrarEstudiantePorId(Model model, @PathVariable("id") Long identificacion) {

        Student student = studentService.searchById(identificacion);

        model.addAttribute("estudiante", student);

        return "mostrar_estudiante";
    }

    // http://localhost:8080/student?id=1
    @GetMapping(params = "id")
    public String mostrarEstudiantePorId2(Model model, @RequestParam("id") Long identificacion) {

        Student student = studentService.searchById(identificacion);

        model.addAttribute("estudiante", student);

        return "mostrar_estudiante";
    }

    @GetMapping("/delete/{id}")
    public String eliminarEstudiante(@PathVariable("id") Long id) {
        studentService.delete(id);
        return "redirect:/student";
    }

    @GetMapping("/add")
    public String mostrarFormularioCrear(Model model) {

        Student student = new Student("", 0, "", "");

        model.addAttribute("estudiante", student);

        model.addAttribute("carreras", carreraService.searchAll());

        return "crear_estudiante";
    }

    @GetMapping("/update/{id}")
    public String getMethodName(@PathVariable("id") Long id, Model model) {
        Student student = studentService.searchById(id);

        model.addAttribute("estudiante", student);
        model.addAttribute("carreras", carreraService.searchAll());

        return "crear_estudiante";
    }

    @PostMapping("/add")
    public String agregarEstudiante(
            @ModelAttribute("estudiante") Student student,
            @RequestParam Long carreraId) {

        log.info(student.getId() + " - " + student.getNombre());

        studentService.save(student, carreraId);

        return "redirect:/student";
    }

    @GetMapping("/{id}/inscribir")
    public String mostrarFormularioInscripcion(@PathVariable Long id, Model model) {
        Student student = studentService.searchById(id);
        model.addAttribute("estudiante", student);
        List<Curso> cursos = cursoService.searchAll();
        model.addAttribute("cursos", cursos);

        Set<Long> cursosInscritos = student.getInscripciones()
                .stream()
                .map(i -> i.getCurso().getId())
                .collect(Collectors.toSet());

        model.addAttribute("cursosInscritos", cursosInscritos);

        return "inscribir";
    }

    @PostMapping("/{id}/inscribir")
    public String procesarInscripcionMultiple(
            @PathVariable Long id,
            @RequestParam List<Long> cursosIds) {

        inscripcionService.createAndSaveMultipleInscriptions(id, cursosIds);

        return "redirect:/student/" + id;
    }

    // nuevo
    @GetMapping(params = "nombre")
    public String buscarEstudiantesPorNombre(@RequestParam String nombre, Model model) {
        model.addAttribute("estudiantes", studentService.findByNombre(nombre));

        return "mostrar_todos_estudiantes";
    }

}
