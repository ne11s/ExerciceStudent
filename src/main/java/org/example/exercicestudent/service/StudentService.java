package org.example.exercicestudent.service;

import org.example.exercicestudent.model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class StudentService {
    private final Map<UUID, Student> students;

    public StudentService() {
        students = new HashMap<>();

        Student student1 = new Student().builder().firstName("San").lastName("Nel").age(24).email("nelsan@mail.com").id(UUID.randomUUID()).build();
        Student student2 = new Student().builder().firstName("Scelle").lastName("Noa").age(22).email("NoaScelle@mail.com").id(UUID.randomUUID()).build();
        Student student3 = new Student().builder().firstName("Saidi").lastName("Salim").age(24).email("Salimou@mail.com").id(UUID.randomUUID()).build();

        this.students.put(student1.getId(), student1);
        this.students.put(student2.getId(), student2);
        this.students.put(student3.getId(), student3);
    }
    public List<Student> getAllStudents() {
        return this.students.values().stream().toList();
    }

    public Student getStudent(UUID id) {
        return this.students.get(id);
    }
    public Student createStudent(Student student) {
        this.students.put(student.getId(), student);
        return student;
    }
    public List<Student> getStudentByFirstName(String firstName) {
        return this.students.values().stream().filter(student -> student.getFirstName().toLowerCase().contains(firstName.toLowerCase())).toList();
    }
    public String deleteStudent(UUID id) {
        this.students.remove(id);
        return id.toString();
    }
    public Student updateStudent(UUID id, Student student) {
        this.students.put(id, student);
        return student;
    }
}
