package com.example.demo.controller;

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

import com.example.demo.entities.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.CarreraService;
import com.example.demo.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/student")
@Controller
public class StudentController {

    @Autowired
    StudentService service;

    @Autowired
    CarreraService carreraService;

    Logger log = LoggerFactory.getLogger(StudentController.class);

    @GetMapping()
    public String mostrarEstudiantes(Model model) {
        model.addAttribute("estudiantes", service.searchAll());
        return "mostrar_todos_estudiantes";
    }

    // http://localhost:8080/student/1/tareas
    @GetMapping("/{id}")
    public String mostrarEstudiantePorId(Model model, @PathVariable("id") Long identificacion) {

        Student student = service.searchById(identificacion);

        model.addAttribute("estudiante", student);

        return "mostrar_estudiante";
    }

    // http://localhost:8080/student?nombre=pedro&edad=20&genero=masculino
    @GetMapping(params = "id")
    public String mostrarEstudiantePorId2(Model model, @RequestParam("id") Long identificacion) {

        Student student = service.searchById(identificacion);

        model.addAttribute("estudiante", student);

        return "mostrar_estudiante";
    }

    @GetMapping("/delete/{id}")
    public String eliminarEstudiante(@PathVariable("id") Long id) {
        service.delete(id);
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
        Student student = service.searchById(id);

        model.addAttribute("estudiante", student);

                model.addAttribute("carreras", carreraService.searchAll());


        return "crear_estudiante";
    }

    @PostMapping("/add")
    public String agregarEstudiante(
            @ModelAttribute("estudiante") Student student,
            @RequestParam Long carreraId) {

        log.info(student.getId() + " - " + student.getNombre());

        service.save(student, carreraId);

        return "redirect:/student";
    }

        // nuevo
    @GetMapping(params = "nombre")
    public String buscarEstudiantesPorNombre(@RequestParam String nombre, Model model) {
        model.addAttribute("estudiantes", service.findByNombre(nombre));

        return "mostrar_todos_estudiantes";
    }



}
