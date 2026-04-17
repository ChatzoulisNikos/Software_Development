package com.myy803.traineeship_project.mapper;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myy803.traineeship_project.domainmodel.Professor;

public interface ProfessorMapper extends JpaRepository<Professor, Integer>{

	Professor findByUsername(String username);

}
