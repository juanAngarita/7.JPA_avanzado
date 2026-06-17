package com.example.demo.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Student;

import lombok.extern.java.Log;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // sql: select * from student where nombre = ?
    // named queries
    List<Student> findByNombre(String nombre);

    List<Student> findBySemestreLessThan(Integer semestre);

    /*
     * select s.*
     * FROM student s
     * left join inscripcion i
     * on i.student_id = s.id
     * group by s.id
     * having count(i.id) < ?
     */

    // jpql, sql de jpa
    // jpql a sql de h2, jpql a sql de mysql
    @Query("""
                SELECT s
                FROM Student s
                LEFT JOIN s.inscripciones i
                group by s
                having count(i) < :cantidad
            """)
    List<Student> findStudentsWithLessThanInscripciones(@Param("cantidad") long cantidadInscripciones);

    @Query("""
                SELECT s
                FROM Student s
                LEFT JOIN s.inscripciones i
                group by s
                having AVG(i.nota) < :promedio
            """)
    List<Student> findStudentWithPromedioMenorA(@Param("promedio") double promedio);

}
