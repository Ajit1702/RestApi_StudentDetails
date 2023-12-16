package com.example.SpringDataBase.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.SpringDataBase.Dao.StudentRepository;
import com.example.SpringDataBase.Models.Student;

@Service
public class StudentServiceImpl implements StudentService {

	private StudentRepository repository;

	public StudentServiceImpl(StudentRepository repository) {
		this.repository = repository;
	}

	@Override
	public Student addStudent(Student student) {
		Student s = repository.save(student);
		return s;
	}

	@Override
	public List<Student> addListofStudent(List<Student> list) {
		List<Student> savedStudents = repository.saveAll(list);
		return savedStudents;
	}

	@Override
	public Student getStudentById(int id) {
		Optional<Student> s = repository.findById(id);

		if (s.isPresent()) {
			Student student = s.get();
			return student;
		} else {
			throw new NoSuchElementException("Student with ID" + id + "not found");
		}

	}

	@Override
	public List<Student> showStudent() {
		List<Student> list = repository.findAll();
		return list;
	}

	@Override
	public Student deleteById(int id) {
		Optional<Student> deleteStudent = repository.findById(id);
		if (deleteStudent.isPresent()) {
			Student student = deleteStudent.get();
			repository.deleteById(id);
			return student;
		} else {
			throw new NoSuchElementException("Student with ID" + id + "not found");
		}

	}

	@Override
	public Student updateStudent(Student student) {
		System.out.println("Updating student with ID: " + student.getId());
		Optional<Student> optional = repository.findById(student.getId());

		if (optional.isPresent()) {
			Student existingStudent = optional.get();
			existingStudent.setName(student.getName());
			existingStudent.setEmail(student.getEmail());
			existingStudent.setAddress(student.getAddress());
			existingStudent.setPhone(student.getPhone());

			return repository.save(existingStudent);
		} else {
			throw new NoSuchElementException("Student not found");
		}
	}

}
