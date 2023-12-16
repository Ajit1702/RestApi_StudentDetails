package com.example.SpringDataBase.Controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringDataBase.Models.Student;
import com.example.SpringDataBase.Services.StudentService;

@RestController
public class MainController {

	@Autowired
	private StudentService service;
	
	//add one Student
	@PostMapping("/new")
	public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {
	    Student createdStudent = service.addStudent(student);
	    return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	}
	
	//add more than one student 
	@PostMapping("/saveAll")
	public ResponseEntity<List<Student>> saveAllStudent(@RequestBody List<Student> list) {
	    List<Student> savedStudents = service.addListofStudent(list);
	    return new ResponseEntity<>(savedStudents, HttpStatus.OK);
	}
	
	//get student by id
	 @GetMapping("/{id}")
	    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
	        try {
	            Student studentById = service.getStudentById(id);
	            return new ResponseEntity<>(studentById, HttpStatus.OK);
	        } catch (NoSuchElementException e) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            // Log the exception for debugging purposes
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	
	//get all student
	@GetMapping("/showAllStudent")
	public ResponseEntity<List<Student>> showAllStudent() {
	    List<Student> studentList = service.showStudent();

	    if (!studentList.isEmpty()) {
	        return new ResponseEntity<>(studentList, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	}
	
	//delete student by id
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
	    try {
	        service.deleteById(id);
	        return new ResponseEntity<>("Student with ID " + id + " deleted successfully", HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>("Student with ID " + id + " not found", HttpStatus.NOT_FOUND);
	    }
	}
	
	//update student
	@PutMapping("/updatestudent")
	public ResponseEntity<String> updateStudent(@RequestBody Student s) {
	    try {
	        Student updatedStudent = service.updateStudent(s);
	        return new ResponseEntity<>("Student with ID " + s.getId() + " updated successfully", HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<>("Student with ID " + s.getId() + " not found", HttpStatus.NOT_FOUND);
	    }
	}
	
}
