package com.myy803.traineeship_project.strategies;

import java.util.ArrayList;
import java.util.List;

import com.myy803.traineeship_project.domainmodel.TraineeshipPosition;
import com.myy803.traineeship_project.mapper.StudentMapper;
import com.myy803.traineeship_project.mapper.TraineeshipMapper;

public class PositionsSearchByLocationAndTopics implements PositionsSearchStrategy {

	@Override
	public List<TraineeshipPosition> search(Integer id, TraineeshipMapper tMapper, StudentMapper sMapper) {
		List<TraineeshipPosition> positions = new ArrayList<TraineeshipPosition>();
		PositionsSearchByTopics topicsSearch = new PositionsSearchByTopics();
		PositionsSearchByLocation locationSearch = new PositionsSearchByLocation();
		List<TraineeshipPosition> topicPositions = topicsSearch.search(id, tMapper, sMapper);
		List<TraineeshipPosition> locationPositions = locationSearch.search(id, tMapper, sMapper);
		
		for (TraineeshipPosition position : topicPositions) {
            if (locationPositions.contains(position)) {
                positions.add(position);
            }
        }
		return positions;
	}

}
