package com.myy803.traineeship_project.strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myy803.traineeship_project.domainmodel.Professor;
import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.mapper.ProfessorMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

public class SupervisorAssignmentByInterests implements SupervisorAssignmentStrategy {

	@Override
	public void assign(Integer id, TraineeshipMapper tMapper, ProfessorMapper pMapper) {
		Map<Professor, Double> professors = new HashMap<Professor, Double>();
		List<Professor> allProfessors = pMapper.findAll();
		TraineeshipPosition position = tMapper.findById(id).get();
		
		List<String> topics = Arrays.asList(position.getTopics().split(","));
		
		for (Professor professor: allProfessors) {
			List<String> interests = Arrays.asList(position.getTopics().split(","));
			
			professors.put(professor,getJaccardSimilarity(topics,interests));

		}
		
		Professor professor = new Professor();
		double jaccardSimilarity = 0;
		
		for (Map.Entry<Professor, Double> entry : professors.entrySet()) {
			if (entry.getValue() > jaccardSimilarity) {
				professor = entry.getKey();
				jaccardSimilarity = entry.getValue();
			}
		}

		if (jaccardSimilarity > 0.65) {
			position.setSupervisor(professor);
			professor.getSupervisedPositions().add(position);
			
			tMapper.save(position);
			pMapper.save(professor);
		}
		
	}
	
	private double getJaccardSimilarity(List<String> topics, List<String> interests) {
		
		List<String> intersection = new ArrayList<>();
		for (String topic: topics) {
			if (interests.contains(topic)) {
				intersection.add(topic);
			}
		}
		List<String> union = new ArrayList<>(topics);
		for (String interest: interests) {
			if(!union.contains(interest)) {
				union.add(interest);
			}
		}
		
		return ((double)intersection.size()/union.size());
	}

}
