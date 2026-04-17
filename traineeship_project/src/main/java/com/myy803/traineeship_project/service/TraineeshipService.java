package com.myy803.traineeship_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;

@Service
public interface TraineeshipService {
	public void saveTraineeship(TraineeshipPosition traineeship);
	public List<TraineeshipPosition> retrieveAvailableTraineeships();
	public void deleteTraineeship(Integer traineeshipId);
	public List<TraineeshipPosition> retrieveAssignedTraineeships();
	public TraineeshipPosition retrieveTraineeship(Integer id);
	public List<TraineeshipPosition> retrieveSupervisedTraineeships();
	public List<TraineeshipPosition> retrieveTraineeshipsForApplicant(String strategy, Integer id);
	public void assignTraineeship(Integer userId, Integer trainId);
	public List<TraineeshipPosition> retrieveInProgressTraineeships();
	boolean assignProfessor(int id, String strategyString);
	void savePassFailGrade(int id, boolean grade);
}
