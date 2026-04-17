package com.myy803.traineeship_project.strategies;

import com.myy803.traineeship_project.mapper.ProfessorMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

public interface SupervisorAssignmentStrategy {
	void assign(Integer id, TraineeshipMapper tMapper, ProfessorMapper pMapper);
}
