package com.myy803.traineeship_project.strategies;

public class SupervisorAssignmentFactory {

	public SupervisorAssignmentStrategy create(String strategy) {
		if (strategy.contentEquals("interests")) {
			return new SupervisorAssignmentByInterests();
		}
		
		if (strategy.contentEquals("load")) {
			return new SupervisorAssignmentByLoad();
		}
		
		return null;
	}
}
