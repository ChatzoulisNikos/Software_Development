package com.myy803.traineeship_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myy803.traineeship_project.domainmodel.Professor;
import com.myy803.traineeship_project.domainmodel.ProfessorEvaluation;
import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.mapper.ProfessorEvaluationMapper;
import com.myy803.traineeship_project.mapper.ProfessorMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	private ProfessorMapper mapper;
	@Autowired
	private ProfessorEvaluationMapper evMapper;
	@Autowired
	private TraineeshipMapper tMapper;
	
	@Override
	public void saveProfile(Professor professor) {
		if (mapper.findByUsername(professor.getUsername()) == null) {
			mapper.save(professor);	
		}
	}
	
	@Override
	public void saveEvaluation(ProfessorEvaluation evaluation) {
		TraineeshipPosition position = evaluation.getPosition();
		position.setProfessorEvaluation(evaluation);
		evMapper.save(evaluation);
		tMapper.save(position);
	}
	
}
