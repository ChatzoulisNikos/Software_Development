package com.myy803.traineeship_project.strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.myy803.traineeship_project.domainmodel.Student;
import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.mapper.StudentMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

public class PositionsSearchByLocation implements PositionsSearchStrategy {

	@Override
	public List<TraineeshipPosition> search(Integer id, TraineeshipMapper tMapper, StudentMapper sMapper) {
		List<TraineeshipPosition> positions = new ArrayList<TraineeshipPosition>();
		List<TraineeshipPosition> allPositions = tMapper.findAllByIsAssigned(false);
		Student student = sMapper.findById(id).get();
		
		for (TraineeshipPosition position: allPositions) {
			String location = position.getCompany().getLocation();
			if (student.getPreferredLocation().contentEquals(location) && matchSkills(position.getSkills(),student.getSkills())) {
				positions.add(position);
			}
		}
		
		return positions;
	}
	
	private boolean matchSkills(String requestedSkills, String studentSkills) {
		List<String> reqSkillsList = Arrays.asList(requestedSkills.split(","));
		List<String> stuSkillsList = Arrays.asList(studentSkills.split(","));
		
		for (String skill: stuSkillsList) {
			if (reqSkillsList.contains(skill)) {
				return true;
			}
		}
		return false;
	}

}
