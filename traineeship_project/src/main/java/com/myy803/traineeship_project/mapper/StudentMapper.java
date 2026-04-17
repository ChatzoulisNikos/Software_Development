package com.myy803.traineeship_project.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myy803.traineeship_project.domainmodel.Student;

public interface StudentMapper extends JpaRepository<Student, Integer>{
	Student findByUsername(String username);

	List<Student> findAllByLookingForTraineeship(boolean lookingForTraineeship);
}
