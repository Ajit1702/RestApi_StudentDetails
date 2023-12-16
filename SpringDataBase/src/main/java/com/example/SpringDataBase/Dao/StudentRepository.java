package com.example.SpringDataBase.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringDataBase.Models.Student;

public interface StudentRepository extends JpaRepository<Student,Integer>{
	

}
