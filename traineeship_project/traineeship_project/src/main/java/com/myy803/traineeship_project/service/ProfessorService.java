package com.myy803.traineeship_project.service;

import org.springframework.stereotype.Service;

import com.myy803.traineeship_project.domainmodel.Professor;
import com.myy803.traineeship_project.domainmodel.ProfessorEvaluation;

@Service
public interface ProfessorService {

	public void saveProfile(Professor professor);

	void saveEvaluation(ProfessorEvaluation evaluation);
}
