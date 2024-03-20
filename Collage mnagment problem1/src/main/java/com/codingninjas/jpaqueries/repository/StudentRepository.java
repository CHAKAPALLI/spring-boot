package com.codingninjas.jpaqueries.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingninjas.jpaqueries.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findAllByCourses_name(String course);

}
