package com.myy803.traineeship_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myy803.traineeship_project.domainmodel.Student;

@Service
public interface StudentService {

	public void saveProfile(Student student);
	
	public void applyForTraineeship();

	public List<Student> retrieveAppliedUsers();

	public void saveLogbook(String logbook);

}
