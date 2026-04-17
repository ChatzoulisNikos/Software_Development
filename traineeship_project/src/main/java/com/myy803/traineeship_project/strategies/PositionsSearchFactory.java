package com.myy803.traineeship_project.strategies;

public class PositionsSearchFactory {

	public PositionsSearchStrategy create(String searchStrategy) {
		
		if (searchStrategy.contentEquals("locationSearch"))
			return new PositionsSearchByLocation();
		
		if (searchStrategy.contentEquals("topicsSearch"))
			return new PositionsSearchByTopics();
		
		if (searchStrategy.contentEquals("location&topics"))
			return new PositionsSearchByLocationAndTopics();
		
		return null;
	
	}
}
