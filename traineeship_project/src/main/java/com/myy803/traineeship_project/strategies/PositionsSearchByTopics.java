package com.myy803.traineeship_project.strategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.myy803.traineeship_project.domainmodel.Student;
import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.mapper.StudentMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

public class PositionsSearchByTopics implements PositionsSearchStrategy {

	@Override
	public List<TraineeshipPosition> search(Integer id, TraineeshipMapper tMapper, StudentMapper sMapper) {
		List<TraineeshipPosition> positions = new ArrayList<TraineeshipPosition>();
		List<TraineeshipPosition> allPositions = tMapper.findAllByIsAssigned(false);
		Student student = sMapper.findById(id).get();
		List<String> interests = Arrays.asList(student.getInterests().split(","));
		
		for (TraineeshipPosition position: allPositions) {
			List<String> topics = Arrays.asList(position.getTopics().split(","));
			
			if(checkJaccardSimilarity(topics,interests) && matchSkills(position.getSkills(), student.getSkills())) {
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
	
	private boolean checkJaccardSimilarity(List<String> topics, List<String> interests) {
		double threshold = 0.65;
		
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
		
		return ((double)intersection.size()/union.size()) > threshold;
	}

}
