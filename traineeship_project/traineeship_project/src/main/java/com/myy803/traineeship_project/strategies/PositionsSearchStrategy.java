package com.myy803.traineeship_project.strategies;

import java.util.List;

import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.mapper.StudentMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

public interface PositionsSearchStrategy {
	List<TraineeshipPosition> search(Integer id, TraineeshipMapper tMapper, StudentMapper sMapper);
}
