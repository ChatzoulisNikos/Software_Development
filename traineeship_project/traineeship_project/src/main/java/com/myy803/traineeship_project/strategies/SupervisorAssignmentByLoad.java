package com.myy803.traineeship_project.strategies;

import java.util.List;
import com.myy803.traineeship_project.domainmodel.Professor;
import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.mapper.ProfessorMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

public class SupervisorAssignmentByLoad implements SupervisorAssignmentStrategy {

	@Override
	public void assign(Integer id, TraineeshipMapper tMapper, ProfessorMapper pMapper) {

		List<Professor> allProfessors = pMapper.findAll();
		TraineeshipPosition position = tMapper.findById(id).get();
		
		Professor professorL = new Professor();
		int minimumLoad = 99999999;
		for (Professor professor: allProfessors) {
			int load = professor.getSupervisedPositions().size();
			if (load < minimumLoad) {
				professorL = professor;
				minimumLoad = load;
			}
			
		}
		
		if (allProfessors.size() > 0) {
			position.setSupervisor(professorL);
			professorL.getSupervisedPositions().add(position);
			
			tMapper.save(position);
			pMapper.save(professorL);
		}
	}
	


}
