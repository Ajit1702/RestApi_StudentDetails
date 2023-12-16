package com.example.SpringDataBase.Services;

import java.util.List;

import com.example.SpringDataBase.Models.Student;

public interface StudentService {
	
public Student addStudent(Student student);

public List<Student> addListofStudent(List<Student> list);

public Student getStudentById(int id);

public List<Student> showStudent();

public Student deleteById(int id);

public Student updateStudent(Student s);
}
