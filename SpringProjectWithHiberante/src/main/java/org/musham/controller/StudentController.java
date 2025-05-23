package org.musham.controller;

import org.apache.coyote.Response;
import org.musham.model.Student;
import org.musham.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping("/")
    public String getWelcomeMessage() {
        return "Welcome Message";
    }

    @GetMapping("/welcome/{emp-name}")
    public String getWelcomeMessage(@PathVariable(name = "emp-name") String name) {
        return "Welcome Message for " + name;
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeMsg(@RequestParam(name = "empName") String name) {
        return new ResponseEntity("Welcome Message for " + name + " with request parameter",
                HttpStatus.OK);
    }

    @GetMapping("/students")
    public List<Student> getAllStudentInfo() {
        return repository.findAll();
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @DeleteMapping("student/{studentID}")
    public Optional<Student> deleteStudent(@PathVariable String studentID) {
        Optional<Student> student = repository.findById(studentID);
        repository.deleteById(studentID);

        return student; 
    }


}
